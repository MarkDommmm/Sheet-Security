package hh.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DepartmentResponse {

    private Long id;
    private String name;
    private String description;

}
