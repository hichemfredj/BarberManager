package cal.barbermanager.controller;

import cal.barbermanager.model.User;
import cal.barbermanager.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    //
    // DEPENDENCIES
    //

    private final UserService userService;

    //
    // CONSTRUCTOR
    //
    public UserController(UserService userService){
        this.userService = userService;
    }


    //
    // GET
    //

    @GetMapping("list-employer")
    public List<User> getListEmployee(){
        return userService.getListEmployer();
    }
}
