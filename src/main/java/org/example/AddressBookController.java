package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressBookController {
    private AddressBookRepository addressBookRepository;

    @Autowired
    public AddressBookController(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    @RequestMapping("/addressBook")
    public AddressBook createAddressBook() {
        AddressBook addressBook = new AddressBook();
        addressBookRepository.save(addressBook);
        return addressBook;
    }

    @RequestMapping(value = "/addressBook", method = RequestMethod.GET)
    public AddressBook getAddressBook(@RequestParam("id") int id) {
        return addressBookRepository.findById(id);
    }
}
