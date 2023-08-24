package code.repository;

import code.exception.ReservationCancellationException;
import code.model.Reservation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Transactional
    @Modifying
    @Query("update Reservation r set r.status='Cancelled' where r.id = ?1")
    void cancelReservationById(Long Id) throws ReservationCancellationException;

    @Query("select r from Reservation r where r.starwood_user_id = ?1")
    List<Reservation> getReservationByStarwoodID(String Id);
}