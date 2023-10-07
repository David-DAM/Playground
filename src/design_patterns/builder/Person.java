package design_patterns.builder;

public class Person {

    String name;
    String lastname;
    int age;
    double height;

    private Person(PersonBuilder builder) {
        this.name = builder.name;
        this.lastname = builder.lastname;
        this.age = builder.age;
        this.height = builder.height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }

    public static class PersonBuilder{

        String name;
        String lastname;
        int age;
        double height;

        public PersonBuilder() {

        }

        public PersonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public PersonBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder setHeight(double height) {
            this.height = height;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }
}
