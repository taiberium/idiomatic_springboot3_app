package org.example.idiomatic_springboot3_app.service;

import lombok.RequiredArgsConstructor;
import org.example.idiomatic_springboot3_app.model.Counter;
import org.example.idiomatic_springboot3_app.repository.CountersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountersService {

    private final CountersRepository countersRepository;

    public Optional<Counter> findOne(String name) {
        return countersRepository.findById(name);
    }

    public List<Counter> findAll() {
        return countersRepository.findAll();
    }

    @Transactional
    public Counter increment(String name, int count) {
        Optional<Counter> optionalCounter = findOne(name);
        Counter incrementedCounter = optionalCounter
                .map(counter -> new Counter(name, counter.getCount() + count))
                .orElseGet(() -> new Counter(name, count));
        countersRepository.save(incrementedCounter);
        return incrementedCounter;
    }
}
