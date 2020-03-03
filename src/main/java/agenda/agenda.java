package agenda;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;


public class Agenda {

    private ArrayList<Contact> contacts;
    private ProfessionalContact proContact;
    private PersonalContact perContact;

    public Agenda() {
        contacts = new ArrayList<Contact>();


    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public boolean savePersonalContact(String id, String name, String number, String gender, Date date) throws IOException {
        Contact c = searchContact(id);
        //If the contact exists as a Personal Contact, it cannot be added
        if (c != null && c instanceof PersonalContact) {
            return false;
        }
        perContact = new PersonalContact(id, name, gender, number, date);
        contacts.add(perContact);

        return true;
    }

    public void loadPersonalContact() throws IOException {
        FileReader input = new FileReader("src/archivos/contactosPersonales.txt");
        BufferedReader file = new BufferedReader(input);
        String line = file.readLine();

        //load info

        while (line != null) {
            String data[] = line.split("/");
            String id = data[0];
            String name = data[1];
            String gender = data[2];
            String number = data[3];
            String date = data[4];

            String data2[] = date.split("/");
            int day = Integer.parseInt(data2[0]);
            int month = Integer.parseInt(data2[1]);
            int year = Integer.parseInt(data2[2]);
            Date date1 = new Date(day, month, year);
            perContact = new PersonalContact(id, name, gender, number, date1);
            contacts.add(perContact);



            line = file.readLine();
        }
    }

    public void loadProfessionalContact() throws IOException {
        FileReader input = new FileReader("src/archivos/contactosLaborales.txt");
        BufferedReader file = new BufferedReader(input);
        String line = file.readLine();

        //load info

        while (line != null) {
            String data[] = line.split("/");
            String id = data[0];
            String name = data[1];
            String gender = data[2];
            String number = data[3];
            String mail = data[4];


            proContact = new ProfessionalContact(id, name, gender, number, mail);
            contacts.add(proContact);


            line = file.readLine();
        }
    }

    public boolean saveProfessionalContact(String id, String name, String number, String gender,
                                           String mail) {
        Contact c = searchContact(id);
        //If the contact exists as a Professional Contact, it cannot be added
        if (c != null && c instanceof ProfessionalContact) {
            return false;
        }
        proContact = new ProfessionalContact(id, name, gender, number, mail);
        contacts.add(proContact);

        return true;
    }

    public void addProfessionalContactTxt() throws IOException {
        FileWriter file = new FileWriter("src/archivos/contactosLaborales.txt");
        for (Contact contact : contacts) {
            if (contact instanceof ProfessionalContact) {
                ContactoLaboral contact = (ProfessionalContact) contact;

                file.write(
                        contact.getId() + "/"
                                + contact.getName() + "/"
                                + contact.getGender() + "/"
                                + contact.getPhoneNumber() + "/"
                                + contact.getMail() + "\n");
            }



        }
        file.close();
    }

    public void agregarContactoPersonalTxt() throws IOException {
        FileWriter archivo = new FileWriter("src/archivos/contactosPersonales.txt");
        for (Contacto contact : contacts) {
            if (contact instanceof ContactoPersonal) {
                perContact = (ContactoPersonal) contact;

                archivo.write(
                        perContact.getId() + "/"
                                + perContact.getNombre() + "/"
                                + perContact.getGenero() + "/"
                                + perContact.getTelefono() + "/"
                                + perContact.getFechaCumpleaños().getDia() + "-"
                                + perContact.getFechaCumpleaños().getMes() + "-"
                                + perContact.getFechaCumpleaños().getAnio() + "\n");
            }
        }
        archivo.close();
    }

    public Contacto searchContact(String id) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(id)) {
                return contacts.get(i);
            }
        }
        return null;
    }

    public void generarArchivoContactosPersonales(File selectedFile) throws IOException {
        FileWriter w = new FileWriter(selectedFile);
        BufferedWriter bw = new BufferedWriter(w);

        PrintWriter wr = new PrintWriter(bw);
        for (Contacto contacto : contacts) {
            if (contacto instanceof ContactoPersonal) {

                perContact = (ContactoPersonal) contacto;
                wr.write(
                        "Numbre:" + perContact.nombre + " Telefono:" + perContact.getTelefono() + "\n"
                                + "******************************************************************* \n");//escribimos en el archivo

            }
        }
        wr.close();
        bw.close();
    }

    public void generarArchivoContactosLaborales(File selectedFile) throws IOException {
        FileWriter w = new FileWriter(selectedFile);
        BufferedWriter bw = new BufferedWriter(w);

        PrintWriter wr = new PrintWriter(bw);
        for (Contacto contacto : contacts) {
            proContact = (ContactoLaboral) contacto;
            wr.write(
                    "Numbre:" + proContact.nombre + " Telefono:" + proContact.getTelefono() + "\n"
                            + "******************************************************************* \n");//escribimos en el archivo

        }
        wr.close();
        bw.close();
    }
}