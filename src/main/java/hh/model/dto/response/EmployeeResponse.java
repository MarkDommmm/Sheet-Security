package hh.model.dto.response;

import hh.model.entity.Department;
import hh.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Department department;
    private Set<Role> role;
    private boolean status;
}
