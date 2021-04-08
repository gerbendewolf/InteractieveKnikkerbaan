package sample;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class ArduinoInit {
    private Knikkerspel ks;
    private PrintWriter out;
    private String port;
    public void resetTellers(){
        try {
            out.println(1);
        }
        catch(Exception e){
            System.out.println(e.getMessage()+"poort "+port+" doet het niet.");
        }

    }
    public ArduinoInit(String port, Knikkerspel ks){
        this.ks=ks;
        this.port=port;

    }

    public boolean Init(){
        SerialPort userPort = SerialPort.getCommPort(port);
        userPort.openPort();
        if (userPort.isOpen()) {
            System.out.println("Port "+port+" initialized!");
            //timeout not needed for event based reading
            //userPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
        } else {
            System.out.println("Port "+port+" not available");
            return false;
        }
        out = new PrintWriter(userPort.getOutputStream(),true);


        userPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                byte[] newData = new byte[userPort.bytesAvailable()];
                String s="";
                int numRead=0;
                //while(!compleet) {
                numRead = userPort.readBytes(newData, newData.length);

                for (int i = 0; i < numRead; i++) {
                    //System.out.print( (char)newData[i]);
                    s += String.valueOf(newData[i]);
                    //System.out.print(String.valueOf(newData[i]));

                }
                //}
                //
                System.out.println(s);
                if(s.length()>2) {
                    ks.setWaardeSerial((s));
                }


            }
        });
        return true;
    }

}
