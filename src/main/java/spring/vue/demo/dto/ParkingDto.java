package spring.vue.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDto {

    private String carNumber;

    private String carModel;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public static class ResponseDto{
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH시 mm분 ss초", timezone = "Asia/Seoul")
        private LocalDateTime enterTime;
    }

}
