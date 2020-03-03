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

    public boolean addProfessionalContact(String id, String name, String number, String gender, String email) {
        boolean resul = true;
        contact c = searchContact(id);
        //If the contact exists as a Professional Contact, it cannot be added
        if (c != null && c instanceof professionalContact) {
            resul = false;
        }
        proContact = new professionalContact(id, name, gender, number, email);
        contacts.add(proContact);

        return resul;
    }
    public boolean addPersonalContact(String id, String name, String number, String gender, Date date) throws IOException, ParseException {
        boolean resul = true;
        contact c = searchContact(id);
        //If the contact exists as a Personal Contact, it cannot be added
        if (c != null && c instanceof personalContact) {
            resul = false;
        }
        perContact = new personalContact(id, name, gender, number, date);
        contacts.add(perContact);
        return resul;
    }
    public boolean removeContact(String id){
        boolean resul=false;
        contact c = searchContact(id);
        if(c != null) {
            contacts.remove(c);
            resul = true;
        }
            return resul;
    }

    public void loadPersonalContact() throws IOException, ParseException {
        FileReader input = new FileReader("src/database/personalContact.txt");
        BufferedReader file = new BufferedReader(input);
        String line = file.readLine();

        //load info

        while (line != null) {
            String data[] = line.split("-");
            String id = data[0];
            String name = data[1];
            String gender = data[2];
            String number = data[3];
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data[4]);

            perContact = new personalContact(id, name, gender, number, date);
            contacts.add(perContact);



            line = file.readLine();
        }
    }

    public void loadProfessionalContact() throws IOException {
        FileReader input = new FileReader("src/database/professionalContact.txt");
        BufferedReader file = new BufferedReader(input);
        String line = file.readLine();

        //load info

        while (line != null) {
            String data[] = line.split("-");
            String id = data[0];
            String name = data[1];
            String gender = data[2];
            String number = data[3];
            String email = data[4];


            proContact = new professionalContact(id, name, gender, number, email);
            contacts.add(proContact);


            line = file.readLine();
        }
    }

    public void saveProfessionalContactTxt() throws IOException {
        FileWriter file = new FileWriter("src/database/professionalContact.txt");
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

    public void savePersonalContactTxt() throws IOException {
        FileWriter file = new FileWriter("src/database/personalContact.txt");
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

    public contact searchContact(String id) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(id)) {
                return contacts.get(i);
            }
        }
        return null;
    }

    public void generatePersonalContactsFile(File selectedFile) throws IOException {
        FileWriter w = new FileWriter(selectedFile);
        BufferedWriter bw = new BufferedWriter(w);

        PrintWriter wr = new PrintWriter(bw);
        for (contact contact : contacts) {
            if (contact instanceof professionalContact) {

                perContact = (personalContact) contact;
                wr.write(
                        "Name:" + perContact.getName() + " Phone:" + perContact.getPhoneNumber() + "\n"
                                + "******************************************************************* \n");

            }
        }
        wr.close();
        bw.close();
    }

    public void generateProfessionalContactsFile(File selectedFile) throws IOException {
        FileWriter w = new FileWriter(selectedFile);
        BufferedWriter bw = new BufferedWriter(w);

        PrintWriter wr = new PrintWriter(bw);
        for (contact contact : contacts) {
            proContact = (professionalContact) contact;
            wr.write(
                    "Name:" + proContact.getName() + " Phone:" + proContact.getPhoneNumber() + "\n"
                            + "******************************************************************* \n");
        }
        wr.close();
        bw.close();
    }
}