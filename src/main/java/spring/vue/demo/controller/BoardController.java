package spring.vue.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.vue.demo.dto.BoardDto;
import spring.vue.demo.service.BoardService;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public ResponseEntity<?> listBoard() {
        log.info("| START |");
        BoardDto boardDto = BoardDto.builder()
                .title("제목입니다야 이거 맞냐ㅋㅋㅋ").writer("글쓴이글쓴이 원투").id(1)
                .build();

        List<BoardDto> boardDtoList = List.of(boardDto);

        return ResponseEntity.status(HttpStatus.OK).body(boardDtoList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody BoardDto boardDto) {
        log.info("| API SAVE | >>> {}", boardDto);
        boardService.save(boardDto);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @GetMapping("/read")
    public ResponseEntity<?> read() {
        List<BoardDto> read = boardService.read();
        log.info("read: {}", read);
        return ResponseEntity.status(HttpStatus.OK).body(read);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        log.info("| id : {} |", id);
        BoardDto one = boardService.getOne(id);

        log.info("| getOne: {} |", one);
        if (one == null) {
            return ResponseEntity.status(201).body("noData");
        }
        return ResponseEntity.status(HttpStatus.OK).body(one);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("| delete: {} |", id);
        boardService.deleteBoard(id);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        int realId = (int)id.longValue();
        boardDto.setId(realId);
        log.info("| BEFOR: {}", boardDto);
        int update = boardService.update(boardDto);
        log.info("| AFTER : {} |",update);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
