package hello;

import hello.dao.ContactRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController

public class ContactsController {
    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value = "/hello/contacts{nameFilter}")


    public List<Contacts> contacts(@RequestParam(value = "nameFilter", defaultValue = "not") String name) {
        System.out.println(name);

        return sortContacts(name);
    }


    private List<Contacts> sortContacts( String regExp){
        List<Contacts> contacts = new ArrayList();
        for (Contacts contact : contactRepository.findAll()) {
            if (!contact.getName().matches(regExp)) {
                contacts.add(contact);
            }
        }
        return contacts;
    }
}





