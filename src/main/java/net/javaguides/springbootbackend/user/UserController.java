package net.javaguides.springbootbackend.user;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class UserController {
    Logger logger=LoggerFactory.getLogger(UserController.class);
    @Autowired private UserService service;
    @GetMapping("/users")
    public String showUserList(Model model){
        logger.trace("Listing all employee details with trace log level");
        logger.info("Listing all employee details with info log level");
        List<User> listUsers = service.listAll();
        System.out.println("jjjj");
        model.addAttribute("listUsers",
                listUsers);
        return "users";

    }
    @GetMapping("/users/new")
    public String showNewForm(Model model){
        logger.trace("Adding new employee details with trace log level");
        logger.info("Adding new employee details with info log level");
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle","Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        logger.trace("Getting saved the new employee details with trace log level");
        logger.info("Getting saved the new employee details with info log level");
        service.save(user);
        ra.addFlashAttribute("message", "The employee details has been successfully added. ");
        return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            logger.info("Editing the employee details with info log level started");
            User user = service.get(id);
            model.addAttribute("user",user);
            model.addAttribute("pageTitle","Edit User (ID: "+ id + ")");
            logger.info("Editing the employee details with info log level ended.");
            return "user_form";
        } catch (UserNotFoundExeception e) {
            ra.addFlashAttribute("message", e.getMessage());
            logger.error("Exception while editing the employee details with info log level");
            return "redirect:/users";
        }
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            logger.info("Deleting the employee details with info log level");
            service.delete(id);
            ra.addFlashAttribute("message", "The user ID " +id + "has been deleted.");
            logger.info("Deleting the employee details with info log level is ended");
        } catch (UserNotFoundExeception e) {
            logger.error("Exception in deleting the employee details with info log level");
            ra.addFlashAttribute("message", e.getMessage());
        }
            return "redirect:/users";

    }

}
