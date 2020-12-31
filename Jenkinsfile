def container_name="prod-container"
pipeline {
    agent any

    stages {
        stage('Preparation') {
            steps {
                checkout scm
            }
        }
        stage('Deploy to Production') {
            steps {
                echo'Deploying'
                sh "docker stop  ${container_name}"
                sh "docker rm  ${container_name}"
                sh "docker run -p 8087:8080 -d --name ${container_name} mayssa04/env-qualite:0.1.0"
                echo 'deployment complete'
            }
        }
        stage('Push to dockerhub'){
            steps {
                echo 'Connection to docker ub'
                sh 'docker login -u mayssa04 -p rootroot '
                echo 'TAG the docker image in local'
                sh "docker tag mayssa04/env-qualite:0.1.0 mayssa04/env-prod:v0.1.0"
                echo 'Push the docker image to dockerhub repository for ENV Production'
                sh 'docker push mayssa04/env-prod:v0.1.0'
            }
        }
    }
}