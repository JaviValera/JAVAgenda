package contact;
import java.util.Objects;
public class professionalContact extends contact {
    String email;
    public professionalContact(int id, String name, String gender, int phoneNumber, String email) {
        super(id,name,gender,phoneNumber);
        this.email =  email;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        professionalContact that = (professionalContact) object;
        return java.util.Objects.equals(email, that.email);
    }
    public int hashCode() {
        return Objects.hash(super.hashCode(), email);
    }
    public java.lang.String toString() {
        return "professionalContact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}