package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entities.User;
import web.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;
    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "users/all-users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("user", usersService.show(id));
       return "users/show";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user){
        usersService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", usersService.show(id));
        return "users/edit";
    }

    @PatchMapping()
    public String update(@ModelAttribute("user") User user){
        usersService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        usersService.delete(id);
        return "redirect:/users";
    }
}
