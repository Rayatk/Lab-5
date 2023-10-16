package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = BuddyInfo.class)
    private List<BuddyInfo> list;

    public AddressBook() {
        this.list = new ArrayList<>();
    }

    public AddressBook(String name) {
        this.name = name;
        this.list = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BuddyInfo getBuddy(int index) {
        return this.list.get(index);
    }

    public List<BuddyInfo> getList() {
        return list;
    }

    public void addBuddyInfo(BuddyInfo buddyInfo) {
        if (buddyInfo != null) {
            list.add(buddyInfo);
        }
    }

    public void removeBuddyInfo(int index) {
        list.remove(index);
    }

    public static void main(String[] args) {
        AddressBook book = new AddressBook("Bikini Bottom");
        BuddyInfo buddy1 = new BuddyInfo("SpongeBob", 123456, "Bikini Bottom");
        BuddyInfo buddy2 = new BuddyInfo("Patrick", 7891011, "Bikini Bottom");
        book.addBuddyInfo(buddy1);
        book.addBuddyInfo(buddy2);
        System.out.println(book.getName() + ":");
        for (BuddyInfo buddyInfo : book.list) {
            System.out.println(buddyInfo.getName() + ": " + buddyInfo.getNumber() + ", " + buddyInfo.getAddress());
        }
    }
}
