package pl.hotelmanagement.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.hotelmanagement.model.Guest;
import pl.hotelmanagement.repository.GuestRepository;
import pl.hotelmanagement.repository.ReservationRepository;
import pl.hotelmanagement.repository.RoomRepository;
import pl.hotelmanagement.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GuestService {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/guests")
    public String showGuests(Model model) throws JsonProcessingException {
        List<Guest> guestList = guestRepository.findAll();
        System.out.println(guestList);
        model.addAttribute("guests", guestList);
        return "guests";
    }

    @GetMapping("/add-guest")
    public String showAddGuestForm(Model model){
        model.addAttribute("newGuest", new Guest());
        return "addnewguest";
    }

    @PostMapping("/add-guest")
    public String addGuest(@Valid @ModelAttribute("newGuest") Guest guest, BindingResult bindingResult){

/*        Optional<User> userFromDb = userRepository.findByUsername(username);*//*

        if(userFromDb.isEmpty()){
            return ResponseEntity.ok(HttpStatus.UNPROCESSABLE_ENTITY);
        }*/

        List<Guest> guestList = guestRepository.findAll();

        if(bindingResult.hasErrors()) {
            return "addnewguest";
        }


        Guest guestSaved = new Guest(guest.getGuest_id(), guest.getFirstname(), guest.getLastname(), guest.getMiddlename(), guest.getAddress(), guest.getPhonenumber(), guest.getEmail(), guest.getNotes());
        Guest save = guestRepository.save(guestSaved);

        return "redirect:/guests";
    }
    @RequestMapping("/delete-guest/{guest_id}")
    public String deleteGuest(@PathVariable int guest_id){
        guestRepository.deleteByGuestid(guest_id);
        return "redirect:/guests";
    }
}
