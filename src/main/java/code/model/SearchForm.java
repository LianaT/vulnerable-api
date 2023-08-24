package code.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class SearchForm {

    private Number occupants;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;

    private Integer credit_card_number;

    public Integer getCredit_card_number() {
        return this.credit_card_number;
    }
    public Number getOccupants() {
        return this.occupants;
    }

    public Date getStart() {
        return this.start;
    }

    public Date getEnd() {
        return this.end;
    }

    public void setOccupants(Number occupants) {this.occupants = occupants;}
    public void setStart(Date start) {
        this.start = start;
    }
    public void setEnd(Date end) {
         this.end = end;
    }
    public void getCredit_card_number(Integer credit_card_number){this.credit_card_number = credit_card_number;}

    public SearchForm(Number occupants, Date start, Date end, Integer credit_card_number) {
        this.occupants = occupants;
        this.start = start;
        this.end = end;
        this.credit_card_number = credit_card_number;
    }
}