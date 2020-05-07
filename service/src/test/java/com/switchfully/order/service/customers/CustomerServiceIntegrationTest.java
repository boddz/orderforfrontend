package com.switchfully.order.service.customers;

import com.switchfully.order.IntegrationTest;
import com.switchfully.order.domain.customers.Customer;
import com.switchfully.order.domain.customers.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static com.switchfully.order.domain.customers.CustomerTestBuilder.aCustomer;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerServiceIntegrationTest extends IntegrationTest{

    @Inject
    private CustomerService customerService;

    @Inject
    private CustomerRepository customerRepository;

    @AfterEach
    void resetDatabase() {
        customerRepository.reset();
    }

    @Test
    void createCustomer() {
        Customer customerToCreate = aCustomer().build();

        Customer createdCustomer = customerService.createCustomer(customerToCreate);

        assertThat(customerRepository.get(customerToCreate.getId()))
                .isEqualToComparingFieldByField(customerToCreate)
                .isEqualToComparingFieldByField(createdCustomer);
    }

    @Test
    void getAllCustomers() {
        Customer customer1 = customerService.createCustomer(aCustomer().build());
        Customer customer2 = customerService.createCustomer(aCustomer().build());
        Customer customer3 = customerService.createCustomer(aCustomer().build());

        List<Customer> allCustomers = customerService.getAllCustomers();

        assertThat(allCustomers).containsExactlyInAnyOrder(customer1, customer2, customer3);
    }

    @Test
    void getCustomer() {
        customerService.createCustomer(aCustomer().build());
        Customer customerToFind = customerService.createCustomer(aCustomer().build());
        customerService.createCustomer(aCustomer().build());

        Customer foundCustomer = customerService.getCustomer(customerToFind.getId());

        assertThat(foundCustomer).isEqualToComparingFieldByField(customerToFind);
    }

}
