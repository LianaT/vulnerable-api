package code.repository;

import code.exception.ReservationNotFoundException;
import code.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

    @Query("select r.id from Room r where r.id not in( select r.id from Room r LEFT OUTER JOIN Reservation res on " +
            "r.id = res.room_id where (res.end > ?1 and res.start <= ?1 ) or (res.end >= ?2 and res.start < ?2) or " +
            "r.occupancy < ?3)")
    List<Long> findAvailableRooms(@DateTimeFormat(pattern = "yyyy-MM-dd") Date checkIn, @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOut, Number occupants) throws ReservationNotFoundException;

}
