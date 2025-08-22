package spring.vue.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
    private int id;
    private String title;
    private String writer;
    private String content;
    private Date create_at;
}
