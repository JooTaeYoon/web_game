//package spring.vue.demo.entity.shop;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.data.jpa.repository.EntityGraph;
//
//import java.sql.Timestamp;
//
//@Builder
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Clothes {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @JoinColumn(name = "customer_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Customer customerId;
//
//    @Column(name = "location_id")
//    private Integer locationId;
//
//    @Column(name = "type")
//    private String type;
//
//    @Column(name = "issue")
//    private String issue;
//
//    @Column(name = "create_at", insertable = false, updatable = false)
//    @CreationTimestamp
//    private Timestamp createAt;
//
//    @Column(name = "retrieved_at", insertable = false, updatable = false)
//    @UpdateTimestamp
//    private Timestamp retrievedAt;
//
//    @Override
//    public String toString() {
//        return "customerId => " + customerId + "type => " + type;
//    }
//
//    @Column(name = "retrieved")
//    private Boolean retrieved;
//
//    @Column(name = "comment")
//    private String comment;
//}
