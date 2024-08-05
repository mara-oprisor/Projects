package model;

/**
 * Represents a client entity with basic information such as name, age, email, and phone number.
 */
public class Client {
    private int id;
    private String name;
    private int age;
    private String email;
    private String phoneNr;

    /**
     * Retrieves the ID of the client.
     *
     * @return the ID of the client
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the client.
     *
     * @param id the ID of the client
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the client.
     *
     * @return the name of the client
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     *
     * @param name the name of the client
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the age of the client.
     *
     * @return the age of the client
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the client.
     *
     * @param age the age of the client
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Retrieves the email address of the client.
     *
     * @return the email address of the client
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the client.
     *
     * @param email the email address of the client
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the phone number of the client.
     *
     * @return the phone number of the client
     */
    public String getPhoneNr() {
        return phoneNr;
    }

    /**
     * Sets the phone number of the client.
     *
     * @param phoneNr the phone number of the client
     */
    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    /**
     * Constructs a new Client object with the specified attributes.
     *
     * @param name    the name of the client
     * @param age     the age of the client
     * @param email   the email address of the client
     * @param phoneNr the phone number of the client
     */
    public Client(String name, int age, String email, String phoneNr) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNr = phoneNr;
    }

    /**
     * Constructs a new Client object with no specified attributes.
     */
    public Client() {

    }
}
