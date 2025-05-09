package spring.vue.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OmokDto {

    private int x;
    private int y;
    private String sessionId;
    private String color;
    private String turn;
}
