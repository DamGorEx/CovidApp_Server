package mcs.Model.Repositories;

import mcs.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<User.Role, String> {
    User.Role findByName(String name);
}
