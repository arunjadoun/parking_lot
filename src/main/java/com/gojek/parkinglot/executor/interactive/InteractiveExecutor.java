package com.gojek.parkinglot.executor.interactive;

import com.gojek.parkinglot.abstractions.Executor;
import com.gojek.parkinglot.handler.HandlerFactory;
import com.gojek.parkinglot.request.Command;

public class InteractiveExecutor implements Executor {

    public void execute(String cmd){
        if (cmd != null && !cmd.trim().isEmpty()) {
            Command commandWrapper = Command.getCommandWrapperFromString(cmd);
            if(commandWrapper!=null) {
                HandlerFactory.getCommandHandler(commandWrapper).handle();
            }else{
                System.out.println("Invalid command");
            }
        }else {
            System.out.println("Invalid command");
        }
    }
}
