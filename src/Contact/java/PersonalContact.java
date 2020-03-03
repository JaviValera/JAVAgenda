package contact;
Public class personalContact extends contact {
Date date;
    public personalContact(String id, String name, String gender, String phoneNumber, String date) {
    super(id,name,gender,phoneNumber);
    this.date =  new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }
    public Date getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        personalContact that = (personalContact) object;
        return java.util.Objects.equals(date, that.date);
    }
    public int hashCode() {
        return Objects.hash(super.hashCode(), date);
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