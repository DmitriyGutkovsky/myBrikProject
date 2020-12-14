package by.mybrik.repository.impl;

import by.mybrik.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
//    List<Role> findUserRoles(Long userId);
}
