package ir.maktab.snappfood.repository.dao;

import ir.maktab.snappfood.repository.entity.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodDao extends CrudRepository<Food, Integer> {

    Food findByNameAndPrice(String foodName, Integer foodPrice);

}
