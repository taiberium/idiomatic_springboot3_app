package org.example.idiomatic_springboot3_app.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.example.idiomatic_springboot3_app.model.Counter;
import org.example.idiomatic_springboot3_app.service.CountersService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/counters")
@RequiredArgsConstructor
public class CountersController {

    private final CountersService countersService;

    @GetMapping("{name}")
    public Optional<CounterDto> findOne(@PathVariable("name") String name) {
        return countersService.findOne(name)
                .map(this::mapToDto);
    }

    @GetMapping
    public List<CounterDto> getAll() {
        return countersService.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @PostMapping("{name}/count/{count}")
    public CounterDto increment(@PathVariable("name") String name, @PathVariable("count") @Min(1) int count) {
        Counter counter = countersService.increment(name, count);
        return mapToDto(counter);
    }

    private CounterDto mapToDto(Counter counter) {
        return new CounterDto(counter.getName(), counter.getCount());
    }

    public record CounterDto(String name, int count) {
    }
}
