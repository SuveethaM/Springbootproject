package net.javaguides.springbootbackend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class  MainController {
    Logger logger=LoggerFactory.getLogger(MainController.class);
    @GetMapping("")
        public String showHomePage(){
        System.out.println("main");
        logger.debug("Showing the index of the Page ");
        return "index";
        }
}
