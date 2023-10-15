package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {
    private AddressBookRepository addressBookRepository;
    private BuddyInfoRepository buddyInfoRepository;

    @Autowired
    public WebController(AddressBookRepository addressBookRepository, BuddyInfoRepository buddyInfoRepository) {
        this.addressBookRepository = addressBookRepository;
        this.buddyInfoRepository = buddyInfoRepository;
    }

    @GetMapping("/start")
    public String start(Model model) {
        model.addAttribute("addressBook", new AddressBook());
        model.addAttribute("buddy", new BuddyInfo());
        return "start";
    }

    @PostMapping("/buddyAdd")
    public String buddySubmit(@ModelAttribute("buddy") BuddyInfo buddyInfo) {
        AddressBook addressBook = addressBookRepository.findById(1);
        addressBook.addBuddyInfo(buddyInfo);
        buddyInfoRepository.save(buddyInfo);
        addressBookRepository.save(addressBook);
        return "buddy";
    }

    @PostMapping("/addressBookCreate")
    public String addressBookSubmit(Model model) {
        if (addressBookRepository.findById(1) == null) {
            addressBookRepository.save(new AddressBook());
        }

        AddressBook addressBook = addressBookRepository.findById(1);
        model.addAttribute("addressBook", addressBook);
        return "addressBook";
    }
}
