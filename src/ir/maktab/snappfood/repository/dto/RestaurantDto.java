package ir.maktab.snappfood.repository.dto;

import ir.maktab.snappfood.repository.entity.Food;

public class RestaurantDto {
    private Food food;
    private String nameOfRest;
    private Integer maxOfCount;
    private Integer region;
    private Long totalService;

    public RestaurantDto() {
    }

    public RestaurantDto(String nameOfRest, Integer area, Long totalService, Food food, Integer maxOfCount) {
        this.food = food;
        this.nameOfRest = nameOfRest;
        this.maxOfCount = maxOfCount;
        this.region = area;
        this.totalService = totalService;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getNameOfRest() {
        return nameOfRest;
    }

    public void setNameOfRest(String nameOfRest) {
        this.nameOfRest = nameOfRest;
    }

    public Integer getMaxOfCount() {
        return maxOfCount;
    }

    public void setMaxOfCount(Integer maxOfCount) {
        this.maxOfCount = maxOfCount;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Long getTotalService() {
        return totalService;
    }

    public void setTotalService(Long totalService) {
        this.totalService = totalService;
    }

    @Override
    public String toString() {
        return "RestaurantDto{" +
                "nameOfFood='" + food + '\'' +
                ", nameOfRest='" + nameOfRest + '\'' +
                ", area=" + region +
                ", totalService=" + totalService +
                '}';
    }
}
