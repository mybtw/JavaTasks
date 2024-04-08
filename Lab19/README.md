<h2> Логи </h2>

Выберите действие: 1 - Добавить рецепт, 2 - Найти рецепт, 3 - Удалить рецепт, 4 - Выход \
2 \
Искать по точному имени? (да/нет): \
да \
Введите точное имя рецепта: \
Оливье \
Hibernate: select r1_0.id,r1_0.name from recipe r1_0 where r1_0.name=? \
Hibernate: select i1_0.recipe_id,i1_0.id,i1_0.name,i1_0.quantity,i1_0.unit from ingredient i1_0 where i1_0.recipe_id=? \
Найден рецепт: Оливье с ингредиентами: \
- Картофель, 0.200 кг \
- Морковь, 0.100 кг \
- Колбаса, 0.200 кг \
  Выберите действие: 1 - Добавить рецепт, 2 - Найти рецепт, 3 - Удалить рецепт, 4 - Выход \
  1 \
  Введите название рецепта: \
  Бутеры \
  Добавить ингредиент? (да/нет): \
  да \
  Введите название ингредиента: \
  Колбаса \
  Введите количество ингредиента (число): \
  0.5 \
  Введите единицу измерения ингредиента: \
  кг \
  Добавить ингредиент? (да/нет): \
  да \
  Введите название ингредиента: \
  Хлеб \
  Введите количество ингредиента (число): \
  1 \
  Введите единицу измерения ингредиента: \
  кг \
  Добавить ингредиент? (да/нет): \
  нет \
  Hibernate: insert into recipe (name,id) values (?,default) \
  Hibernate: insert into ingredient (name,quantity,recipe_id,unit,id) values (?,?,?,?,default) \
  Hibernate: insert into ingredient (name,quantity,recipe_id,unit,id) values (?,?,?,?,default) \
  Рецепт 'Бутеры' успешно добавлен. \
  Выберите действие: 1 - Добавить рецепт, 2 - Найти рецепт, 3 - Удалить рецепт, 4 - Выход \
  2 \
  Искать по точному имени? (да/нет): \
  нет \
  Введите часть имени рецепта: \
  Бут \
  Hibernate: select r1_0.id,r1_0.name from recipe r1_0 where r1_0.name like ? escape '\' \
  Hibernate: select i1_0.recipe_id,i1_0.id,i1_0.name,i1_0.quantity,i1_0.unit from ingredient i1_0 where i1_0.recipe_id=? \
  Найден рецепт: Бутеры с ингредиентами: \
- Колбаса, 0.500 кг \
- Хлеб, 1.000 кг \
  Выберите действие: 1 - Добавить рецепт, 2 - Найти рецепт, 3 - Удалить рецепт, 4 - Выход \
  3 \
  Введите имя рецепта: \
  Бутеры \
  Hibernate: select r1_0.id,r1_0.name from recipe r1_0 where r1_0.name=? \
  Hibernate: select i1_0.recipe_id,i1_0.id,i1_0.name,i1_0.quantity,i1_0.unit from ingredient i1_0 where i1_0.recipe_id=? \
  Hibernate: delete from ingredient where id=? \
  Hibernate: delete from ingredient where id=? \
  Hibernate: delete from recipe where id=? \
  Выберите действие: 1 - Добавить рецепт, 2 - Найти рецепт, 3 - Удалить рецепт, 4 - Выход \
  2 \
  Искать по точному имени? (да/нет): \
  нет \
  Введите часть имени рецепта: \
  Бутеры \
  Hibernate: select r1_0.id,r1_0.name from recipe r1_0 where r1_0.name like ? escape '\' \
  Рецепт(ы) не найден. \
  Выберите действие: 1 - Добавить рецепт, 2 - Найти рецепт, 3 - Удалить рецепт, 4 - Выход \
  2 \
  Искать по точному имени? (да/нет): \
  да \
  Введите точное имя рецепта: \
  Оливье \
  Hibernate: select r1_0.id,r1_0.name from recipe r1_0 where r1_0.name=? \
  Hibernate: select i1_0.recipe_id,i1_0.id,i1_0.name,i1_0.quantity,i1_0.unit from ingredient i1_0 where i1_0.recipe_id=? \
  Найден рецепт: Оливье с ингредиентами: \
- Картофель, 0.200 кг \
- Морковь, 0.100 кг \
- Колбаса, 0.200 кг \
  Выберите действие: 1 - Добавить рецепт, 2 - Найти рецепт, 3 - Удалить рецепт, 4 - Выход \