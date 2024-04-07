package domain;

public class Meal{
    private String name;
    private int cooking_time;
    private String ingredients;

    public Meal(String name, int cooking_time, String ingredients) {
        this.name = name;
        this.cooking_time = cooking_time;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCooking_time() {
        return cooking_time;
    }

    public void setCooking_time(int cooking_time) {
        this.cooking_time = cooking_time;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", cooking_time=" + cooking_time +
                ", ingredients='" + ingredients + '\'' +
                '}';
    }
}