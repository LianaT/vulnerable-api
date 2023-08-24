package code.repository;

import code.model.Membership;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    @Transactional
    @Modifying
    @Query("update Membership m set m.name = ?2, m.surname = ?3, m.address = ?4, m.phone_number = ?5, m.email = ?6, m.password = ?7  where m.id = ?1")
    void updateMemberById(Long Id, String name, String surname, String address, String pnumber, String email, String pword );

    @Query("select m.id from Membership m where m.username = ?1")
    List<Long> findFirstByName(String username);

}
