package mcs.Model.Repositories;

import mcs.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<User.Role.Privilege, String> {

    User.Role.Privilege findByName(String name);
}
