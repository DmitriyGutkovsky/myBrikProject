package by.mybrik.repository.impl;

import by.mybrik.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepository extends JpaRepository<Role, Long> {

  @Modifying
  @Transactional
  @Query(
      value = "delete from m_roles where user_id = :userId and role_name = :roleName",
      nativeQuery = true)
  int deleteQuery(@Param("userId") Long userId, @Param("roleName") String roles);
}
