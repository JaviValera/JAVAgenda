package contact;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
public class personalContact extends contact {
private Date date;
    public personalContact(int id, String name, String gender, int phoneNumber, Date date) {
    super(id,name,gender,phoneNumber);
    this.date =  date;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public java.lang.String toString() {
        return "personalContact{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", gender='" + this.getGender() + '\'' +
                ", phoneNumber='" + this.getPhoneNumber() + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}