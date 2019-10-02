package com.gojek.parkinglot.executor.file;

import com.gojek.parkinglot.abstractions.Executor;
import com.gojek.parkinglot.handler.HandlerFactory;
import com.gojek.parkinglot.request.Command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileBasedExecutor implements Executor {

    public void execute(String filePath) {
        try {

            Files.lines(Paths.get(filePath)).forEach(cs->{
                Command command=Command.getCommandWrapperFromString(cs);
                if(command!=null){
                    HandlerFactory.getCommandHandler(command).handle();
                }else{
                    System.out.println("Invalid Command");
                }
            });

        } catch (IOException e) {
            //Error in reading the file
        }

    }
}