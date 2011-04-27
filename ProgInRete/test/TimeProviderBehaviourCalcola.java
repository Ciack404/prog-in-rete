import JAM.*;

public class TimeProviderBehaviourCalcola extends JAMWhileBehaviour {
    public TimeProviderBehaviourCalcola(JAMAgent myAgent) {
        super(myAgent);
    }
    public void setup() throws JAMBehaviourInterruptedException {
        ((TimeProviderAgent)myAgent).resetOra();
    }
    public void dispose() throws JAMBehaviourInterruptedException { }
    public void action() throws JAMBehaviourInterruptedException {
        ((TimeProviderAgent)myAgent).incrementaOra();
        sleep(1000);
    }
}
