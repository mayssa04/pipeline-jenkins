def commit_id
def container_name="feature-container"
pipeline {
    agent any

    stages {
        stage('Preparation') {
            steps {
                checkout scm
                sh "git rev-parse --short HEAD > .git/commit-id"
                script {                        
                    commit_id = readFile('.git/commit-id').trim()
                }
            }
        }
        
        stage ('Compile Stage') {

            steps {
                withMaven(maven : 'maven3.6.3') {
                    sh 'mvn clean install'
                }
            }
        post {
            always {
               archiveArtifacts artifacts: 'target/**/*'
            }
        }
        }

        stage ('Testing Stage') {

            steps {
                withMaven(maven : 'maven3.6.3') {
                    sh 'mvn test'
                }
            }
           
        }
        
        stage('Code Quality Analysis') {
            steps {
               sh " mvn sonar:sonar -Dsonar.host.url=http://54.227.159.19:9000"
            }
        }
        
        stage(' Build Docker image') {
            steps {
                echo 'Building....'
                sh "docker build -t feature-image:${commit_id} ."
                echo 'build complete'
            }
        }
        stage('Deploy to dev') {
            steps {
                echo'Deploying'
                sh "docker stop  ${container_name}"
                sh "docker rm  ${container_name}"
                sh "docker run -p 8085:8080 -d --name ${container_name} feature-image:${commit_id}"
                echo 'deployment complete'
            }
        }

    }
}
