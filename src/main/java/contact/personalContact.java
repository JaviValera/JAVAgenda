package contact;

public class personalContact extends contact {
private String date;
    public personalContact(int id, String name, String gender, int phoneNumber, String date) {
    super(id,name,gender,phoneNumber);
    this.date =  date;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        personalContact that = (personalContact) o;
        return date != null ? date.equals(that.date) : that.date == null;
    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
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