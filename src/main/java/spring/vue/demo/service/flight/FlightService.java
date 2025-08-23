//package spring.vue.demo.service.flight;
//
//import jakarta.persistence.EntityNotFoundException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.dao.OptimisticLockingFailureException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Transactional;
//import spring.vue.demo.dto.flight.FlightDto;
//import spring.vue.demo.entity.flight.SeatEntity;
//import spring.vue.demo.repository.flight.FlightRepository;
//
//import java.util.HashMap;
//import java.util.Optional;
//
//@Service
//@Slf4j
//public class FlightService {
//
//    private final FlightRepository flightRepository;
//
//    public FlightService(FlightRepository flightRepository) {
//        this.flightRepository = flightRepository;
//    }
//
//    @Transactional(isolation = Isolation.REPEATABLE_READ)
//    public void reserveSeat(FlightDto flightDto) {
//        String seatNumber = flightDto.getSeatNumber();
//        try {
//            SeatEntity byId = flightRepository.findBySeatNumber(seatNumber);
//            if (byId == null) {
//                // 좌석 데이터 자체가 없다면 새로 생성
//                SeatEntity newSeat = SeatEntity.builder()
//                        .seatNumber(seatNumber) // 여기 너가 넘겨줄 값
//                        .reserved(true)
//                        .build();
//                flightRepository.save(newSeat);
//                return;
//            }
//
//            if (byId.isReserved()) {
//                throw new RuntimeException("이미 예약된 좌석입니다.");
//            }
//
//        } catch (OptimisticLockingFailureException e) {
//            throw new RuntimeException("Concurrent update detected, please retry");
//        }
//    }
//}
