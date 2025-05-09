package spring.vue.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        /topic/1 형태로 메세지를 수신(subscribe) 해야 함을 설정.
        registry.enableSimpleBroker("/room", "/ws");
//        /app로 시작하는 url패턴으로 메시지가 발행 되면 @controller 객체의 @MessageMapping메서드로 라우팅
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
        registry.addEndpoint("/ws")
                .setHandshakeHandler(new CusetomHandshakeHandler())
                .setAllowedOriginPatterns("*").withSockJS();
    }
}
