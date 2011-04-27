package JAM;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class JAMAgentMonitor
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class JAMAgentMonitor extends JFrame implements Observer{
    ButtonPanel panel;
    private JAMAgent jama;
    public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 400;

    public JAMAgentMonitor(JAMAgent jam){
        jama = jam;
        setTitle("JAMAgent Monitor");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        // add panel to frame
        panel = new ButtonPanel(this);
        add(panel);
        jama.addObserver(this);
    }


    public void update(Observable o, Object arg) {
       String log = (String)arg;
       panel.textMonitor.append("\n" + log);
    }


    /**Invoca il metodo init sullÕoggetto agent
      */
      void initJAMAgent(){
          try{
              jama.init();
	  }
	  catch(Exception e){
	      //throw new JAMADSLException(e);
              System.err.println("lanciata ecc in monitor");
	      System.exit(1);
          }
      }


      /**Invoca il metodo start sullÕoggetto agent.
        */
      void startJAMAgent(){
          try{
	      jama.start();
	  }
	  catch(Exception e){
	      //throw new JAMADSLException(e);
	      System.err.println("lanciata ecc in monitor");
              System.exit(1);
	  }
      }


    /**invoca il metodo destroy sullÕoggetto agent
      */
    void destroyJAMAgent(){
	    try{
		    jama.destroy();
		}
	        catch(Exception e){
                    //throw new JAMADSLException(e);
                    System.err.println("lanciata ecc in monitor");
                    System.exit(1);
	    }
	}



    class ButtonPanel extends JPanel implements ActionListener{
        JButton Init;
        JButton Start;
        JButton Destroy;
        JButton Exit;
        JLabel label;
        JTextArea textMonitor;
        JAMAgentMonitor monitor;

        public ButtonPanel(JAMAgentMonitor m){
            monitor = m;
            //impedisce il ridimensionamento della finestra
            setResizable(false);
            // creazione bottoni e label
            Init = new JButton("Init");
            Start = new JButton("Start");
            Start.setEnabled(false);
            Destroy = new JButton("Destroy");
            Destroy.setEnabled(false);
            Exit = new JButton("Exit");
            label = new JLabel("Connection console:");
            textMonitor = new JTextArea(10,30);
            JScrollPane textMessagesScrollPane = new JScrollPane(textMonitor);

            // aggiungere i bottoni e i label al panel
            add(label);
            add(textMessagesScrollPane, BorderLayout.CENTER);
            add(Init);
            add(Start);
            add(Destroy);
            add(Exit);

            // associo azioni ai bottoni
            // setBackground(Color.BLUE);
            Init.addActionListener(this);
            Start.addActionListener(this);
            Destroy.addActionListener(this);
            Exit.addActionListener(this);
       }

    /**Riceve un evento come parametro e indirizza l'azione da svolgere
      *@param ActionEvent event
      */
        public void actionPerformed(ActionEvent event){

           try {
               String command = event.getActionCommand();
               if (command.equals("Init")){
                   monitor.initJAMAgent();
                   Init.setEnabled(false);
                   Destroy.setEnabled(true);
                   Start.setEnabled(true);
              }
              else if (command.equals("Start")){
              //    monitor.startJAMAgent();
                  }
              else if (command.equals("Destroy")){
                  monitor.destroyJAMAgent();
                  Init.setEnabled(true);
                  Destroy.setEnabled(false);
                  Start.setEnabled(false);
              }
              else if (command.equals("Exit")){
                  jama.deleteObserver(monitor);
                  System.exit(0);
              }
          }catch(Exception e){
              //throw new JAMADSLException(e);
              System.err.println("lanciata ecc in monitor");
              System.exit(1);
          }

       }
    }
}
