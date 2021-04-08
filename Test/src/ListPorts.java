import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import java.util.Enumeration;

public class ListPorts {
    public static void main(String args[]) {
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();

        while (ports.hasMoreElements()) {

            System.out.println("test");
            CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
            String type;
            switch (port.getPortType()) {
                case CommPortIdentifier.PORT_PARALLEL:
                    type = "Parallel";
                    break;
                case CommPortIdentifier.PORT_SERIAL:
                    type = "Serial";
                    break;
                default: /// Shouldn't happen
                    type = "Unknown";
                    break;
            }
            System.out.println(port.getName() + ": " + type);
        }
        try {
            CommPortIdentifier port11 = CommPortIdentifier.getPortIdentifier("COM11");
        }
        catch(NoSuchPortException e){
            System.out.println(e.getMessage());
        }
    }
}