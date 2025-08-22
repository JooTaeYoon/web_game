package spring.vue.demo.dsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import spring.vue.demo.dsl.entity.QSiteUser;
import spring.vue.demo.dsl.entity.SiteUser;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public SiteUser getQslUser(Long id) {

        QSiteUser siteUser = QSiteUser.siteUser;

        return jpaQueryFactory.selectFrom(siteUser).where(siteUser.id.eq(id))
                .fetchOne();
    }
}
