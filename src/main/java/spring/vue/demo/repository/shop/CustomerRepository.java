package spring.vue.demo.repository.shop;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.vue.demo.entity.shop.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByName(String customerName);

}
