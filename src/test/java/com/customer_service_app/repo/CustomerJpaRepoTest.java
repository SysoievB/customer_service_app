package com.customer_service_app.repo;

import com.customer_service_app.entity.Customer;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@Sql({"/sql/create.sql"})//, "/sql/populate.sql"
class CustomerJpaRepoTest {
    private final static String COUNTRY = "USA";

    @Autowired
    TestEntityManager manager;
    @Autowired
    CustomerJpaRepo repo;

    @Test
    void returns_all_by_country() {
        //given
       manager.persist(getCustomer());
       manager.persist(getCustomer());
       manager.persist(getCustomer());

        //when
        val result = repo.findAllByCountry(COUNTRY);

        //then
        assertThat(result).hasSize(3);
    }

    private Customer getCustomer() {
        return new Customer("Ivan", "Ivanov", LocalDate.now(), COUNTRY);
    }
}