package code.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private Integer room_id;
    private Long starwood_user_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty
    private Date start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty
    private Date end;
    @NotEmpty
    private String card_number;
    @NotEmpty
    private String first_name;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String phone_number;
    @NotEmpty
    private String email_address;
    @NotEmpty
    private String status;
    @NotEmpty
    private String address;

    public Reservation(){
        super();
    }
    public Reservation(Long id, Integer room_id, Long starwood_user_id, String first_name, String surname, String phone_number, String email_address, Date start, Date end, String card_number, String address) {
        this.id = id;
        this.room_id = room_id;
        this.starwood_user_id = starwood_user_id;
        this.first_name = first_name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.email_address = email_address;
        this.start = start;
        this.end = end;
        this.card_number = card_number;
        this.status = "Active";
        this.address = address;
    }

    public Long getId() {
        return id;
    }
    public Integer getRoom_Id() {
        return room_id;
    }
    public Long getStarwood_user_id() {
        return starwood_user_id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public String getSurname() {
        return surname;
    }
    public Date getStart() {
        return start;
    }
    public Date getEnd() {
        return end;
    }
    public String getCard_number() {
        return card_number;
    }
    public String getStatus() {
        return status;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public String getEmail_address() { return email_address;}
    public void setId(Long id) {this.id = id;}
    public void setRoom_id(Integer room_id) { this.room_id = room_id;}
    public void setStarwood_user_id(Long starwood_user_id) {this.starwood_user_id = starwood_user_id;}
    public void setFirst_name(String first_name) {this.first_name= first_name;}
    public void setSurname(String surname){this.surname = surname;}
    public void setStart(Date start) {this.start = start;}
    public void setEnd(Date end) {this.end =end;}
    public void setCard_number(String card_number) {this.card_number = card_number;}
    public void setStatus(String status) {this.status = status;}
    public void setPhone_number(String phone_number) {this.phone_number = phone_number;}
    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}