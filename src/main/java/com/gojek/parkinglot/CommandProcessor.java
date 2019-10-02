package com.gojek.parkinglot;

import com.gojek.parkinglot.abstractions.Executor;
import com.gojek.parkinglot.executor.ExecutorFactory;
import com.gojek.parkinglot.executor.ExecutorType;

import java.util.Scanner;

public class CommandProcessor {

    public static void main(String[] args) {
        Executor commandExecutor = null;
        if (args.length > 0) {
            commandExecutor = ExecutorFactory.getCommandExecutor(ExecutorType.FILE);
            commandExecutor.execute(args[0]);
        } else {
            commandExecutor = ExecutorFactory.getCommandExecutor(ExecutorType.INTERACTIVE);
            while (true) {
                Scanner in = new Scanner(System.in);
                String nextCommand = in.nextLine();
                if (nextCommand != null && !nextCommand.trim().isEmpty()) {
                    commandExecutor.execute(nextCommand.trim());
                }
            }
        }
    }
}