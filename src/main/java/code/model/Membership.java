package code.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.validation.constraints.NotBlank;
import java.util.List;

/*
    Membership need:
        Name of the member
        Surename of the member
        Address of the member
        Phone number of the member
        Email of the member
        password of the username
        credit card number of the membership account
 */

@Entity
@Table(name= "membership")
public class Membership {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String address;

    @NotBlank
    private String phone_number;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public Membership()
    {
        super();
    }

    public Membership(String username, String name, String surname, String address, String phone_number, String email,
                      String password, String credit_card)
    {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone_number()
    {
        return phone_number;
    }

    public void setPhone_number(String phone_number)
    {
        this.phone_number = phone_number;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}