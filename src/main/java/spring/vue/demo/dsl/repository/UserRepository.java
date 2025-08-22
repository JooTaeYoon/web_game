package spring.vue.demo.dsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.vue.demo.dsl.entity.SiteUser;

public interface UserRepository extends JpaRepository<SiteUser, Long> , UserRepositoryCustom {
}
