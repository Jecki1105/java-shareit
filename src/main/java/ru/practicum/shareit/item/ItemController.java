package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.Constants;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> findByOwner(@RequestHeader(Constants.USER_ID_HEADER) Long userId) {
        return itemService.findByOwner(userId);
    }

    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam String text) {
        return itemService.search(text);
    }

    @GetMapping("/{itemId}")
    public ItemDto findById(@PathVariable Long itemId) {
        return itemService.findById(itemId);
    }

    @PostMapping
    public ItemDto create(@RequestHeader(Constants.USER_ID_HEADER) Long userId,
                          @RequestBody ItemDto itemDto) {
        return itemService.create(userId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public ItemDto update(@RequestHeader(Constants.USER_ID_HEADER) Long userId,
                          @PathVariable Long itemId,
                          @RequestBody ItemDto itemDto) {
        return itemService.update(userId, itemId, itemDto);
    }
}
