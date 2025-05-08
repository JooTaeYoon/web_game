package spring.vue.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SessionManager {

    private final Set<String> sessionIds = ConcurrentHashMap.newKeySet();
    private final Map<String, Long> sessionMap = new ConcurrentHashMap<>();
    private final List<String> sessionList = new CopyOnWriteArrayList<>();

    public void add(String sessionId) {
//        sessionIds.add(sessionId);
        if (!sessionList.contains(sessionId))
            sessionList.add(sessionId);
    }


    public void remove(String sessionId) {
//        sessionIds.remove(sessionId);
        sessionList.remove(sessionId);
    }

    /*
    public Set<String> getAllExcept(String sessionId) {
        return sessionIds.stream()
                .filter(id -> !id.equals(sessionId))
                .collect(Collectors.toSet());
    }
     */

    public Map<String, String> isFirst(String sessionId) {
         String color = sessionList.indexOf(sessionId)%2 ==0 ? "black": "white";
         log.info("sessionList : {}",sessionList.indexOf(sessionId)%2);
         log.info("listSize: {}",sessionList.size());

         Map<String, String> map = new HashMap<>();
         map.put("color", color);
         map.put("sessionId", sessionId);
         return map;
    }

    public List<String> getAllExcept(String sessionId) {
        return sessionList.stream().filter(id -> !id.equals(sessionId)).collect(Collectors.toUnmodifiableList());
    }

}
