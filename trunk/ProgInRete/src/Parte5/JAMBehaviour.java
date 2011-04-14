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

    void done(){

    }

    boolean isDone(){
        return this.done;
    }

    void setMyThread(Thread myThread){

    }

    void sleep(long ms){

    }

    void action() throws JAMBehaviourInterruptedException {

    }

    void setup() throws JAMBehaviourInterruptedException {

    }

    void dispose() throws JAMBehaviourInterruptedException {
        
    }
}
