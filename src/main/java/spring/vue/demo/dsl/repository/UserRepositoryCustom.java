package spring.vue.demo.dsl.repository;


import spring.vue.demo.dsl.entity.SiteUser;

public interface UserRepositoryCustom {

    SiteUser getQslUser(Long id);

}
