package week1.taco.repository;

import org.springframework.data.repository.CrudRepository;

import week1.taco.models.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
