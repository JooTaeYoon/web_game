package spring.vue.demo.controller.crawling;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequestMapping("/api")
@RestController
@Slf4j
public class CrawlingController {

    @PostMapping("/crawling")
    public ResponseEntity<?> crawling(){
        log.info("crawling START");
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> result = restTemplate.getForObject("http://localhost:3000/crawling", Map.class);
        log.info("result: {}",result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        log.info("crawling START");
        return ResponseEntity.status(HttpStatus.OK).body("hello");
    }
}
