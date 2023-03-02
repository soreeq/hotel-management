package pl.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.hotelmanagement.model.Reservation;

import javax.transaction.Transactional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM reservations c WHERE c.reservation_id = :reservation_id", nativeQuery = true)
    void deleteByReservationid(@Param("reservation_id") int reservation_id);
    /*int findRoomIdByReservationId(int reservation_id);*/
    @Query(value = "SELECT * FROM reservations c WHERE c.reservation_id = :reservation_id", nativeQuery = true)
    Reservation findByReservationId(@Param("reservation_id") int reservation_id);

}
