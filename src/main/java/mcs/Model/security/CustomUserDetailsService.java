package mcs.Model.security;

import mcs.Model.Repositories.UserRepository;
import mcs.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

//    @Autowired
//    private WebApplicationContext applicationContext123;
    @Autowired
    private UserRepository userRepository;

//    @PostConstruct
//    void completeSetUp() {
//        userRepository = applicationContext.getBean(UserRepository.class);
//    }

    public CustomUserDetailsService() {
        super();
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final User appUser = userRepository.findByEmail(email);
        if (appUser == null) {
            throw new UsernameNotFoundException(email);
        }
        return new AppUserPrincipal(appUser);
    }
}
