package JAM;

/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public abstract class JAMBehaviour implements Runnable{
    private boolean done;
    private Thread myThread;
    private boolean running;
    protected JAMAgent myAgent;

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

    public void setRunning(boolean run){
        this.running = run;
    }

    public void setMyThread(Thread myThread){
        this.myThread = myThread;
    }

    public void sleep(long ms){
        try{
            this.myThread.sleep(ms);
        }catch(InterruptedException e){}
    }

    public abstract void action() throws JAMBehaviourInterruptedException;

    public abstract void setup() throws JAMBehaviourInterruptedException;

    public abstract void dispose() throws JAMBehaviourInterruptedException;

    public void run(){
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
     }
}