package hh.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeRequest {
    //    private Long id;
    @NotBlank(message = "Employee name cannot be blank")
    @NotEmpty(message = "Employee name cannot be empty!!!")
    @Size(min = 6, message = "Employee name must be at least 6 characters")
    private String username;
    private String name;
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email invalidate")
    private String email;

    @NotBlank(message = "Employee password cannot be blank")
    @NotEmpty(message = "Employee password cannot be empty!!!")
    @Size(min = 6, message = "Employee password must be at least 6 characters")
    private String password;

    private Long department;
    private Set<Long> role;
    private boolean status;
}
