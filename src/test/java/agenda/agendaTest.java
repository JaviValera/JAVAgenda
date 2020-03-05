package agenda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import contact.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;

import static org.apache.commons.io.FileUtils.contentEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.io.FileUtils;

class agendaTest {
    agenda agenda;
    File filePerdb,fileProdb,filePerTxt,fileProTxt;
    personalContact per1,per2,per3;
    professionalContact pro1,pro2;
    ArrayList<contact> contacts;

    @BeforeEach
    void setUp() throws ParseException {
        agenda = new agenda();
        filePerdb = new File("src/test/personalContactTest.db");
        fileProdb = new File("src/test/professionalContactTest.db");
        filePerTxt = new File("src/test/personalContactsTest.txt");
        fileProTxt = new File("src/test/professionalContactsTest.txt");
        contacts = new ArrayList<contact>();
        per1 = new personalContact(1,"name1","Female",123,"01/01/1991");
        per3 = new personalContact(-1,"fail","Male",22222,"02/02/1992");
        pro1 = new professionalContact(2,"name2","Other",1234,"mail@mail.com");
        agenda.addPersonalContact(per1);
        agenda.addProfessionalContact(pro1);
        contacts.add(per1);
        contacts.add(pro1);
    }
    @Test
    void getContacts() {
        assertEquals(contacts,agenda.getContacts());

    }

    @Test
    void addProfessionalContact() {
        pro2 = new professionalContact(3,"name3","Male",12345,"mail@bth.com");
        agenda.addProfessionalContact(pro2);
        assertTrue(pro2.equals(agenda.searchContact(pro2)));
    }

    @Test
    void addPersonalContact() throws ParseException {
        per2 = new personalContact(4,"name4","Other",123456,"01/01/1991");
       agenda.addPersonalContact(per2);
       assertTrue(per2.equals(agenda.searchContact(per2)));
    }

    @Test
    void removeContact() {
        agenda.removeContact(per1);
        assertEquals(null,agenda.searchContact(per1));
    }

    @Test
    void wipeContacts(){
        agenda.wipeContacts();
        contacts.clear();
        assertTrue(contacts.equals(agenda.getContacts()));
    }

    @Test
    void wipe() throws IOException {
        agenda.wipe();
        contacts.clear();
        assertTrue(contacts.equals(agenda.getContacts()));
    }

    @Test
    void loadPersonalContact() throws IOException, ParseException {
        agenda.savePersonalContact();
        agenda.wipeContacts();
        contacts.remove(pro1);
        agenda.loadPersonalContact();
        assertEquals(contacts.toString(),agenda.getContacts().toString());
    }

    @Test
    void loadProfessionalContact() throws IOException {
        agenda.saveProfessionalContact();
        agenda.wipeContacts();
        contacts.remove(per1);
        agenda.loadProfessionalContact();
        assertEquals(contacts.toString(),agenda.getContacts().toString());
    }

    @Test
    void saveProfessionalContact() throws IOException {
        File aux = new File("src/database/professionalContact.db");
        agenda.saveProfessionalContact();
        assertTrue(contentEquals(aux, fileProdb));
    }

    @Test
    void savePersonalContact() throws IOException {
        File aux = new File("src/database/personalContact.db");
        agenda.savePersonalContact();
        assertTrue(contentEquals(aux,filePerdb));
    }

    @Test
    void searchContactSuccess() { assertTrue(pro1.equals(agenda.searchContact(pro1)));
    }

    @Test
    void searchContactFail() { assertFalse(null!=agenda.searchContact(per3));
    }

    @Test
    void generatePersonalContactsFile() throws IOException {
        agenda.generatePersonalContactsFile();
        File aux = new File("src/personalContacts.txt");
        agenda.savePersonalContact();
        assertTrue(FileUtils.contentEquals(aux,filePerTxt));
        wipe();
    }

    @Test
    void generateProfessionalContactsFile() throws IOException {
        agenda.generateProfessionalContactsFile();
        File aux = new File("src/professionalContacts.txt");
        agenda.saveProfessionalContact();
        assertTrue(FileUtils.contentEquals(aux,fileProTxt));
        wipe();
    }
}