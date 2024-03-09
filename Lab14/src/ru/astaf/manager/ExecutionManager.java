package ru.astaf.manager;

import ru.astaf.context.Context;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}

