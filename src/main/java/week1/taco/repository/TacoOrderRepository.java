package week1.taco.repository;

import org.springframework.data.repository.CrudRepository;

import week1.taco.models.TacoOrder;

public interface TacoOrderRepository extends CrudRepository<TacoOrder, Long> {
}
