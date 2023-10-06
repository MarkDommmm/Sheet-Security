package hh.model.dto.request;

import hh.model.entity.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleRequest {

    private RoleName roleName;

    private String description;


}
