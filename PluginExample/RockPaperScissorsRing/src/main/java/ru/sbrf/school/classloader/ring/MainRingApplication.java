package ru.sbrf.school.classloader.ring;

import ru.sbrf.school.classloader.RockPaperScissorsEnum;
import ru.sbrf.school.classloader.api.PlayableRockPaperScissors;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MainRingApplication {
    public static void main(String[] args) {

        Map<String, Class<?>> pluginsMap = getPlugins();
        Queue<Map.Entry<String, Class<?>>> pluginQueue = new LinkedList<>();

        for (Map.Entry<String, Class<?>> entry : pluginsMap.entrySet()) {
            pluginQueue.add(entry);
        }

        while (pluginQueue.size() > 1) {
            Map.Entry<String, Class<?>> entry1 = pluginQueue.poll();
            Map.Entry<String, Class<?>> entry2 = pluginQueue.poll();

            Class<?> plugin1Class = entry1.getValue();
            Class<?> plugin2Class = entry2.getValue();
            String plugin1Name = entry1.getKey();
            String plugin2Name = entry2.getKey();


            int score1 = 0, score2 = 0;

            for (int round = 1; round <= 3; round++) {
                try {
                    PlayableRockPaperScissors player1 = (PlayableRockPaperScissors) plugin1Class.newInstance();
                    PlayableRockPaperScissors player2 = (PlayableRockPaperScissors) plugin2Class.newInstance();

                    RockPaperScissorsEnum move1 = player1.play();
                    RockPaperScissorsEnum move2 = player2.play();
                    System.out.println("Round " + round + ":");
                    System.out.println("Player 1 (" + plugin1Name + ") plays " + move1);
                    System.out.println("Player 2 (" + plugin2Name + ") plays " + move2);

                    int result = compareMoves(move1, move2);

                    if (result == 1) {
                        score1++;
                    } else if (result == -1) {
                        score2++;
                    }

                    System.out.println("Score after Round " + round + ": Player 1: " + score1 + ", Player 2: " + score2);
                    System.out.println("========================");

                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (score1 > score2) {
                System.out.println(plugin1Name + " wins the match");
                pluginQueue.add(entry1);
            } else if (score2 > score1) {
                System.out.println(plugin2Name + " wins the match");
                pluginQueue.add(entry2);
            } else {
                // In case of a draw
                pluginQueue.add(entry1);
                pluginQueue.add(entry2);
            }
        }

        if (!pluginQueue.isEmpty()) {
            System.out.println("The overall winner is " + pluginQueue.poll().getKey());
        }
    }

    /**
     *
     * @param move1  ход игрока 1
     * @param move2  ход игрока 2
     * @return 1 - победа игрока 1, 0 - ничья, -1 - победа игрока 2
     */
    private static int compareMoves(RockPaperScissorsEnum move1, RockPaperScissorsEnum move2) {
        if (move1 == move2) {
            return 0;
        }
        return switch (move1) {
            case ROCK -> (move2 == RockPaperScissorsEnum.SCISSORS) ? 1 : -1;
            case PAPER -> (move2 == RockPaperScissorsEnum.ROCK) ? 1 : -1;
            case SCISSORS -> (move2 == RockPaperScissorsEnum.PAPER) ? 1 : -1;
        };
    }

    /**
     * @return Получаем весь список плагинов-классов
     */
    private static Map<String, Class<?>> getPlugins() {
        File[] jars = getPluginFiles();
        Map<String, Class<?>> pluginClasses = new HashMap<>();
        for (int i = 0; i < jars.length; i++) {
            try {
                URL jarURL = jars[i].toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL});
                String fileName = jars[i].getName();
                // TODO возможно, classLoader и классы будут удалены GC т.к. на classLoader не будет ссылок
                //  (у любого объекта есть ссылка на definition classLoader)
                Class<?> clazz = classLoader.loadClass("ru.sbrf.school.classloader.player.RockPaperScissorsPlayer");
                pluginClasses.put(fileName, clazz);
            } catch (MalformedURLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return pluginClasses;
    }


    /**
     * @return список файлов-плагинов из папки для плагинов
     */
    private static File[] getPluginFiles() {
        File pluginDir = new File("C:\\Users\\astaf\\Downloads\\Telegram Desktop\\PluginExample\\PluginExample\\plugins");

        return pluginDir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".jar");
            }
        });
    }

}
