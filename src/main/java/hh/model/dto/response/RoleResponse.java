package hh.model.dto.response;

import hh.model.entity.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleResponse {

    private Long id;
    private RoleName roleName;

    private String description;


}
