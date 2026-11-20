package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Constants;
import ru.practicum.shareit.booking.dto.BookItemRequestDto;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingDto create(@RequestHeader(Constants.USER_ID_HEADER) Long userId,
                             @RequestBody BookItemRequestDto requestDto) {
        return bookingService.create(userId, requestDto);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto approve(@RequestHeader(Constants.USER_ID_HEADER) Long userId,
                              @PathVariable Long bookingId,
                              @RequestParam Boolean approved) {
        return bookingService.approve(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingDto findById(@RequestHeader(Constants.USER_ID_HEADER) Long userId,
                               @PathVariable Long bookingId) {
        return bookingService.findById(userId, bookingId);
    }

    @GetMapping
    public List<BookingDto> findAllByUser(@RequestHeader(Constants.USER_ID_HEADER) Long userId,
                                          @RequestParam(defaultValue = "ALL") String state) {
        return bookingService.findAllByUser(userId, state);
    }

    @GetMapping("/owner")
    public List<BookingDto> findAllByOwner(@RequestHeader(Constants.USER_ID_HEADER) Long ownerId,
                                           @RequestParam(defaultValue = "ALL") String state) {
        return bookingService.findAllByOwner(ownerId, state);
    }
}
