package code.controller;

import code.exception.*;
import code.model.*;
import code.repository.*;
import code.repository.MembershipRepository;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    CardRepository cardRepository;

    // Load home search page
    @RequestMapping({"/", "/list"})
    public String viewHomePage(Model model){
//  Uncomment for sample data to begin, with the sample rooms then you can create user and bookings.
//        Room r1 = new Room( (long)1, 1, 5, 100);
//        roomRepository.save(r1);
//        Room r2 = new Room( (long)2, 15, 5, 100);
//        roomRepository.save(r2);
//        Room r3 = new Room( (long)3, 4, 4, 80);
//        roomRepository.save(r3);
//        Room r4 = new Room( (long)4, 67, 2, 30);
//        roomRepository.save(r4);
//        Room r5 = new Room( (long)5, 24, 3, 40);
//        roomRepository.save(r5);
//
        return "welcome";
    }

    // Search for available rooms matching search criteria
    @RequestMapping(value="/search", method= RequestMethod.POST)
    public String searchForRoom(@ModelAttribute("SearchForm") SearchForm searchForm, Model model) throws ReservationNotFoundException, NullPointerException, ParseException {
        Iterable<Long> availableRooms = roomRepository.findAvailableRooms(searchForm.getStart(), searchForm.getEnd(), searchForm.getOccupants());
        List<Room> listRooms = roomRepository.findAllById(availableRooms);
        if (listRooms.isEmpty())
        {
            model.addAttribute("informationMessage", "No rooms were found matching your search criteria.");
        } else if (searchForm.getStart() == null || searchForm.getEnd() == null || searchForm.getOccupants() == null )
        {
            model.addAttribute("informationMessage", "Please fill in all search fields to view results");
        } else if (searchForm.getOccupants().intValue() < 1)
        {
            model.addAttribute("informationMessage", "Cannot search for rooms for less than one person.");
        } else
        {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date todayDate = dateFormatter.parse(dateFormatter.format(new Date()));

            if (searchForm.getEnd().before(searchForm.getStart()) || searchForm.getStart().equals(searchForm.getEnd())) {
                model.addAttribute("informationMessage", "Check-out date must be at least one day after the check-in date.");
            } else if (searchForm.getStart().before(todayDate))
            {
                model.addAttribute("informationMessage", "Reservations cannot be made in the past.");
            } else {
                model.addAttribute("listRooms", listRooms);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String start = dateFormat.format(searchForm.getStart());
                String end = dateFormat.format(searchForm.getEnd());
                model.addAttribute("start", start);
                model.addAttribute("end", end);
                model.addAttribute("informationMessage", "Rooms matching your search for " + searchForm.getOccupants() + " guests from " + start + " to " + end);
            }
        }
        return "welcome";
    }

    // Generate booking form for selected room and dates
    @GetMapping("/bookRoom/{id}/{start}/{end}")
    public String getBookingForm(@PathVariable(value="id") Long roomId, @PathVariable String start, @PathVariable String end, Model model)
            throws RoomNotFoundException, BookingFormFailedException {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException(roomId));
        model.addAttribute("room", room);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        if (room == null || start == null || end == null)
        {
            throw new BookingFormFailedException();
        } else {
            return "bookingForm";
        }
    }

    // Save reservation
    @RequestMapping(value="/bookRoom/save", method= RequestMethod.POST)
    public String saveReservation(@ModelAttribute("Reservation") Reservation reservation, Model model) throws IllegalArgumentException, OptimisticEntityLockException, ReservationNotFoundException, RoomNotFoundException, BookingFormFailedException {
        if (reservation.getFirst_name() == null || reservation.getSurname() == null || reservation.getAddress() == null ||
        reservation.getCard_number() == null || reservation.getEmail_address() == null|| reservation.getPhone_number() == null ||
        reservation.getFirst_name() == "" || reservation.getSurname() == "" || reservation.getAddress() == "" || reservation.getEmail_address() == "")
        {
            model.addAttribute("error_message", "Please fill in all fields to complete booking.");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String start = dateFormat.format(reservation.getStart());
            String end = dateFormat.format(reservation.getEnd());
            model.addAttribute("reservation", reservation);
            getBookingForm(reservation.getRoom_Id().longValue(), start, end, model);
        }
        else {
            reservationRepository.save(reservation);
            Long reservationID = reservation.getId();
            reservationRepository.findById(reservationID).orElseThrow(() -> new ReservationNotFoundException(reservationID));
            model.addAttribute("reservationID", "Reservation number "+reservationID.toString()+" successfully created.");
        }
        return "bookingForm";
    }

    // Open search for reservations page
    @RequestMapping({ "/findReservation"})
    public String viewReservationsPage(Model model){
        return "findReservationsForm";
    }

    // Search for my previous reservation by ID
    @RequestMapping(value="/reservations/search", method= RequestMethod.POST)
    public String searchForAReservation(@ModelAttribute("reservation_id") Long reservation_id, Model model) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(reservation_id).orElseThrow(() -> new ReservationNotFoundException(reservation_id));
        model.addAttribute("reservation", reservation);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(reservation.getStart());
        String end = dateFormat.format(reservation.getEnd());
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        return "editReservationsForm";
    }

    // Delete reservation by id
    @RequestMapping(value="/reservations/delete/{id}", method= RequestMethod.POST)
    public String deleteReservation(@ModelAttribute("SearchForm") SearchForm searchForm, @PathVariable("id") Long id, Model model) throws ReservationNotFoundException, ParseException, ReservationCancellationException {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
        model.addAttribute("reservation", reservation);
        if (reservation.getCard_number().equals(searchForm.getCredit_card_number()))
        {
            model.addAttribute("errorMessage", reservation.getCard_number()+ " "+ searchForm.getCredit_card_number());
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date todayDate = dateFormatter.parse(dateFormatter.format(new Date()));

            if (reservation.getStart().before(todayDate) || reservation.getStart().equals(todayDate)) {
                model.addAttribute("errorMessage", "You cannot cancel a reservation less than a days notice.");
            } else {
                reservationRepository.cancelReservationById(id);
                model.addAttribute("errorMessage", "Reservation successfully cancelled.");
            }
        } else
        {
            model.addAttribute("errorMessage", "A reservation can only be cancelled using the credit card used to make the booking.");
        }
        return searchForAReservation(reservation.getId(), model);
    }


    @GetMapping("/memberBooking/{Roomid}/{User_id}/{start}/{end}")
    public String getMemberReservationById(@PathVariable(value="Roomid") Long roomId, @PathVariable(value="User_id") Long user_id, @PathVariable String start, @PathVariable String end, Model model)
            throws ReservationNotFoundException, MemberNotFoundException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ReservationNotFoundException(roomId));
        Membership member = membershipRepository.findById(user_id)
                .orElseThrow(() -> new MemberNotFoundException(user_id));
        List<Card> card_list = cardRepository.getCardsByID(user_id);
        model.addAttribute("card_list", card_list);
        Integer price = room.getPrice();
        price = Math.toIntExact(Math.round(price * 0.9));
        room.setPrice(price);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("room", room);
        model.addAttribute("member", member);
        model.addAttribute("id", user_id);

        return "memberBooking";
    }

    @RequestMapping(value="/memberBooking/save/{user_id}", method= RequestMethod.POST)
    public String saveMemberReservation(@ModelAttribute("Reservation") Reservation reservation, @ModelAttribute("user_card") String card_number, @PathVariable(value="user_id") Long user_id, Model model) throws IllegalArgumentException, MemberNotFoundException, OptimisticEntityLockException, ReservationNotFoundException, RoomNotFoundException, BookingFormFailedException {
        Membership member = membershipRepository.findById(user_id)
                .orElseThrow(() -> new MemberNotFoundException(user_id));
        reservation.setFirst_name(member.getName());
        reservation.setSurname(member.getSurname());
        reservation.setAddress(member.getAddress()) ;
        reservation.setCard_number(card_number) ;
        reservation.setEmail_address(member.getEmail()) ;
        reservation.setPhone_number(member.getPhone_number()) ;
        reservation.setStarwood_user_id(user_id);
        reservationRepository.save(reservation);
        Long reservationID = reservation.getId();
        reservationRepository.findById(reservationID).orElseThrow(() -> new ReservationNotFoundException(reservationID));
        model.addAttribute("reservationID", "Reservation number "+reservationID.toString()+" successfully created.");
        return "redirect:/memberPage/%d".formatted(user_id);
    }

    // Register a new membership
    @RequestMapping("/newMember")
    public String newMember() {
        return "registerMembership";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/memberPage/{id}")
    public String memberPage(@ModelAttribute("SearchForm") SearchForm searchForm, @PathVariable(value="id") Long id, Model model) throws MemberNotFoundException, ReservationNotFoundException, NullPointerException, ParseException {
        Iterable<Long> availableRooms = roomRepository.findAvailableRooms(searchForm.getStart(), searchForm.getEnd(), searchForm.getOccupants());
        List<Room> listRooms = roomRepository.findAllById(availableRooms);
//        List<Room> listRooms = roomRepository.findAll();
//        model.addAttribute("listRooms", listRooms);
        if (listRooms.isEmpty())
        {
            model.addAttribute("informationMessage", "No rooms were found matching your search criteria.");
        } else if (searchForm.getStart() == null || searchForm.getEnd() == null || searchForm.getOccupants() == null )
        {
            model.addAttribute("informationMessage", "Please fill in all search fields to view results");
        } else if (searchForm.getOccupants().intValue() < 1)
        {
            model.addAttribute("informationMessage", "Cannot search for rooms for less than one person.");
        } else
        {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date todayDate = dateFormatter.parse(dateFormatter.format(new Date()));

            if (searchForm.getEnd().before(searchForm.getStart()) || searchForm.getStart().equals(searchForm.getEnd())) {
                model.addAttribute("informationMessage", "Check-out date must be at least one day after the check-in date.");
            } else if (searchForm.getStart().before(todayDate))
            {
                model.addAttribute("informationMessage", "Reservations cannot be made in the past.");
            } else {
                model.addAttribute("listRooms", listRooms);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String start = dateFormat.format(searchForm.getStart());
                String end = dateFormat.format(searchForm.getEnd());
                model.addAttribute("start", start);
                model.addAttribute("end", end);
                model.addAttribute("informationMessage", "Rooms matching your search for " + searchForm.getOccupants() + " guests from " + start + " to " + end);
            }
        }
        Membership member = membershipRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        model.addAttribute("member", member);
        List<Reservation> rev_list = reservationRepository.getReservationByStarwoodID(id.toString());
        model.addAttribute("rev_list", rev_list);

        return "memberPage";
    }

    @RequestMapping(value="/memberPage/cancel/{rev_id}")
    public String cancelMemberReservation(@PathVariable("rev_id") Long id, Model model) throws ReservationNotFoundException, ParseException, ReservationCancellationException, MemberNotFoundException {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
        Membership member = membershipRepository.findById(reservation.getStarwood_user_id())
                .orElseThrow(() -> new MemberNotFoundException(reservation.getStarwood_user_id()));
        reservationRepository.cancelReservationById(id);
        return "redirect:/memberPage/%d".formatted(member.getId());
    }

    @RequestMapping(value="/memberPage/removeAccount/{mem_id}")
    public String removeMemberAccount(@PathVariable("mem_id") Long id, Model model) throws ReservationNotFoundException, ParseException, ReservationCancellationException, MemberNotFoundException {
        membershipRepository.deleteById(id);
        return "redirect:/list";
    }

    @RequestMapping(value="/updateMembershipDetail/removeCard/{card_id}")
    public String removeCard(@PathVariable("card_id") Long id, Model model) throws MemberNotFoundException {
        Optional<Card> cur_card = cardRepository.findById(id);
        Membership member = membershipRepository.findById(cur_card.get().getMember_id())
                .orElseThrow(() -> new MemberNotFoundException(cur_card.get().getMember_id()));
        cardRepository.deleteById(id);
        return "redirect:/updateMembershipDetail/%d".formatted(member.getId());
    }

    @RequestMapping({"/updateMembershipDetail/{current_id}"})
    public String updateMembershipform(@PathVariable(value="current_id") Long current_id, Model model) throws MemberNotFoundException {
        Membership member = membershipRepository.findById(current_id)
                .orElseThrow(() -> new MemberNotFoundException(current_id));

        model.addAttribute("member", member);
        List<Card> card_list = cardRepository.getCardsByID(current_id);
        model.addAttribute("card_list", card_list);
        return "updateMembershipDetail";
    }

    @PostMapping({"/updateMembershipDetail/{current_id}"})
    public String updatedMembership(@ModelAttribute("member") Membership membership, @PathVariable(value="current_id") Long current_id, @ModelAttribute("new_card") Card new_card, Model model) {

        membershipRepository.updateMemberById(current_id, membership.getName(), membership.getSurname(), membership.getAddress(), membership.getPhone_number(), membership.getEmail(), membership.getPassword() );
        if (new_card.getCredit_card() != "") {
            new_card.setMember_id(current_id);
            cardRepository.save(new_card);
        }
        return "redirect:/memberPage/%d".formatted(current_id);
    }

    @PostMapping("/register")
    public String registerMember(@ModelAttribute("membership") Membership membership, @ModelAttribute("card") Card card, Model model) {
        membershipRepository.save(membership);
        System.out.println(membership.getUsername());
        System.out.println(membership.getName());
        List<Long> member1 = membershipRepository.findFirstByName(membership.getUsername());
        long id = member1.get(0);
        card.setMember_id(id);
        cardRepository.save(card);
        return "redirect:/memberPage/%d".formatted(id);
    }

    @PostMapping("/login")
    public String loginMember(@ModelAttribute("membership") Membership membership, Model model) throws PasswordIncorrectException, MemberNotFoundException {
        String uname = membership.getUsername();
        String inputPassword = membership.getPassword();
        //System.out.println(inputPassword);

        List<Long> member_id = membershipRepository.findFirstByName(uname);
        Membership member = membershipRepository.findById(member_id.get(0))
                .orElseThrow(() -> new MemberNotFoundException(member_id.get(0)));
        String correctPassword = member.getPassword();
        System.out.println(correctPassword);

        if (Objects.equals(correctPassword, inputPassword))
        {
            return "redirect:/memberPage/%d".formatted(member_id.get(0));
        }
        else
        {
            throw new PasswordIncorrectException();
        }
    }

}