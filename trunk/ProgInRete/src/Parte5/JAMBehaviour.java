package Parte5;

import eccezioni.*;

/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public abstract class JAMBehaviour implements Runnable{
    private boolean done;
    private Thread myThread;
    protected JAMAgent myAgent;

    public JAMBehaviour(JAMAgent agent){
        this.myAgent = agent;
        this.done = false;
    }

    public void done(){
        this.done = true;
    }

    public boolean isDone(){
        return this.done;
    }

    public void setMyThread(Thread myThread){
        this.myThread = myThread;
    }

    public void sleep(long ms) throws InterruptedException {
        this.myThread.sleep(ms);
    }

    public void action() throws JAMBehaviourInterruptedException {

    }

    public void setup() throws JAMBehaviourInterruptedException {

    }

    public void dispose() throws JAMBehaviourInterruptedException {
        
    }
}
