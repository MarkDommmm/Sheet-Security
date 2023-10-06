package hh.Repository;

import hh.model.entity.Department;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepo extends JpaRepository<Department,Long> {
    Page<Department> findAllByNameContaining(String fullName, Pageable pageable);
}
