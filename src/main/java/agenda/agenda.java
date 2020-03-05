package agenda;
import contact.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class agenda {

    private ArrayList<contact> contacts;
    private professionalContact proContact;
    private personalContact perContact;

    public agenda() {
        contacts = new ArrayList<contact>();
    }

    public ArrayList<contact> getContacts() {
        return contacts;
    }

    public boolean addProfessionalContact(professionalContact newContact) {
        boolean resul = true;
        contact c = searchContact(newContact);
        //If the contact exists as a Professional Contact, it cannot be added
        if (c != null && c instanceof professionalContact) resul = false;
        else contacts.add(newContact);
        return resul;
    }
    public boolean addPersonalContact(personalContact newContact){
        boolean resul = true;
        contact c = searchContact(newContact);
        //If the contact exists as a Personal Contact, it cannot be added
        if (c != null && c instanceof personalContact) resul = false;
        else contacts.add(newContact);
        return resul;
    }

    public boolean modify(contact contact,int value, String data){
        boolean resul=true;
        switch (value){
            case 1:
                contact.setId(Integer.parseInt(data));
                break;
            case 2:
                contact.setName(data);
                break;
            case 3:
                contact.setGender(data);
                break;
            case 4:
                contact.setPhoneNumber(Integer.parseInt(data));
                break;
            default:
                resul=false;
        }
        return resul;
    }

    public boolean removeContact(contact contact){
        boolean resul=false;
        contact c = searchContact(contact);
        if(c != null) {
            contacts.remove(c);
            resul = true;
        }
            return resul;
    }

    public void wipeContacts(){
        contacts.clear();
    }

    public void wipe() throws IOException {
        wipeContacts();
        savePersonalContact();
        saveProfessionalContact();
        File file = new File("src/personalContacts.txt");
        file.delete();
        file = new File("src/professionalContacts.txt");
        file.delete();
    }

    public void loadPersonalContact() throws IOException, ParseException {
        FileReader input = new FileReader("src/database/personalContact.db");
        BufferedReader file = new BufferedReader(input);
        String line = file.readLine();

        //load info

        while (line != null) {
            String data[] = line.split("-");
            int id = Integer.parseInt(data[0]);
            String name = data[1];
            String gender = data[2];
            int phoneNumber = Integer.parseInt(data[3]);
            String date = data[4];
            perContact = new personalContact(id, name, gender, phoneNumber, date);
            contacts.add(perContact);



            line = file.readLine();
        }
    }

    public void loadProfessionalContact() throws IOException {
        FileReader input = new FileReader("src/database/professionalContact.db");
        BufferedReader file = new BufferedReader(input);
        String line = file.readLine();

        //load info

        while (line != null) {
            String data[] = line.split("-");
            int id = Integer.parseInt(data[0]);
            String name = data[1];
            String gender = data[2];
            int phoneNumber = Integer.parseInt(data[3]);
            String email = data[4];


            proContact = new professionalContact(id, name, gender, phoneNumber, email);
            contacts.add(proContact);


            line = file.readLine();
        }
    }

    public void saveProfessionalContact() throws IOException {
        FileWriter file = new FileWriter("src/database/professionalContact.db");
        for (contact contact : contacts) {
            if (contact instanceof professionalContact) {
                proContact= (professionalContact) contact;

                file.write(
                        proContact.getId() + "-"
                                + proContact.getName() + "-"
                                + proContact.getGender() + "-"
                                + proContact.getPhoneNumber() + "-"
                                + proContact.getEmail() + "\n");
            }



        }
        file.close();
    }

    public void savePersonalContact() throws IOException {
        FileWriter file = new FileWriter("src/database/personalContact.db");
        for (contact contact : contacts) {
            if (contact instanceof personalContact) {
                perContact= (personalContact) contact;

                file.write(
                        perContact.getId() + "-"
                                + perContact.getName() + "-"
                                + perContact.getGender() + "-"
                                + perContact.getPhoneNumber() + "-"
                                + perContact.getDate() + "\n");
            }
        }
        file.close();
    }

    public contact searchContact(contact contact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == contact.getId()) {
                return contacts.get(i);
            }
        }
        return null;
    }

    public void generatePersonalContactsFile() throws IOException {
        FileWriter w = new FileWriter(new File("src/personalContacts.txt"));
        BufferedWriter bw = new BufferedWriter(w);

        PrintWriter wr = new PrintWriter(bw);
        for (contact contact : contacts) {
            if (contact instanceof personalContact) {

                perContact = (personalContact) contact;
                wr.write(
                        "Name:" + perContact.getName() + " Phone:" + perContact.getPhoneNumber() + "\n"
                                + "******************************************************************* \n");

            }
        }
        wr.close();
        bw.close();
    }

    public void generateProfessionalContactsFile() throws IOException {
        FileWriter w = new FileWriter(new File("src/professionalContacts.txt"));
        BufferedWriter bw = new BufferedWriter(w);

        PrintWriter wr = new PrintWriter(bw);
        for (contact contact : contacts) {
            if (contact instanceof professionalContact) {
                proContact = (professionalContact) contact;
                wr.write(
                    "Name:" + proContact.getName() + " Phone:" + proContact.getPhoneNumber() + "\n"
                            + "******************************************************************* \n");
            }
        }
        wr.close();
        bw.close();
    }
    private int findfreeid(){
        boolean notfound=false;
    int i=0;
        do{
            i++;
            notfound=false;
            for (contact n : this.contacts) {
                if (n.getId() == i) {
                    notfound = true;
                }
            }
        }while(notfound);
        return i;
    }
    public void mergeAgenda(agenda agenda){
       agenda.contacts.forEach((n)->{
           if(n instanceof personalContact) {
               personalContact aux = (personalContact) n;
               aux.setId(this.findfreeid());
               addPersonalContact(aux);
           }
           else{
               professionalContact aux = (professionalContact) n;
               aux.setId(this.findfreeid());
               addProfessionalContact(aux);
           }

       });
    }
}