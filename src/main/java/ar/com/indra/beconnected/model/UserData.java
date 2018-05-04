package ar.com.indra.beconnected.model;

public class UserData {

    private String name;

    private String surname;

    private String gender;

    private String email;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender(){return gender;}

    public void setGender(String gender){this.gender = gender;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("{ [name = ");
        builder.append(name);
        builder.append("],[surname = ");
        builder.append(surname);
        builder.append("],[gender = ");
        builder.append(gender);
        builder.append("],[email = ");
        builder.append(email);
        builder.append("],[age = ");
        builder.append(age);
        builder.append("] }");

        return builder.toString();

    }


}
