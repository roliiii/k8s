package com.fluffy.counter.controller;

import com.fluffy.counter.entity.Counter;
import com.fluffy.counter.repository.CounterRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Consumer;

@RestController
@Transactional
@AllArgsConstructor
public class CounterRestController {

    private CounterRepository counterRepository;

    @GetMapping
    public List<Counter> all(){
        return counterRepository.findAll();
    }

    @GetMapping("/{name}/add")
    public int add(@PathVariable("name") String name){
        return count(name, x -> {
            x.setValue(x.getValue() + 1);
        }).getValue();
    }


    @GetMapping("/{name}/sub")
    public int sub(@PathVariable("name") String name){
        return count(name, x -> {
            x.setValue(x.getValue() - 1);
        }).getValue();
    }

    @GetMapping("/{name}")
    public int get(@PathVariable("name") String name){
        return count(name, x -> {}).getValue();
    }

    private Counter count(String name, Consumer<Counter> operation) {
        Counter counter = counterRepository.findById(name).orElseGet(() -> {
            Counter newCounter = new Counter();
            newCounter.setName(name);
            return counterRepository.save(newCounter);
        });

        operation.accept(counter);
        return counter;

    }

}
