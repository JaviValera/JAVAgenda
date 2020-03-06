package agenda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import contact.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.apache.commons.io.FileUtils.contentEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.io.FileUtils;

class agendaTest {
    agenda agenda;
    File filePerdb,fileProdb,filePerTxt,fileProTxt;
    personalContact per1;
    personalContact per2;
    professionalContact pro1,pro2;
    ArrayList<contact> contacts;

    @BeforeEach
    void setUp() {
        agenda = new agenda();
        filePerdb = new File("src/test/personalContactTest.db");
        fileProdb = new File("src/test/professionalContactTest.db");
        filePerTxt = new File("src/test/personalContactsTest.txt");
        fileProTxt = new File("src/test/professionalContactsTest.txt");
        contacts = new ArrayList<contact>();
        per1 = new personalContact(1,"name1","Female",123,"01/01/1991");
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
        assertEquals(pro2, agenda.searchContact(pro2));
    }

    @Test
    void addPersonalContact() {
        per2 = new personalContact(4,"name4","Other",123456,"01/01/1991");
       agenda.addPersonalContact(per2);
        assertEquals(per2, agenda.searchContact(per2));
    }

    @Test
    void modify(){
        agenda.modify(per1,1,"99");
        assertEquals(99,per1.getId());
        agenda.modify(per1,2,"NameTest");
        assertEquals("NameTest",per1.getName());
        agenda.modify(per1,3,"Gender");
        assertEquals("Gender",per1.getGender());
        agenda.modify(per1,4,"1234567890");
        assertEquals(1234567890,per1.getPhoneNumber());
    }

    @Test
    void removeContact() {
        assertTrue(agenda.removeContact(per1));
        assertNull(agenda.searchContact(per1));
    }

    @Test
    void wipeContacts(){
        assertNotEquals(0, agenda.getContacts().size());
        agenda.wipeContacts();
        contacts.clear();
        assertEquals(contacts, agenda.getContacts());
    }

    @Test
    void wipe() throws IOException {
        assertNotEquals(0, agenda.getContacts().size());
        agenda.wipe();
        contacts.clear();
        assertEquals(contacts, agenda.getContacts());
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
    void searchContactSuccess() {
        assertEquals(pro1, agenda.searchContact(pro1));
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

    @Test
    void findfreeid() {
        assertEquals(3,agenda.findfreeid());
    }

    @Test
    void mergeAgenda() {
        agenda agenda2 = new agenda();
        per2 = new personalContact(3,"name1","Female",123,"01/01/1991");
        pro2 = new professionalContact(4,"name2","Other",1234,"mail@mail.com");
        agenda2.addPersonalContact(per2);
        agenda2.addProfessionalContact(pro2);
        agenda.mergeAgenda(agenda2);
        assertEquals(5,agenda.findfreeid());
    }
}