package org.xml.demo.ui.states;

import org.xml.demo.ui.Constance;

import java.io.*;

public class Memento implements IApplicationWindowStateManager {
    private ApplicationWindowState applicationWindowState;


    public void saveApplicationWindowState(ApplicationWindowState applicationWindowState)  {
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(Constance.WORKSPASE_FILE_NAME));
            objectOutputStream.writeObject(applicationWindowState);
            objectOutputStream.close();
        }catch (ClassCastException e){
            System.out.println("File isn't found");
        }
        catch (IOException e){
            System.out.println("Ups");
        }
    }

    @Override
    public ApplicationWindowState provideState() {
        return applicationWindowState;
    }

    @Override
    public void changeState(ApplicationWindowState state) {
        applicationWindowState = state;
    }
}
