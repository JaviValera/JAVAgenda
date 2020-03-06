package contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class personalContactTest  {
    personalContact personal;
    private String date;
    @BeforeEach
    void setUp() throws ParseException {
        int id = 1;
        String name = "testname";
        String gender = "testgender";
        int phoneNumber = 1;
        date = "06/11/1996";
        personal = new personalContact(id, name, gender, phoneNumber,date);
    }
    @Test
    void getDate() {
        assertEquals(date,personal.getDate());
    }

    @Test
    void setDate() {
        personal.setDate(date);
        assertEquals(date,personal.getDate());
    }
}