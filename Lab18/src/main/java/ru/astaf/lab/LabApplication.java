package ru.astaf.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.astaf.lab.model.Ingredient;
import ru.astaf.lab.model.Recipe;
import ru.astaf.lab.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LabApplication implements CommandLineRunner {
    private final RecipeRepository recipeRepository;

    @Autowired
    public LabApplication(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LabApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        recipeRepository.initDb();
        recipeRepository.addRecipes();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("Выберите действие: 1 - Добавить рецепт, 2 - Найти рецепт, 3 - Удалить рецепт, 4 - Выход");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        addRecipe(scanner);
                        break;
                    case "2":
                        findRecipe(scanner);
                        break;
                    case "3":
                        deleteRecipe(scanner);
                        break;
                    case "4":
                        running = false;
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            }
        }
    }

    private void deleteRecipe(Scanner scanner) {
        try {
            System.out.println("Введите имя рецепта:");
            String exactName = scanner.nextLine();
            recipeRepository.deleteRecipeByName(exactName);
            System.out.println(exactName + " удален");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void findRecipe(Scanner scanner) {
        System.out.println("Искать по точному имени? (да/нет):");
        String searchType = scanner.nextLine().trim().toLowerCase();

        List<Recipe> recipes;
        if (searchType.equals("да")) {
            System.out.println("Введите точное имя рецепта:");
            String exactName = scanner.nextLine();
            recipes = recipeRepository.findRecipesByExactName(exactName);
        } else {
            System.out.println("Введите часть имени рецепта:");
            String namePart = scanner.nextLine();
            recipes = recipeRepository.findRecipesByPartOfName(namePart);
        }

        if (recipes.isEmpty()) {
            System.out.println("Рецепт(ы) не найден.");
        } else {
            recipes.forEach(recipe -> {
                System.out.println("Найден рецепт: " + recipe.getName() + " с ингредиентами:");
                recipe.getIngredients().forEach(ingredient ->
                        System.out.println("- " + ingredient.getName() + ", " + String.format("%.3f", ingredient.getQuantity()) + " " + ingredient.getUnit()));
            });
        }
    }

    private void addRecipe(Scanner scanner) {
        System.out.println("Введите название рецепта:");
        String recipeName = scanner.nextLine();

        List<Ingredient> ingredients = new ArrayList<>();
        while (true) {
            System.out.println("Добавить ингредиент? (да/нет):");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (!answer.equals("да")) {
                break;
            }

            System.out.println("Введите название ингредиента:");
            String ingredientName = scanner.nextLine();

            System.out.println("Введите количество ингредиента (число):");
            double quantity;
            try {
                quantity = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод, попробуйте снова.");
                continue;
            }

            System.out.println("Введите единицу измерения ингредиента:");
            String unit = scanner.nextLine();

            ingredients.add(new Ingredient(ingredientName, quantity, unit));
        }

        Recipe recipe = new Recipe(recipeName, ingredients);
        recipeRepository.addRecipe(recipe);
        System.out.println("Рецепт '" + recipeName + "' успешно добавлен.");
    }

}

