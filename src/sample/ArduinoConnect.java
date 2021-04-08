package sample;

import com.fazecast.jSerialComm.SerialPort;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ArduinoConnect {
    public static SerialPort userPort;
    static InputStream in;
    private SerialPort ports[];

    public ArrayList<String> getPorts() {
        ArrayList<String> portsS = new ArrayList<>();
        for (SerialPort port : ports) {
            portsS.add(port.getSystemPortName());
        }
        return portsS;
    }

    public ArduinoConnect() {


        // Use the PortChooser from before. Pop up the JDialog.
        ports = SerialPort.getCommPorts();
        int i = 1;
        //User port selection
        System.out.println("COM Ports available on machine");




        //Initializing port


    }


}
