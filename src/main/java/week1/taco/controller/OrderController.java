package week1.taco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import week1.taco.models.TacoOrder;
import week1.taco.repository.TacoOrderRepository;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final TacoOrderRepository tacoOrderRepo;

    public OrderController(TacoOrderRepository tacoOrderRepo) {
        this.tacoOrderRepo = tacoOrderRepo;
    }

    @GetMapping("/current")
    public TacoOrder orderForm() {
        return new TacoOrder();
    }

    @PostMapping
    public ResponseEntity<TacoOrder> processOrder(@RequestBody @Valid TacoOrder order) {
        TacoOrder saved = tacoOrderRepo.save(order);
        log.info("Order submitted: {}", saved);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
