package agenda;
import contact.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class agenda {

    private final ArrayList<contact> contacts;
    private professionalContact proContact;
    private personalContact perContact;

    public agenda() {
        contacts = new ArrayList<contact>();
    }

    public ArrayList<contact> getContacts() {
        return contacts;
    }

    public void addProfessionalContact(professionalContact newContact) {
        contact c = searchContact(newContact.getId());
        //If the contact exists as a Professional Contact, it cannot be added
        if (c instanceof professionalContact);
        else contacts.add(newContact);
    }
    public void addPersonalContact(personalContact newContact){
        contact c = searchContact(newContact.getId());
        //If the contact exists as a Personal Contact, it cannot be added
        if (c instanceof personalContact);
        else contacts.add(newContact);
    }

    public void modify(int id, int value, String data){
        switch (value){
            case 1:
                searchContact(id).setId(Integer.parseInt(data));
                break;
            case 2:
                searchContact(id).setName(data);
                break;
            case 3:
                searchContact(id).setGender(data);
                break;
            case 4:
                searchContact(id).setPhoneNumber(Integer.parseInt(data));
                break;
        }
    }

    public boolean removeContact(int id){
        boolean resul=false;
        contact c = searchContact(id);
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

    public void loadPersonalContact() throws IOException {
        FileReader input = new FileReader("src/database/personalContact.db");
        BufferedReader file = new BufferedReader(input);
        String line = file.readLine();

        //load info

        while (line != null) {
            String[] data = line.split("-");
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
            String[] data = line.split("-");
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

    public contact searchContact(int id) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == id) {
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
    protected int findfreeid(){
        boolean notfound;
    int i=0;
        do{
            i++;
            notfound=false;
            for (contact n : this.contacts) {
                if (n.getId() == i) {
                    notfound = true;
                    break;
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