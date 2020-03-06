import agenda.agenda;
import contact.*;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        agenda agenda = new agenda();
        agenda agenda1= new agenda();
        personalContact per1 = new personalContact(1,"Mikel","Male",987654321, "06/11/1996");
        personalContact per3 = new personalContact(2,"James","Female",12345678, "15/07/1990");
        personalContact per2 = new personalContact(3,"Tyler","Other",12341234, "24/02/2000");
        professionalContact pro1 = new professionalContact(4,"Xerxe","Male",70707070, "xerxe@og.pro");
        professionalContact pro2 = new professionalContact(5,"Caps","Female",80808080, "caps@g2.pro");
        professionalContact pro3 = new professionalContact(6,"Denyk","Other",90909090, "denyk@misfits.pro");
        agenda.addPersonalContact(per1);
        agenda.addPersonalContact(per2);
        agenda1.addPersonalContact(per3);
        agenda.addProfessionalContact(pro1);
        agenda.addProfessionalContact(pro2);
        agenda1.addProfessionalContact(pro3);
        agenda.savePersonalContact();
        agenda.saveProfessionalContact();
        agenda.generatePersonalContactsFile();
        agenda.generateProfessionalContactsFile();
        System.out.println(agenda.searchContact(pro1).toString());
        agenda.modify(pro1,1,"99");
        System.out.println(agenda.searchContact(pro1).toString());
        agenda.modify(pro1,2,"pepe");
        System.out.println(agenda.searchContact(pro1).toString());
        agenda.modify(pro1,3,"tanque apache");
        System.out.println(agenda.searchContact(pro1).toString());
        agenda.modify(pro1,4,"112");
        System.out.println(agenda.searchContact(pro1).toString());
        agenda.modify(pro1,0,"pepe");
        System.out.println(agenda.searchContact(pro1).toString());
        agenda.mergeAgenda(agenda1);
        agenda.wipe();
    }
}
