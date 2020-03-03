import agenda.agenda;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class main {
    public static void main(String[] args) throws ParseException, IOException {
        agenda agenda = new agenda();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        agenda.addPersonalContact(1,"Mikel",987654321,"Male", sdf.parse("06/11/1996"));
        agenda.addPersonalContact(2,"James",123456789,"Male", sdf.parse("15/07/1990"));
        agenda.addPersonalContact(3,"Tyler",123987465,"Male", sdf.parse("24/02/2000"));
        agenda.addProfessionalContact(4,"Xerxe",070707070,"Male", "xerxe@og.com");
        agenda.addProfessionalContact(5,"Caps",080808080,"Male", "caps@g2.com");
        agenda.addProfessionalContact(6,"Denyk",090909090,"Male", "denyk@misfits.com");
        agenda.savePersonalContact();
        agenda.saveProfessionalContact();
        agenda.generatePersonalContactsFile();
        agenda.generateProfessionalContactsFile();
        agenda.searchContact(4).toString();
    }
}
