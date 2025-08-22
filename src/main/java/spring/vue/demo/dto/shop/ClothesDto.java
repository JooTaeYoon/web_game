package spring.vue.demo.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ClothesDto {

    private String name;
    private Integer locationId;
    private Integer customerId;
    private String issue;
    private String type;

    private String comment;

}
