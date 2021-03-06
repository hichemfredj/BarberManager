package cal.barbermanager.controller;

import cal.barbermanager.model.User;
import cal.barbermanager.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("list-employer")
    public List<User> getListEmployee(){
        return userService.getListEmployer();
    }

    @GetMapping("users/{id}")
    public User getUserById(@PathVariable UUID id){

        return userService.getUserById(id);

    }
}
