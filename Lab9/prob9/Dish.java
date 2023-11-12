package prob9;
import java.util.*;

public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public enum Type { MEAT, FISH, OTHER }

    @Override
    public String toString() {
        return name;
    }

    public static final List<Dish> menu =
            Arrays.asList( new Dish("pork", false, 800, Dish.Type.MEAT),
                           new Dish("beef", false, 700, Dish.Type.MEAT),
                           new Dish("chicken", false, 400, Dish.Type.MEAT),
                           new Dish("french fries", true, 530, Dish.Type.OTHER),
                           new Dish("rice", true, 350, Dish.Type.OTHER),
                           new Dish("season fruit", true, 120, Dish.Type.OTHER),
                           new Dish("pizza", true, 550, Dish.Type.OTHER),
                           new Dish("prawns", false, 400, Dish.Type.FISH),
                           new Dish("salmon", false, 450, Dish.Type.FISH));
    
 // a. Is there any Vegetarian meal available (return type boolean)
    public static boolean isVegetarianMealAvailable() {
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    // b. Is there any healthy menu have calories less than 1000 (return type boolean)
    public static boolean isHealthyMenuAvailable() {
        return menu.stream().anyMatch(dish -> dish.getCalories() < 1000);
    }

    // c. Is there any unhealthy menu have calories greater than 1000 (return type boolean)
    public static boolean isUnhealthyMenuAvailable() {
        return menu.stream().anyMatch(dish -> dish.getCalories() > 1000);
    }

    // d. Find and return the first item for the type of MEAT (return type Optional<Dish>)
    public static Optional<Dish> findFirstMeatDish() {
        return menu.stream().filter(dish -> dish.getType() == Type.MEAT).findFirst();
    }

    // e. Calculate total calories in the menu using reduce (return type int)
    public static int calculateTotalCalories() {
        return menu.stream().mapToInt(Dish::getCalories).reduce(0, Integer::sum);
    }

    // f. Calculate total calories in the menu using Method References (return type int)
    public static int calculateTotalCaloriesMethodReference() {
        return menu.stream().mapToInt(Dish::getCalories).sum();
    }
    
	public static void main(String[] args) {
        System.out.println("Is there any Vegetarian meal available? " + Dish.isVegetarianMealAvailable());
        System.out.println("Is there any healthy menu available? " + Dish.isHealthyMenuAvailable());
        System.out.println("Is there any unhealthy menu available? " + Dish.isUnhealthyMenuAvailable());

        Optional<Dish> firstMeatDish = Dish.findFirstMeatDish();
        System.out.println("First Meat Dish: " + firstMeatDish.orElse(null));

        System.out.println("Total Calories using reduce: " + Dish.calculateTotalCalories());
        System.out.println("Total Calories using Method References: " + Dish.calculateTotalCaloriesMethodReference());
   }
}

