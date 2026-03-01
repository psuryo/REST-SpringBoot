package week1.taco.repository;

import org.springframework.data.repository.CrudRepository;

import week1.taco.models.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
