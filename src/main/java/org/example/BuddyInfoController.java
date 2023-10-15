package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BuddyInfoController {
    private BuddyInfoRepository buddyInfoRepository;
    private AddressBookRepository addressBookRepository;

    @Autowired
    public BuddyInfoController(BuddyInfoRepository buddyInfoRepository, AddressBookRepository addressBookRepository) {
        this.buddyInfoRepository = buddyInfoRepository;
        this.addressBookRepository = addressBookRepository;
    }

    @RequestMapping(value = "/buddyInfo", method = RequestMethod.GET)
    public BuddyInfo getBuddy(@RequestParam("bookId") int id, @RequestParam("buddyId") int bId) {
        AddressBook addressBook = addressBookRepository.findById(id);
        return addressBook.getBuddy(bId - 1);
    }

    @RequestMapping(value = "/buddyInfo", method = RequestMethod.POST)
    public BuddyInfo addBuddy(@RequestBody BuddyInfo buddyInfo, @RequestParam("bookId") int id) {
        AddressBook addressBook = addressBookRepository.findById(id);
        addressBook.addBuddyInfo(buddyInfo);
        buddyInfoRepository.save(buddyInfo);
        addressBookRepository.save(addressBook);
        return buddyInfo;
    }

    @RequestMapping(value = "/buddyInfo", method = RequestMethod.DELETE)
    public BuddyInfo removeBuddy(@RequestParam("bookId") int id, @RequestParam("buddyId") int bId) {
        AddressBook addressBook = addressBookRepository.findById(id);
        BuddyInfo buddyInfo = addressBook.getBuddy(bId - 1);
        addressBook.removeBuddyInfo(bId - 1);
        addressBookRepository.save(addressBook);
        buddyInfoRepository.delete(buddyInfo);
        return buddyInfo;
    }
}
