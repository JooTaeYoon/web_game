//package spring.vue.demo.entity.flight;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.sql.Timestamp;
//
//@Entity
//@Builder
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//public class SeatEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "seat_number")
//    private String seatNumber;
//
//    @Column(name = "create_at", insertable = false, updatable = false)
//    @CreationTimestamp
//    private Timestamp createAt;
//
//    private boolean reserved;
//
//    @Version
//    private Long version;
//
//}
