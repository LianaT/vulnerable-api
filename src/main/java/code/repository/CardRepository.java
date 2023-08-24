package code.repository;

import code.model.Card;
import code.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

    @Query("select r from Card r where r.member_id = ?1")
    List<Card> getCardsByID(Long user_id);
}
