//package spring.vue.demo.entity.shop;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.sql.Timestamp;
//
//@Entity
//@Table(name = "login")
//@Getter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class Login {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "password")
//    @JsonIgnore
//    private String password;
//
//    @Column(name = "create_at", insertable = false, updatable = false)
//    @CreationTimestamp
//    private Timestamp createAt;
//
//    @Override
//    public String toString() {
//        return "Login id => " + id + ", name => " + name;
//    }
//}
