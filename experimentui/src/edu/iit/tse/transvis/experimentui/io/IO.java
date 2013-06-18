/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.tse.transvis.experimentui.io;

import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author dtripathy10
 */
public class IO {

    private static volatile IO instance = null;
    //member variable
    InputOutput io;
    private IO() {
        io = IOProvider.getDefault().getIO("Experiment", true);
    }

    public static IO getInstance() {
        if (instance == null) {
            synchronized (IO.class) {
                if (instance == null) {
                    instance = new IO();
                }
            }
        }
        return instance;
    }
    public  InputOutput getInputOutput() {
        io.closeInputOutput();
        return io;
    }
}
