package hh.model.domain;

 import hh.model.entity.Employee;
 import hh.service.iml.IEmployeeServiceIml;
 import lombok.AllArgsConstructor;
import lombok.Data;

 import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 import org.springframework.stereotype.Service;



@AllArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final IEmployeeServiceIml employeeServiceIml;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeServiceIml.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username Not Found"));
        return UserPrinciple.build(employee);
    }
}
