package mcs.Model.Repositories;

import mcs.Model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private User.Role notKnowsUserRole;
    @BeforeAll
    void setUpPrivileges () {
        notKnowsUserRole = new User.Role("ROLE_NOT_KNOWN_USER");
        User.Role.Privilege privilege = new User.Role.Privilege("READ");
        notKnowsUserRole.addPrivilege(privilege);
    }

    @Test
    void createUser() {
        User user = new User("Damian", "Boom","randomAdress","123", "damian@wp.pl", "123");
        user.addRole(notKnowsUserRole);
        userRepository.save(user);
        User test = userRepository.findByEmail("damian@wp.pl");
        assertNotNull(test);
    }

}