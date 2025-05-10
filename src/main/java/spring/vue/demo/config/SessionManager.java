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
    private final Map<String, String> turnMap = new ConcurrentHashMap<>(); // sessionId -> color
    private final Map<String, String> colorMap = new ConcurrentHashMap<>(); // sessionId -> color


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

    public Map<String, String> isFirst(String uuid) {
        Map<String, String> map = new HashMap<>();

        if (sessionList.contains(uuid)) {
            sessionList.remove(uuid);
        }

        sessionList.add(uuid);

        String color = sessionList.indexOf(uuid) % 2 == 0 ? "black" : "white";

        log.info("color => {}", color);

        if (!turnMap.containsKey("currentTurn")) {
            turnMap.put("currentTurn", "black"); // 처음엔 항상 black부터
        }

        map.put("color", color);
        map.put("turn", turnMap.get("currentTurn"));
        map.put("sessionId", uuid);

        log.info("MAP : {}", map);
        return map;
    }

    public void switchTurn() {
        String current = turnMap.get("currentTurn");
        String next = current.equals("black") ? "white" : "black";

        turnMap.put("currentTurn", next);
    }

    public String getCurrentTurn() {
        return turnMap.get("currentTurn");
    }

    public String getAllExcept(String sessionId) {
        return sessionList.stream().filter(id -> !id.equals(sessionId)).toList().toString();
    }
}
