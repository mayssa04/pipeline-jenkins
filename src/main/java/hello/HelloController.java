package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
    @RequestMapping("/")
    public String index() {
        return "Hi class ING-CCV3-1 , Hi Iteam University !! --> ready to go to prod from QA";
    }
    
}
