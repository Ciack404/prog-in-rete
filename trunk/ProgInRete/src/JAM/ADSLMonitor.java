package JAM;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class ADSLMonitor
 * @author Francesco Alisetta, Mattia Camusso
 * @version 1.0
 */
public class ADSLMonitor extends  JFrame{
    ADSLImpl adsl;
    ButtonPanel panel;
    public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 400;

    public ADSLMonitor(){
        setTitle("ADSL Monitor");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        // add panel to frame
        panel = new ButtonPanel(this);
        add(panel);
    }

    public void updateViewMonitor(String s){
       panel.textMonitor.append("\n" + s);
    }

    class ButtonPanel extends JPanel implements ActionListener{
        JButton StartReg;
        JButton StartUp;
        JButton Shoutdown;
        JButton Exit;
        JLabel label1;
        JLabel label2;
        JTextField textPort;
        JTextArea textMonitor;
        ADSLMonitor monitor;

        public ButtonPanel(ADSLMonitor m){
            monitor = m;
            //impedisce il ridimensionamento della finestra
            setResizable(false);
            // creazione bottoni e label
            StartReg = new JButton("Start reg");
            StartUp = new JButton("Start up");
            StartUp.setEnabled(false);
            Shoutdown = new JButton("Shoutdown");
            Shoutdown.setEnabled(false);
            Exit = new JButton("Exit");
            label1 = new JLabel("port:");
            label2 = new JLabel("Connection \n console:");
            textPort = new JTextField(30);
            textMonitor = new JTextArea(10,30);
            JScrollPane textMessagesScrollPane = new JScrollPane(textMonitor);
            // aggiungere i bottoni e i label al panel
            add(label1);
            add(textPort);
            add(StartReg);
            add(label2);
            add(textMessagesScrollPane, BorderLayout.CENTER);
            add(StartUp);
            add(Shoutdown);
            add(Exit);

            // associo azioni ai bottoni
            // setBackground(Color.BLUE);
            StartReg.addActionListener(this);
            StartUp.addActionListener(this);
            Shoutdown.addActionListener(this);
            Exit.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event){
            try {
                String command = event.getActionCommand();
                if (command.equals("Start reg")){
                    if(textPort.getText().trim().equals("")){//finche non si scrive il numero di porta
                        adsl = new ADSLImpl();
                    }
                    else{
                        Integer n = new Integer(textPort.getText());
                        int numPort = n.intValue();
                        adsl = new ADSLImpl("127.0.0.1",numPort,"ADSL");
                    }
                    adsl.addObserver(monitor);
                    adsl.startRMIRegistry();
                    //textMonitor.setText("Creata ADSL: "+"  rmi://"+"127.0.0.1"+":"+numPort+"/"+ "ADSL");
                    StartReg.setEnabled(false);//disabilita il bottone
                    StartUp.setEnabled(true);
                }
                else if (command.equals("Start up")){
                    adsl.startADSL();
                    StartUp.setEnabled(false);
                    //textMonitor.append("\n agganciata la ADSL");
                    /*abilita il bottone della stop adsl*/
                    Shoutdown.setEnabled(true);
                }
            else if (command.equals("Shoutdown")){
                adsl.stopADSL();
                Shoutdown.setEnabled(false);
                //textMonitor.append("\n sganciata la ADSL");
                /*reabilita il bottone della start up*/
                StartUp.setEnabled(true);
            }
            else if (command.equals("Exit")){
                adsl.removeObserver(monitor);
                System.exit(0);
            }
            }catch(Exception e){
                System.err.println("lanciata ecc in monitor");
                System.exit(1);
            }
        }
    }
}
