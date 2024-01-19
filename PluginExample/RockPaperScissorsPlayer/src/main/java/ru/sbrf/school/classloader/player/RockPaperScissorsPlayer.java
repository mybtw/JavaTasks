package ru.sbrf.school.classloader.player;

import ru.sbrf.school.classloader.RockPaperScissorsEnum;
import ru.sbrf.school.classloader.api.PlayableRockPaperScissors;

import java.util.Random;

public class RockPaperScissorsPlayer implements PlayableRockPaperScissors {
    @Override
    public RockPaperScissorsEnum play() {

        var options = RockPaperScissorsEnum.values();

        int optionNumber = getRandomNumber();

        return options[optionNumber];
    }

    public static int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(3);
    }
}
