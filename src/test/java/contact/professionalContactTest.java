package contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class professionalContactTest {
    professionalContact professional;
    private String email;
    @BeforeEach
    void setUp() {
        int id = 1;
        String name = "testname";
        String gender = "testgender";
        int phoneNumber = 1;
    email="testemail";
    professional = new professionalContact(id, name, gender, phoneNumber,email);
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