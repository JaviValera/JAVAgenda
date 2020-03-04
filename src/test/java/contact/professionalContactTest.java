package contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class professionalContactTest {
    professionalContact professional;
    private int id;
    private String name;
    private String gender;
    private int phoneNumber;
    private String email;
    @BeforeEach
    void setUp() {
    id=1;
    name="testname";
    gender="testgender";
    phoneNumber=1;
    email="testemail";
    professional = new professionalContact(id,name,gender,phoneNumber,email);
    }
    @Test
    void getEmail() {
        assertEquals(email,professional.getEmail());
    }
    @Test
    void setEmail() {
        email="emailtest";
        professional.setEmail(email);
        assertEquals("emailtest",professional.getEmail());
    }
}