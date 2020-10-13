package cal.barbermanager.service;

import cal.barbermanager.model.User;
import cal.barbermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UserService {

    //
    // Dependencies
    //

    private final UserRepository userRepository;


    //
    // Constructors
    //
    @Autowired
    public UserService(UserRepository userRepository){

        this.userRepository = userRepository;
    }

    //
    // Services
    //

    public List<User> getListEmployer(){

        List<User> listUser = userRepository.findAllByType("EMPLOYER");

        return listUser;
    }


}
