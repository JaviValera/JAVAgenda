package contact;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
public class personalContact extends contact {
Date date;
    public personalContact(String id, String name, String gender, String phoneNumber, Date date) {
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
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}