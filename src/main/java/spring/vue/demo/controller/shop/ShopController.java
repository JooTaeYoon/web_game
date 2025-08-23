//package spring.vue.demo.controller.shop;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import spring.vue.demo.dto.shop.ClothesDto;
//import spring.vue.demo.dto.shop.CustomerDto;
//import spring.vue.demo.dto.shop.LoginDto;
//import spring.vue.demo.entity.shop.Clothes;
//import spring.vue.demo.entity.shop.Customer;
//import spring.vue.demo.entity.shop.Login;
//import spring.vue.demo.service.shop.ShopService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/shop/api")
//@RequiredArgsConstructor
//@Slf4j
//public class ShopController {
//
//    private final ShopService shopService;
//
//    //    로그인
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
//        Login login = shopService.login(loginDto);
//        log.info("save {}", login);
//        return ResponseEntity.status(HttpStatus.OK).body(login);
//    }
//
//    //    손님 등록
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody CustomerDto customerDto) {
//        Customer register = shopService.register(customerDto);
//        return ResponseEntity.status(HttpStatus.OK).body(register);
//    }
//
//    //    옷 맡기기
//    @PostMapping("/clothes")
//    public ResponseEntity<?> clothesRegister(@RequestBody CustomerDto customerDto) {
//        Clothes clothes = shopService.clothesRegister(customerDto);
//        if (clothes != null) {
//            return ResponseEntity.status(HttpStatus.OK).body(clothes);
//        }
//        return ResponseEntity.status(HttpStatus.OK).body("no save");
//    }
//
//    //    손님이 옷 찾아 감 체크 (retrieved 컬럼 업데이트)
//    @PostMapping("/retrievedClothes/{customerId}")
//    public ResponseEntity<?> retrievedClothes(@PathVariable Long customerId, @RequestBody ClothesDto clothesDto) {
//        Clothes clothes = shopService.retrievedClothes(customerId, clothesDto);
//        if (clothes != null) return ResponseEntity.status(HttpStatus.OK).body(clothes);
//        else return ResponseEntity.status(HttpStatus.OK).body("맡긴 옷이 없음");
//    }
//
//    //    모든 손님 보여주기
//    @GetMapping("/findAll")
//    public ResponseEntity<?> findAll() {
//        List<Customer> all = shopService.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(all);
//    }
//
//    @PostMapping("/placeClothes/{id}")
//    public ResponseEntity<?> placeClothes(@RequestBody ClothesDto clothesDto, @PathVariable Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body("우선skip");
//    }
//
//    //    손님이 그동안 맡겼던 옷 정보 가져오기
//    @GetMapping("/{id}")
//    public ResponseEntity<?> findCustomerClothes(@PathVariable Long id) {
//        List<Clothes> customerClothes = shopService.findCustomerClothes(id);
//        return ResponseEntity.status(HttpStatus.OK).body(customerClothes);
//    }
//}
