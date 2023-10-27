$(document).ready(function() {
   $.ajax({
       uri: "http://localhost:8080/addressBook?id=1"
   }).then(function(data) {
       if (data) {
           $('.addressbook-id').append("AddressBook ID: " + data.id);

           for (i = 0; i < data.list.length; i++) {
               $('.addressbook-contents').append("Name: " + data.list[i].name + ", ").append("Phone Number: " + data.list[i].number + ", ").append("Address: " + data.list[i].address).append("<br>");
           }
       }
   });
});