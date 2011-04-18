package Parte5;

import eccezioni.*;

/**
 * Class MessageBoxNoSync
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class JAMSimpleBehaviour extends JAMBehaviour {

    public JAMSimpleBehaviour(JAMAgent age){
        super(age);
    }

    public void run(){
        try{
            this.setup();
            this.action();
        }catch(JAMBehaviourException err){
            if(this.isDone())   return;
            System.out.println(err);
        }finally{
            try{
                this.dispose();
            }catch(JAMBehaviourException err){
                System.out.println(err);
            }
        }
    }

    /**
     *
     * @throws JAMBehaviourInterruptedException
     */
    public void action() throws JAMBehaviourInterruptedException {

    }

    /**
     *
     * @throws JAMBehaviourInterruptedException
     */
    public void setup() throws JAMBehaviourInterruptedException {

    }

    /**
     *
     * @throws JAMBehaviourInterruptedException
     */
    public void dispose() throws JAMBehaviourInterruptedException {

    }
}
