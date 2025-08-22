package spring.vue.demo.controller.vote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.vue.demo.config.SessionManager;
import spring.vue.demo.dto.vote.VoteDto;

@Controller
@Slf4j
public class VoteController {


    private final SessionManager sessionManager;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public VoteController(SessionManager sessionManager, SimpMessagingTemplate simpMessagingTemplate) {
        this.sessionManager = sessionManager;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/vote/api/init")
    public void init(SimpMessageHeaderAccessor accessor) {
        simpMessagingTemplate.convertAndSend("/room/vote/api","message hi!");
    }

    @MessageMapping("/vote/api/count")
    public void count(@RequestBody VoteDto voteDto) {
        log.info("vote: {}",voteDto.getCount());
        simpMessagingTemplate.convertAndSend("/room/vote/api", voteDto);
    }
}
