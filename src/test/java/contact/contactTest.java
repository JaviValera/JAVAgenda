package contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class contactTest {
    contact contact;
    private int id = 1;
    private String name = "testname";
    private String gender = "testgender";
    private int phoneNumber = 1;
    @BeforeEach
    void setUp() {
    contact = new contact(id,name,gender,phoneNumber);
    }

    @Test
    void getId() {
    assertEquals(id,contact.getId());
    }

    @Test
    void setId() {
    id=2;
    contact.setId(id);
    assertEquals(id,contact.getId());
    }

    @Test
    void getName() {
    assertEquals(name,contact.getName());
    }

    @Test
    void setName() {
    name= "nametest";
    contact.setName(name);
    assertEquals(name,contact.getName());
    }

    @Test
    void getGender() {
    assertEquals(gender,contact.getGender());
    }

    @Test
    void setGender() {
    gender = "gendertest";
    contact.setGender(gender);
    assertEquals(gender,contact.getGender());
    }

    @Test
    void getPhoneNumber() {
        assertEquals(phoneNumber,contact.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        phoneNumber = 2;
        contact.setPhoneNumber(phoneNumber);
        assertEquals(phoneNumber,contact.getPhoneNumber());
    }

}