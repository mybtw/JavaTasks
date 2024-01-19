package ru.sbrf.school.classloader.api;

import ru.sbrf.school.classloader.RockPaperScissorsEnum;

@FunctionalInterface
public interface PlayableRockPaperScissors { // типа Plugin
    RockPaperScissorsEnum play(); // типа invoke
}
