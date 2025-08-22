package spring.vue.demo.service.shop;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.vue.demo.dto.shop.ClothesDto;
import spring.vue.demo.dto.shop.CustomerDto;
import spring.vue.demo.dto.shop.LoginDto;
import spring.vue.demo.entity.shop.Clothes;
import spring.vue.demo.entity.shop.Customer;
import spring.vue.demo.entity.shop.Login;
import spring.vue.demo.repository.shop.ClothesRepository;
import spring.vue.demo.repository.shop.CustomerRepository;
import spring.vue.demo.repository.shop.LoginRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ShopService {

    private final LoginRepository loginRepository;
    private final CustomerRepository customerRepository;
    private final ClothesRepository clothesRepository;

    public ShopService(LoginRepository loginRepository, CustomerRepository customerRepository, ClothesRepository clothesRepository) {
        this.loginRepository = loginRepository;
        this.customerRepository = customerRepository;
        this.clothesRepository = clothesRepository;
    }


    public Login login(LoginDto loginDto) {
        String name = loginDto.getName();
        log.info("name: {}", name);
        Login findId = loginRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("아이디가 없습니다"));
        log.info("findId : {}", findId);
        return findId;
    }

    public Customer register(CustomerDto customerDto) {
        if (StringUtils.isEmpty(customerDto.getName())) {
            log.info("No Name: {} ", customerDto.getName());
            return (Customer.builder().
                    name("noName").
                    build());
        }
        if (StringUtils.isEmpty(customerDto.getPhoneNumber())) {
            log.info("No PhoneNumber : {}", customerDto.getPhoneNumber());
            customerDto.setPhoneNumber("No PhoneNumber");
        }
        Customer customer = Customer.builder().name(customerDto.getName()).phoneNumber(customerDto.getPhoneNumber()).build();
        Customer save = customerRepository.save(customer);
        return save;
    }

    public Clothes clothesRegister(CustomerDto customerDto) {
        String customerName = customerDto.getName();
        List<Customer> byName = customerRepository.findByName(customerName);
        for (int i = 0; i < byName.size(); i++) {
//            동명이인 일 경우 phone번호로 확인 해야 함
            if (customerDto.getPhoneNumber().equals(byName.get(i).getPhoneNumber())) {
                Clothes clothes = Clothes.builder()
                        .customerId(byName.get(i))
                        .locationId(null)
                        .issue(customerDto.getIssue())
                        .type(customerDto.getType())
                        .build();

                Clothes save = clothesRepository.save(clothes);
                return save;
            }
        }
        return null;
    }

    public List<Customer> findAll() {
        List<Customer> all = customerRepository.findAll();
        return all;
    }

    public List<Clothes> findCustomerClothes(Long id) {
        List<Clothes> findCustomerClothes = clothesRepository.findByCustomerId_Id(id);
        log.info("findCustomerClothes: {}", findCustomerClothes);
        return findCustomerClothes;
    }

    public Clothes retrievedClothes(Long id, ClothesDto clothesDto) {
        String comment = clothesDto.getComment();
        Clothes build = Clothes.builder()
                .retrieved(true)
                .comment(comment).
                build();
        Clothes clothes = clothesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("옷을 찾을 수 없습니다. id: " + id));
        Clothes save = clothesRepository.save(clothes);
        log.info("save : {}", save);
        return save;
    }
}
