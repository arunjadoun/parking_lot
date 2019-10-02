package com.gojek.parkinglot.executor;

import com.gojek.parkinglot.abstractions.Executor;
import com.gojek.parkinglot.executor.file.FileBasedExecutor;
import com.gojek.parkinglot.executor.interactive.InteractiveExecutor;

public class ExecutorFactory {

    private static final Executor fileBasedExecutor = new FileBasedExecutor();
    private static final Executor interactiveExecutor = new InteractiveExecutor();

    /**
     * Factory method to return filebased/interative based interactive executor
     * @param commandExecutor
     * @return CommandExecutor
     */
    public static Executor getCommandExecutor(ExecutorType commandExecutor) {
        switch (commandExecutor) {
            case FILE:
                return fileBasedExecutor;
            default:
                return interactiveExecutor;
        }
    }
}
