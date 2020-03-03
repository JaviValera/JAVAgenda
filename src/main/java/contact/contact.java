package contact;
import java.util.Objects;
public class contact {
    int id;
    String name;
    String gender;
    int phoneNumber;

    public contact(int id, String name, String gender, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        contact contact = (contact) object;
        return id==(contact.id) &&
                name.equals(contact.name) &&
                java.util.Objects.equals(gender, contact.gender) &&
                java.util.Objects.equals(phoneNumber, contact.phoneNumber);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, gender, phoneNumber);
    }
    @java.lang.Override
    public java.lang.String toString() {
        return "contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}