// TOLTO IL METODO RUN: C'È GIÀ SIA IN WHILEBEHAVIOUR CHE IN SIMPLEBEHAVIOUR

package JAM;

import java.io.*;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

/**
 * Class JAMBehaviour
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public abstract class JAMBehaviour implements Runnable{
    boolean done;
    Thread myThread;
    boolean running;
    public JAMAgent myAgent;

    public JAMBehaviour(JAMAgent agent){
        this.myAgent = agent;
        this.done = false;
    }

    public void done(){
        this.done = true;
        this.myThread.interrupt();
    }

    public boolean isDone(){
        return this.done;
    }

    public void setDone(boolean done){
        this.done = done;
    }

    public boolean isRunning(){
        return this.running;
    }

    public void setRunning(boolean run){
        this.running = run;
    }

    public void setMyThread(Thread myThread){
        this.myThread = myThread;
    }

    public void sleep(long ms) throws JAMBehaviourInterruptedException{
        try{
            this.myThread.sleep(ms);
        }catch(InterruptedException e){
            throw new JAMBehaviourInterruptedException();
        }
    }

    public abstract void action() throws JAMBehaviourInterruptedException;

    public abstract void setup() throws JAMBehaviourInterruptedException;

    public abstract void dispose() throws JAMBehaviourInterruptedException;

    /*public void run(){
        try{
            setup();
	    action();
        }catch(JAMBehaviourInterruptedException err){
	    System.out.println(err);
	}finally{
	    try{
	        dispose();
	    } catch(JAMBehaviourInterruptedException err){
	          System.out.println(err);
	    }
	}
     }*/
}
