package com.example.demo;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Recipe;
import com.example.demo.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@EnableTransactionManagement
public class DemoApplication implements CommandLineRunner {

	private final RecipeService recipeService;

	@Autowired
    public DemoApplication(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		recipeService.addRecipes();
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
		System.out.println("Введите имя рецепта:");
		String exactName = scanner.nextLine();
		recipeService.deleteRecipeByName(exactName);
	}


	@Transactional(readOnly = true)
	public void findRecipe(Scanner scanner) {
		System.out.println("Искать по точному имени? (да/нет):");
		String searchType = scanner.nextLine().trim().toLowerCase();

		List<Recipe> recipes;
		if (searchType.equals("да")) {
			System.out.println("Введите точное имя рецепта:");
			String exactName = scanner.nextLine();
			recipes = recipeService.findRecipesByExactName(exactName);
		} else {
			System.out.println("Введите часть имени рецепта:");
			String namePart = scanner.nextLine();
			recipes = recipeService.findRecipesByPartOfName(namePart);
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

		Recipe recipe = new Recipe();
		recipe.setName(recipeName);
		recipe.setIngredients(new ArrayList<>());

		while (true) {
			System.out.println("Добавить ингредиент? (да/нет):");
			String answer = scanner.nextLine().trim().toLowerCase();
			if (!answer.equals("да")) {
				break;
			}

			System.out.println("Введите название ингредиента:");
			String ingredientName = scanner.nextLine();

			System.out.println("Введите количество ингредиента (число):");
			double quantity = Double.parseDouble(scanner.nextLine()); // В реальном приложении стоит добавить обработку исключения

			System.out.println("Введите единицу измерения ингредиента:");
			String unit = scanner.nextLine();

			Ingredient ingredient = new Ingredient();
			ingredient.setName(ingredientName);
			ingredient.setQuantity(quantity);
			ingredient.setUnit(unit);
			ingredient.setRecipe(recipe);
			recipe.getIngredients().add(ingredient);
		}

		recipeService.addRecipe(recipe);
		System.out.println("Рецепт '" + recipeName + "' успешно добавлен.");
	}


}
