package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {
    public Label helloWorld;
    public TextField naamSpel;
    public ListView<String> poorten;
    public Label waardeLaser;
    public Label waardeLaser2;
    public Label Arduino1;
    public Label Arduino2;
    public Label waardeLaser3;
    public Label waardeLaser4;
    public Label waardeLaserStart;
    public Label ArduinoStart;
    private String PoortStart;
    private String Poort1;
    private String Poort2;
    Knikkerspel ks;

    ObservableList<String> observableList = FXCollections.observableArrayList();
    //ObservableValue<String> laserWaardeObs;


   // public Controller(Label helloWorld, TextField naamSpel) {
    //    this.helloWorld = helloWorld;
     //   this.naamSpel = naamSpel;
    //}



    public void startKnikerspel(ActionEvent actionEvent) {
        ks= new Knikkerspel(naamSpel.getText(),this);
        helloWorld.setText("Spel: "+naamSpel.getText());
        setListView();

        waardeLaser.setText("Laser1 = 0");
        waardeLaser2.setText("Laser2 = 0");
        waardeLaser3.setText("Laser3 = 0");
        waardeLaser4.setText("Laser4 = 0");
        waardeLaserStart.setText("LaserS = 0");
        //initialize();
    }

    public void setWaardeLaser() {
        //laserWaardeObs= new ReadOnlyObjectWrapper<>(ks.getLaserWaarde());
        waardeLaser.setText("Laser1 = "+ks.getLaserWaarde());
        //Platform.runLater(()->this.waardeLaser.textProperty().bind(laserWaardeObs));

    }

    public void setWaardeLaser2() {


        waardeLaser2.setText("Laser2 = "+ks.getLaserWaarde());

    }

    public void setArduino2(Label arduino2) {
        Arduino2 = arduino2;
    }

    public void setWaardeLaser3() {
        waardeLaser3.setText("Laser3 = "+ks.getLaserWaarde());
    }

    public void setWaardeLaser4() {
        waardeLaser4.setText("Laser4 = "+ks.getLaserWaarde());
    }
    public void setwaardeLaserStart() {
        waardeLaserStart.setText("LaserStart = "+ks.getLaserWaarde());
    }
    public void setListView()
    {
        for(String port: ks.getPorts()) {
            observableList.add(port);

        }
        poorten.setItems(observableList);

    }

    public ListView<String> getPoorten() {
        return poorten;
    }

    public void setPoorten(ListView<String> poorten) {
        this.poorten = poorten;
    }

    public void SelectCom1(ActionEvent actionEvent) {
        Poort1=poorten.getSelectionModel().getSelectedItem();

        if(ks.setLeesPoort(Poort1)){
            Arduino1.setText("Arduino1: "+Poort1);

        }
        else{
            Arduino1.setText("Arduino1 connectie "+Poort1+" niet gelukt.");

        }

    }

    public void ResetTellers(ActionEvent actionEvent) {
        ks.resetTellers();
    }

    public void SelectCom2(ActionEvent actionEvent) {
        Poort2=poorten.getSelectionModel().getSelectedItem();

        if(ks.setLeesPoort(Poort2)){
            Arduino2.setText("Arduino2: "+Poort2);

        }
        else{
            Arduino2.setText("Arduino2 connectie "+Poort2+" niet gelukt.");

        }

    }

    public void SelectComStart(ActionEvent actionEvent) {
        this.PoortStart=poorten.getSelectionModel().getSelectedItem();

        if(ks.setLeesPoort(PoortStart)){
            ArduinoStart.setText("ArduinoStart: "+PoortStart);

        }
        else{
            ArduinoStart.setText("ArduinoStart connectie "+PoortStart+" niet gelukt.");

        }
    }

    @FXML private Label time;

    private int minute;
    private int hour;
    private int second;


   /* public void initialize() {

        Thread clock = new Thread() {
            public void run() {
                Calendar start = Calendar.getInstance();

                for (int i=0;i<10000;i++) {
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    Calendar cal = Calendar.getInstance();
                    //Calendar diff=cal.-start;
                    second = cal.get(Calendar.SECOND);
                    minute = cal.get(Calendar.MINUTE);
                    hour = cal.get(Calendar.HOUR);
                    //System.out.println(hour + ":" + (minute) + ":" + second);
                    Platform.runLater(()->time.setText(hour + ":" + (minute) + ":" + second));

                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        //...
                    }
                }
            }
        };
        clock.start();
    }*/
}

