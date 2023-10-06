package hh.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DepartmentRequest {
    private Long id;
    private String name;
    private String description;

}
