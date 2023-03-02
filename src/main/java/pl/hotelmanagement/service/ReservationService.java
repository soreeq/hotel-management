package pl.hotelmanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.hotelmanagement.model.Guest;
import pl.hotelmanagement.repository.GuestRepository;
import pl.hotelmanagement.model.Reservation;
import pl.hotelmanagement.model.Room;
import pl.hotelmanagement.repository.ReservationRepository;
import pl.hotelmanagement.repository.RoomRepository;
import pl.hotelmanagement.repository.UserRepository;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ReservationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/reservations")
    public String getReservations(Model model) {
        List<Reservation> reservationList = reservationRepository.findAll();
        List<Guest> guestList = guestRepository.findAll();
        model.addAttribute("reservations", reservationList);
        model.addAttribute("guests", guestList);
        return "reservations.html";
    }

/*    @PostMapping("/reservations")
    public ResponseEntity addReservation(@RequestHeader String username, @RequestBody Reservation reservation) throws JsonProcessingException {
        User userFromDb = userRepository.findByUsername(username);
        Optional<Room> roomFromDb = roomRepository.findById(reservation.getRoom_id());

*//*        if(userFromDb.isEmpty()){
            return ResponseEntity.ok(HttpStatus.UNPROCESSABLE_ENTITY);
        }*//*

        Reservation newReservation = new Reservation(reservation.getreservation_id(), roomFromDb.get().getId(), reservation.getStartdate(), reservation.getEnddate());

        Reservation savedReservation = reservationRepository.save(newReservation);
        return ResponseEntity.ok(savedReservation);
    }*/
    @ModelAttribute("guests")
    public List<Guest> getGuests() {
        List<Guest> list = guestRepository.findAll();
        return list;
    }

    @ModelAttribute("rooms")
    public List<Room> getRooms() {
        List<Room> list = roomRepository.findAll();
        return list;
    }
    @GetMapping("/add-reservation")
    public String showAddReservationForm(Model model){
        model.addAttribute("newReservation", new Reservation());
/*        model.addAttribute("startDate", new Date());
        model.addAttribute("endDate", new Date());
        model.addAttribute("standardDate", LocalDateTime.now());
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("timestamp", Instant.now());*/
        return "addnewreservation.html";
    }
    @PostMapping("/add-reservation")
    public String addReservation(@Valid @ModelAttribute("newReservation") Reservation reservation, BindingResult bindingResult){

        /*        Optional<User> userFromDb = userRepository.findByUsername(username);*//*

        if(userFromDb.isEmpty()){
            return ResponseEntity.ok(HttpStatus.UNPROCESSABLE_ENTITY);
        }*/

        if(bindingResult.hasErrors()) {
                return "addnewreservation";
        }

        roomRepository.updateStatusByRoomid(reservation.getRoom_id(), "Zajete");

/*       System.out.println(test);*/

        Reservation reservationSaved = new Reservation(reservation.getReservation_id(), reservation.getGuest_id(), reservation.getRoom_id(), reservation.getStartdate(), reservation.getEnddate());

        Reservation save = reservationRepository.save(reservationSaved);

        return "redirect:/reservations";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/delete-reservation/{reservation_id}")
    public String deleteReservation(@PathVariable int reservation_id){
        Reservation foundReservation = reservationRepository.findByReservationId(reservation_id);
        int foundRoom_id = foundReservation.getRoom_id();
        roomRepository.updateStatusByRoomid(foundRoom_id, "Wolny");
        reservationRepository.deleteByReservationid(reservation_id);

        /*roomRepository.findByRoomid(reservationRepository.findRoomIdByReservation_Id(reservation_id), "Wolne");*/
        return "redirect:/reservations";
    }

}
