package ir.maktab.snappfood.service;

import ir.maktab.snappfood.repository.dao.FoodDao;
import ir.maktab.snappfood.repository.entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FoodService {

    @Autowired
    FoodDao foodDao;

    public boolean addNewFood(Food food) {
        if (Objects.nonNull(food.getName()) && Objects.nonNull(food.getFoodType())) {
            foodDao.save(food);
            return true;
        }
        return false;
    }

    public Food findByNameAndPrice(Food food) {
        return foodDao.findByNameAndPrice(food.getName(), food.getPrice());
    }
}
