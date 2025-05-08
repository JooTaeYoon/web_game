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

import java.util.Map;

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
    public void coordinate(@RequestBody OmokDto omokDto,SimpMessageHeaderAccessor accessor) {
        log.info("{} ", omokDto);
        log.info("{} ", accessor);

    }

    @MessageMapping("/api/init")
    public void init(SimpMessageHeaderAccessor accessor) {
        SettingDto settingDto = new SettingDto();
        String sessionId = accessor.getSessionId();
        settingDto.setSessionId(sessionId);
        Map<String , String> map = sessionManager.isFirst(sessionId);

        simpMessagingTemplate.convertAndSend("/room/api/subscribe", map);

        log.info("sessionId : {}", map);
    }

}
