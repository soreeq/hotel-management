package pl.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.hotelmanagement.model.Guest;

import javax.transaction.Transactional;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM guests c WHERE c.guest_id = :guest_id", nativeQuery = true)
    void deleteByGuestid(@Param("guest_id") int guest_id);
    @Query(value = "SELECT FROM guests c WHERE c.guest_id = :guest_id", nativeQuery = true)
    Guest findFirstnameAndSecondnameByGuestId(@Param("guest_id") int guest_id);
}
