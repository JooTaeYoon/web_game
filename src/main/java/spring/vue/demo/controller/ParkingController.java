package spring.vue.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.vue.demo.dto.ParkingDto;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/parking")
@Slf4j
public class ParkingController {

    @PostMapping("/init")
    public ResponseEntity<?> init(@RequestBody ParkingDto parkingDto){
        log.info("parkingDto : {}",parkingDto);

        LocalDateTime enterTime= LocalDateTime.now();
        ParkingDto.ResponseDto response = new ParkingDto.ResponseDto();
        response.setEnterTime(enterTime);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
