package spring.vue.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.vue.demo.config.SessionManager;
import spring.vue.demo.dto.OmokDto;
import spring.vue.demo.dto.SettingDto;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
public class OmokSocketController {

    private final SessionManager sessionManager;
    private final SimpMessagingTemplate simpMessagingTemplate;


    public OmokSocketController(SessionManager sessionManager, SimpMessagingTemplate simpMessagingTemplate) {
        this.sessionManager = sessionManager;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @MessageMapping("/api/coordinate")
    public void coordinate(@RequestBody OmokDto omokDto, SimpMessageHeaderAccessor accessor) {
        sessionManager.switchTurn();

        // 턴 정보 추가해서 전달
        omokDto.setTurn(sessionManager.getCurrentTurn());
//        simpMessagingTemplate.convertAndSend("/room/api/gaming", omokDto);
        simpMessagingTemplate.convertAndSend("/room/api/gaming", omokDto);
    }

    @MessageMapping("/api/init")
    public void init(SimpMessageHeaderAccessor accessor, Principal principal) {
        String sessionId = principal.getName();
//        String sessionId = UUID.randomUUID().toString();
//        String sessionId = accessor.getSessionId();
        Map<String, String> map = sessionManager.isFirst(sessionId);
//        simpMessagingTemplate.convertAndSend("/room/api/init", map);
        simpMessagingTemplate.convertAndSendToUser(sessionId, "/room/api/init", map);
    }
}
