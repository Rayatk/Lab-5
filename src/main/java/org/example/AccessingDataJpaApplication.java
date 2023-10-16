package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {
    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class);
    }

    @Bean
    public CommandLineRunner demo(BuddyInfoRepository buddyInfoRepository, AddressBookRepository addressBookRepository) {
        return args -> {
            BuddyInfo buddy1 = new BuddyInfo();
            buddy1.setId(1);
            buddy1.setName("SpongeBob");
            buddy1.setNumber(123456);
            buddy1.setAddress("Bikini Bottom");

            //BuddyInfo buddy2 = new BuddyInfo();
            //buddy2.setId(2);
            //buddy2.setName("Patrick");
            //buddy2.setNumber(7891011);
            //buddy2.setAddress("Bikini Bottom");

            AddressBook book = new AddressBook();
            book.setId(1);
            book.setName("Bikini Bottom");

            buddyInfoRepository.save(buddy1);
            //buddyInfoRepository.save(buddy2);

            log.info("List of Buddies:");
            log.info("----------------");
            for (BuddyInfo buddyInfo : buddyInfoRepository.findAll()) {
                log.info(buddyInfo.getId() + " | " + buddyInfo.getName() + " | " + buddyInfo.getNumber());
            }
            log.info("");

            log.info("List of Buddies by name:");
            log.info("------------------------");
            buddyInfoRepository.findByName("SpongeBob").forEach(bob -> log.info(bob.getId() + " | " + bob.getName() + " | " + bob.getNumber()));
            log.info("");

            book.addBuddyInfo(buddy1);
            //book.addBuddyInfo(buddy2);
            addressBookRepository.save(book);

            log.info("List of Address Books:");
            log.info("----------------------");
            for (AddressBook addressBook : addressBookRepository.findAll()) {
                log.info(addressBook.getId() + " | " + addressBook.getName());
            }
            log.info("");
        };
    }
}
