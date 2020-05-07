package com.switchfully.order;

import com.switchfully.order.domain.customers.Customer;
import com.switchfully.order.domain.customers.addresses.Address;
import com.switchfully.order.domain.customers.emails.Email;
import com.switchfully.order.domain.customers.phonenumbers.PhoneNumber;
import com.switchfully.order.domain.items.Item;
import com.switchfully.order.domain.items.prices.Price;
import com.switchfully.order.service.customers.CustomerService;
import com.switchfully.order.service.items.ItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class Application implements CommandLineRunner {

    ItemService itemService;
    CustomerService customerService;

    public Application(ItemService itemService, CustomerService customerService) {
        this.itemService = itemService;
        this.customerService = customerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        itemService.createItem((new Item(new Item.ItemBuilder().withAmountOfStock(10)
                .withDescription("cheese1")
                .withName("camen")
                .withPrice(new Price(new BigDecimal(10))))));
        itemService.createItem((new Item(new Item.ItemBuilder().withAmountOfStock(10)
                .withDescription("cheese2")
                .withName("cofef")
                .withPrice(new Price(new BigDecimal(10))))));
        itemService.createItem((new Item(new Item.ItemBuilder().withAmountOfStock(10)
                .withDescription("cheese3")
                .withName("roche")
                .withPrice(new Price(new BigDecimal(10))))));
        itemService.createItem((new Item(new Item.ItemBuilder().withAmountOfStock(10)
                .withDescription("cheese4")
                .withName("fort")
                .withPrice(new Price(new BigDecimal(10))))));
        itemService.createItem((new Item(new Item.ItemBuilder().withAmountOfStock(10)
                .withDescription("cheese5")
                .withName("oude")
                .withPrice(new Price(new BigDecimal(10))))));




        customerService.createCustomer(new Customer.CustomerBuilder()
                .withFirstname("dries")
                .withLastname("b")
                .withEmail(Email.EmailBuilder.email().withComplete("qdsf").withDomain("dqflj").withLocalPart("qdfs").build())
                .withPhoneNumber(PhoneNumber.PhoneNumberBuilder.phoneNumber().withCountryCallingCode("qfsd").withNumber("qqsdf").build())
                .withAddress(new Address(Address.AddressBuilder.address().withCountry("belgium").withHouseNumber("five").withPostalCode("3596").withStreetName("kerkstraat")))
                .build());


        customerService.createCustomer(new Customer(new Customer.CustomerBuilder()
                .withFirstname("bart")
                .withLastname("a")
                .withEmail(Email.EmailBuilder.email().withComplete("qdsf").withDomain("dqflj").withLocalPart("qdfs").build())
                .withPhoneNumber(PhoneNumber.PhoneNumberBuilder.phoneNumber().withCountryCallingCode("qfsd").withNumber("qqsdf").build())
                .withAddress(new Address(Address.AddressBuilder.address().withCountry("belgium").withHouseNumber("five").withPostalCode("3596").withStreetName("kerkstraat")))
        ));

        customerService.createCustomer(new Customer(new Customer.CustomerBuilder()
                .withFirstname("kurt")
                .withLastname("c")
                .withEmail(Email.EmailBuilder.email().withComplete("qdsf").withDomain("dqflj").withLocalPart("qdfs").build())
                .withPhoneNumber(PhoneNumber.PhoneNumberBuilder.phoneNumber().withCountryCallingCode("qfsd").withNumber("qqsdf").build())
                .withAddress(new Address(Address.AddressBuilder.address().withCountry("belgium").withHouseNumber("five").withPostalCode("3596").withStreetName("kerkstraat")))

        ));
        customerService.createCustomer(new Customer(new Customer.CustomerBuilder()
                .withFirstname("kris")
                .withLastname("e")
                .withEmail(Email.EmailBuilder.email().withComplete("qdsf").withDomain("dqflj").withLocalPart("qdfs").build())
                .withPhoneNumber(PhoneNumber.PhoneNumberBuilder.phoneNumber().withCountryCallingCode("qfsd").withNumber("qqsdf").build())
                .withAddress(new Address(Address.AddressBuilder.address().withCountry("belgium").withHouseNumber("five").withPostalCode("3596").withStreetName("kerkstraat")))

        ));
    }
}
