import agenda.agenda;
import contact.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class main {
    public static void main(String[] args) throws ParseException, IOException {
        agenda agenda = new agenda();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        personalContact per1 = new personalContact(1,"Mikel","Male",987654321, sdf.parse("06/11/1996"));
        personalContact per3 = new personalContact(2,"James","Female",12345678, sdf.parse("15/07/1990"));
        personalContact per2 = new personalContact(3,"Tyler","Other",12341234, sdf.parse("24/02/2000"));
        professionalContact pro1 = new professionalContact(4,"Xerxe","Male",70707070, "xerxe@og.com");
        professionalContact pro2 = new professionalContact(5,"Caps","Female",80808080, "xerxe@og.com");
        professionalContact pro3 = new professionalContact(6,"Denyk","Other",90909090, "xerxe@og.com");
        agenda.addPersonalContact(per1);
        agenda.addPersonalContact(per2);
        agenda.addPersonalContact(per3);
        agenda.addProfessionalContact(pro1);
        agenda.addProfessionalContact(pro2);
        agenda.addProfessionalContact(pro3);
        agenda.savePersonalContact();
        agenda.saveProfessionalContact();
        agenda.generatePersonalContactsFile();
        agenda.generateProfessionalContactsFile();
        agenda.searchContact(pro1).toString();
        agenda.wipe();
    }
}
