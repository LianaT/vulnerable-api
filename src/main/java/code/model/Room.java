package code.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.validation.constraints.NotBlank;

/*
    Rooms need:
        A room number
        Number of occupants
        Price per night
 */

@Entity
@Table(name= "room")
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private Integer number;

    @NotBlank
    private Integer occupancy;

    @NotBlank
    private Integer price;

    public Room() {
        super();
    }

    public Room(Long id, Integer number, Integer occupancy, Integer price) {
        this.id = id;
        this.number = number;
        this.occupancy = occupancy;
        this.price = price;
    }

    public Long getId() {
        return id;
    }
    public Integer getNumber() {
        return number;
    }
    public Integer getOccupancy() {
        return occupancy;
    }
    public Integer getPrice() {
        return price;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public void setOccupancy(Integer occupancy) {
        this.occupancy = occupancy;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
}
