package week1.taco.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import week1.taco.models.Ingredient;
import week1.taco.models.Taco;
import week1.taco.repository.IngredientRepository;
import week1.taco.repository.TacoRepository;

@Slf4j
@RestController
@RequestMapping("/design")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepo;

    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
    }

    @GetMapping
    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new java.util.ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);
        return ingredients;
    }

    // private Iterable<Ingredient> filterByType(
    //         List<Ingredient> ingredients, Type type) {
    //     return ingredients
    //             .stream()
    //             .filter(x -> x.getType().equals(type))
    //             .collect(Collectors.toList());
    // }

    @PostMapping
    public ResponseEntity<Taco> processTaco(@RequestBody @Valid Taco taco) {
        if (taco.getIngredients() != null) {
            List<Ingredient> managed = taco.getIngredients().stream()
                    .map(i -> ingredientRepo.findById(i.getId()).orElse(i))
                    .collect(java.util.stream.Collectors.toList());
            taco.setIngredients(managed);
        }

        Taco saved = tacoRepo.save(taco);
        log.info("Processing taco: {}", saved);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
