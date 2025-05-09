package spring.vue.demo.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class CusetomHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        String sessionId = UUID.randomUUID().toString();
        log.info("uuid: {}",sessionId);
//        return super.determineUser(request, wsHandler, attributes);
        return ()-> sessionId;
    }
}
