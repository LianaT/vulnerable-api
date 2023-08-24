package code.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.validation.constraints.NotBlank;

@Entity
@Table(name= "card")
public class Card {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private Long member_id;

    @NotBlank
    private String credit_card;

    public Card() {
        super();
    }

    public Card(Long id, long member_id, String credit_card) {
        this.id = id;
        this.member_id = member_id;
        this.credit_card = credit_card;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public String getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(String credit_card) {
        this.credit_card = credit_card;
    }
}
