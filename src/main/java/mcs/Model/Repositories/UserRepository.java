package mcs.Model.Repositories;

import mcs.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, String> {

    User save (User user);
    User findByEmail (String email);
}