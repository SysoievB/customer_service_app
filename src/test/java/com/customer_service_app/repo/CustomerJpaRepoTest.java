package com.customer_service_app.repo;

import com.customer_service_app.entity.Customer;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@SqlGroup({
        @Sql(scripts = {"/sql/create.sql", "/sql/populate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS),
        @Sql(statements = {"drop table customer;"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
})
class CustomerJpaRepoTest {
    private final static String COUNTRY = "USA";

    @Autowired
    TestEntityManager manager;
    @Autowired
    CustomerJpaRepo repo;

    @Test
    void returns_all_by_country() {
        //when
        val result = repo.findAllByCountry(COUNTRY);

        //then
        assertThat(result).hasSize(3);
    }

    @Test
    void save_new_customer() {
        //given
        val customer = getCustomer();
        manager.persist(customer);
        manager.flush();

        //when
        val result = repo.save(customer);
        manager.clear();

        //then
        assertThat(result)
                .returns("Ivan", Customer::getName)
                .returns("Ivanov", Customer::getSurname)
                .returns(LocalDate.now(), Customer::getBirthDate)
                .returns(COUNTRY, Customer::getCountry);
    }

    private Customer getCustomer() {
        return new Customer("Ivan", "Ivanov", LocalDate.now(), COUNTRY);
    }
}