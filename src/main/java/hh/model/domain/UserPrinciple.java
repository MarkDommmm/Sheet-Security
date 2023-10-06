package hh.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hh.model.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrinciple implements UserDetails {
    private Long id;
    private String name;
    private String email;

    private String username;
    @JsonIgnore
    private String password;
    private boolean status;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrinciple build(Employee employee){
        List<GrantedAuthority> list =  employee.getRole().stream().map(
                role-> new SimpleGrantedAuthority(role.getRoleName().name())
        ).collect(Collectors.toList());
        return UserPrinciple.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .username(employee.getUsername())
                .status(employee.isStatus())
                .password(employee.getPassword())
                .authorities(list).build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}