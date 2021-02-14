package ir.maktab.snappfood.repository.dto;

import ir.maktab.snappfood.repository.entity.Food;

public class CartDto {

    private Integer count;
    private Food food;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "count=" + count +
                ", food=" + food +
                '}';
    }
}
