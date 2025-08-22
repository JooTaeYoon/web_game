package spring.vue.demo.controller.flight;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.vue.demo.dto.flight.FlightDto;
import spring.vue.demo.service.flight.FlightService;

@RestController
@RequestMapping("/flight/api")
@Slf4j
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/reserve")
    public ResponseEntity<?> reserve(@RequestBody FlightDto flightDto) {
        flightService.reserveSeat(flightDto);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
