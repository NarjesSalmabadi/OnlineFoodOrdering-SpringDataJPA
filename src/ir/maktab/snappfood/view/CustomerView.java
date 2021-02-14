package ir.maktab.snappfood.view;

import ir.maktab.snappfood.repository.dto.CartDto;
import ir.maktab.snappfood.repository.entity.*;
import ir.maktab.snappfood.repository.enums.FoodType;
import ir.maktab.snappfood.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CustomerView {

    String phoneNumber;
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    RestaurantMenuService restaurantMenuService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ReservationService reservationService;

    public void customerPanel() {
        phoneNumber = GetInput.getPhoneNumber();
        System.out.println("Enter your region:");
        int region = GetInput.getInt();
        while (true) {
            System.out.println("1- Show all restaurants in your region");
            System.out.println("2- Show restaurants in your region with a specific food type");
            System.out.println("3- Exit");
            int input = GetInput.getIntegerInInterval(1, 3);
            switch (input) {
                case 1:
                    showRestaurantByRegion(region);
                    continue;
                case 2:
                    System.out.println("Enter your favorite food type:");
                    FoodType foodType = GetInput.getFoodTypeFromUser();
                    showRestaurantByRegionAndFoodType(region, foodType);
                case 3:
                    System.exit(0);
            }
        }
    }

    private void showRestaurantByRegion(int region) {
        List<Restaurant> restaurantList = restaurantService.findByRegion(region);
        if (restaurantList != null) {
            restaurantList.stream().forEach(System.out::println);
            System.out.println("1-See the Menu Of The Restaurant");
            System.out.println("2-Return ");
            int input = GetInput.getIntegerInInterval(1, 2);
            switch (input) {
                case 1:
                    seeMenuOfRestaurant(restaurantList);
                    break;
                case 2:
                    break;
            }
        } else
            System.out.println("No Restaurants NearBy!Try another Area!");
    }

    private void showRestaurantByRegionAndFoodType(int region, FoodType foodType) {
        List<Restaurant> restaurantList = restaurantService.findByRegionAndFoodType(region, foodType);
        if (restaurantList != null) {
            restaurantList.stream().forEach(System.out::println);
            System.out.println("1-See the Menu Of The Restaurant");
            System.out.println("2-Return ");
            int input = GetInput.getIntegerInInterval(1, 2);
            switch (input) {
                case 1:
                    seeMenuOfRestaurant(restaurantList);
                    break;
                case 2:
                    break;
            }
        } else
            System.out.println("No Restaurants with food type NearBy!Try another Area!");
    }

    private void seeMenuOfRestaurant(List<Restaurant> restaurants) {
        seeMenuOfRest:
        while (true) {
            System.out.println("Enter Name Of Restaurant");
            String nameOfRest = GetInput.getStringFromUser();
            List<RestaurantMenu> menus = restaurantMenuService.seeMenuOfRestaurant(nameOfRest);
            if (menus != null) {
                System.out.println("Restaurant Menus:");
                menus.stream().forEach(m -> System.out.println(m.getMenuName()));
                System.out.println("1- Select a menu");
                System.out.println("2- Select another restaurant");
                System.out.println("3- Return");
                int input = GetInput.getInt();
                switch (input) {
                    case 1:
                        selectMenu(menus);
                        break;
                    case 2:
                        continue seeMenuOfRest;
                    case 3:
                        break seeMenuOfRest;
                }
            } else {
                System.out.println("No Menu from " + nameOfRest + "!");
                continue seeMenuOfRest;
            }
        }
    }

    private void selectMenu(List<RestaurantMenu> menus) {
        List<CartDto> shoppingCart = new ArrayList<>();
        selectMenu:
        while (true) {
            System.out.println("Enter the menu name:");
            FoodType menuName = GetInput.getFoodTypeFromUser();
            RestaurantMenu selectedMenu = null;
            for (RestaurantMenu rm : menus) {
                if (rm.getMenuName().equals(menuName)) {
                    selectedMenu = rm;
                    break;
                }
            }
            if (Objects.nonNull(selectedMenu)) {
                selectedMenu.getFoods().entrySet().stream().forEach(e -> System.out.println(e.getKey() + " Count: " + e.getValue()));
                System.out.println("1- Order food");
                System.out.println("2- Select another menu");
                System.out.println("3- Return...");
                int input = GetInput.getInt();
                switch (input) {
                    case 1:
                        orderFoodFromMenu:
                        while (true) {
                            CartDto cartDto = orderFoodFromMenu(selectedMenu);
                            if (Objects.isNull(cartDto))
                                continue selectMenu;
                            shoppingCart.add(cartDto);
                            while (true) {
                                System.out.println("1- Add more food");
                                System.out.println("2- Go to shopping cart");
                                System.out.println("3- Select another menu to order");
                                System.out.println("4- Return...");
                                int input1 = GetInput.getInt();
                                switch (input1) {
                                    case 1:
                                        continue orderFoodFromMenu;
                                    case 2:
                                        seeShoppingCart(shoppingCart, selectedMenu);
                                        break;
                                    case 3:
                                        continue selectMenu;
                                    case 4:
                                        break selectMenu;
                                }
                            }
                        }
                    case 2:
                        continue selectMenu;
                    case 3:
                        break selectMenu;
                }

            }
        }
    }

    private void seeShoppingCart(List<CartDto> shoppingCart, RestaurantMenu restaurantmenu) {
        System.out.println("Your Factor: ");
        shoppingCart.stream().forEach(System.out::println);
        System.out.println("Total Cost: " + reservationTotalCost(shoppingCart, restaurantmenu));
        shoppingCart:
        while (true) {
            System.out.println("1- Edit the number of food");
            System.out.println("2- Delete a food");
            System.out.println("3- Confirm shopping");
            System.out.println("4- Return");
            int input = GetInput.getInt();
            switch (input) {
                case 1:
                    editNumberOfFood(shoppingCart, restaurantmenu);
                    break;
                case 2:
                    deleteFood(shoppingCart, restaurantmenu);
                    break;
                case 3:
                    confirmShopping(shoppingCart, restaurantmenu);
                    break;
                case 4:
                    break shoppingCart;
            }
        }
    }

    private void confirmShopping(List<CartDto> shoppingCart, RestaurantMenu restaurantmenu) {
        Customer customer = customerService.checkUser(phoneNumber);
        if (customer == null) {
            customer = addNewCustomer();
        }
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setRestaurant(restaurantmenu.getRestaurant());
        if(Objects.nonNull(reservationService.getMaxFactorNumber()))
            reservation.setFactorNumber(reservationService.getMaxFactorNumber()+1);
        else
            reservation.setFactorNumber(1);
        reservation.setTotalCost(reservationTotalCost(shoppingCart, restaurantmenu));
        for (int i = 0; i < shoppingCart.size(); i++) {
            reservation.getOrderedFoods().put(shoppingCart.get(i).getFood(), shoppingCart.get(i).getCount());
            restaurantmenu.getFoods().replace(shoppingCart.get(i).getFood(),
                    restaurantmenu.getFoods().get(shoppingCart.get(i).getFood()) - shoppingCart.get(i).getCount());
        }
        reservationService.addNewReservation(reservation);
        restaurantMenuService.update(restaurantmenu);

        shoppingCart.clear();
        customerPanel();
    }

    private int reservationTotalCost(List<CartDto> shoppingCart, RestaurantMenu restaurantmenu) {
        int totalCost = 0;
        for (CartDto cartDto : shoppingCart) {
            totalCost += (cartDto.getFood().getPrice() * cartDto.getCount());
        }
        totalCost += restaurantmenu.getRestaurant().getShippingCost();
        return totalCost;
    }

    private Customer addNewCustomer() {
        System.out.println("Enter Your first name");
        String firstName = GetInput.getStringFromUser();
        System.out.println("Enter your last name ");
        String lastName = GetInput.getStringFromUser();
        System.out.println("Enter your postal code");
        String postalCode = GetInput.getStringFromUser();
        Customer customer = new Customer();
        customer.setFirstname(firstName);
        customer.setLastname(lastName);
        customer.setPostalCode(postalCode);
        customer.setPhoneNumber(phoneNumber);
        customerService.addNewCustomer(customer);
        return customer;
    }

    private void deleteFood(List<CartDto> shoppingCart, RestaurantMenu restaurantmenu) {
        if (!shoppingCart.isEmpty()) {
            System.out.println("Enter food name:");
            String foodName = GetInput.getStringFromUser();
            for (int i = shoppingCart.size() - 1; i >= 0; i--) {
                if (shoppingCart.get(i).getFood().getName().equals(foodName)) {
                    shoppingCart.remove(i);
                    System.out.println("Your Factor: ");
                    shoppingCart.stream().forEach(System.out::println);
                    System.out.println("Total Cost: " + reservationTotalCost(shoppingCart, restaurantmenu));
                    break;
                } else {
                    System.out.println("The food is not found! try again.");
                }
            }
        } else {
            System.out.println("Your shopping cart is empty!");
        }
    }

    private void editNumberOfFood(List<CartDto> shoppingCart, RestaurantMenu restaurantmenu) {
        System.out.println("Enter food name:");
        String foodName = GetInput.getStringFromUser();
        for (CartDto cartDto : shoppingCart) {
            if (cartDto.getFood().getName().equals(foodName)) {
                System.out.println("Enter the number of food:");
                int count = GetInput.getInt();
                if (restaurantmenu.getFoods().get(cartDto.getFood()) >= count) {
                    cartDto.setCount(count);
                } else {
                    System.out.println("There is not enough food!");
                }
                break;
            }
        }
    }


    private CartDto orderFoodFromMenu(RestaurantMenu selectedMenu) {
        CartDto selectedFood = new CartDto();
        while (true) {
            System.out.println("Enter name of food:");
            String foodName = GetInput.getStringFromUser();
            for (Food food : selectedMenu.getFoods().keySet()) {
                if (food.getName().equals(foodName)) {
                    System.out.println("Enter the number of food:");
                    int count = GetInput.getInt();
                    Integer countOfRestFood = selectedMenu.getFoods().get(food);
                    if (count <= countOfRestFood) {
                        selectedFood.setFood(food);
                        selectedFood.setCount(count);
                        return selectedFood;
                    } else {
                        System.out.println("There is not enough food!");
                        return null;
                    }
                }
            }
            System.out.println("The food is not found! try again.");
        }

    }

}
