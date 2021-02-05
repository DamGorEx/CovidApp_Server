package mcs.Controller;

import mcs.Model.Repositories.UserRepository;
import mcs.Model.User;
import mcs.Model.dto.CredentialDTO;
import mcs.Model.dto.RoleDTO;
import mcs.Model.dto.UserDTOLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SecurityController {

    private UserRepository userRepository;

    @Autowired
    public SecurityController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public UserDTOLogin currentUserName(Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        var roleAdmmExists = user.getRoles().stream()
                .map(User.Role::getName)
                .filter(s -> s.toLowerCase()
                        .equals("admin")).count() > 0;

        CredentialDTO credentialDTO = new CredentialDTO();
        credentialDTO.setLogin(user.getEmail());
        credentialDTO.setRole(roleAdmmExists ? RoleDTO.Admin : RoleDTO.User);

        UserDTOLogin userDTOLogin = new UserDTOLogin();
        userDTOLogin.setName(user.getFName());
        userDTOLogin.setLastName(user.getLName());

        userDTOLogin.setCredentials(credentialDTO);

        return userDTOLogin;
    }
}
