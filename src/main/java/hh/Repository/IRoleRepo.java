package hh.Repository;


import hh.model.entity.Role;
import hh.model.entity.RoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface IRoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(RoleName roleName);
    Set<Role> findAllByIdIn(Set<Long> ids);
    Page<Role> findAllByroleNameContaining(String fullName, Pageable pageable);
}
