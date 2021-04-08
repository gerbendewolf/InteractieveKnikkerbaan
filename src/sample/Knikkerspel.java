package sample;

import javafx.application.Platform;

import java.util.ArrayList;

public class Knikkerspel {
    private int tijd;
    private String naam;
    private ArduinoConnect Arduino;
    private ArrayList<String> ports;
    private String leesPoort;
    private String waardeSerial;
    private ArrayList<ArduinoInit> arduino = new ArrayList<>();

    private Controller controller;



    public Knikkerspel(String naam, Controller c) {
        this.controller=c;
        this.tijd = 0;
        this.naam = naam;
        Arduino = new ArduinoConnect();
        ports = new ArrayList<>();

        for(String port: Arduino.getPorts()) {
            ports.add(port);
        }
        for(String port: ports){
            System.out.println(port);
        }





    }
    public String getLaserWaarde() {
        return waardeSerial;
    }

    public void setWaardeSerial(String waarde) {
        waarde=waarde.replaceAll("\\n", "");

        this.waardeSerial=waarde.substring(2);


       if(waarde.substring(0,2).equals("49")){
           Platform.runLater(()->controller.setWaardeLaser());
        }
        if(waarde.substring(0,2).equals("50")){
            //System.out.println(laserWaarde);
                    Platform.runLater(()->controller.setWaardeLaser2());
        }
        if(waarde.substring(0,2).equals("51")){
            Platform.runLater(()->controller.setWaardeLaser3());

        }
        if(waarde.substring(0,2).equals("52")){
            Platform.runLater(()->controller.setWaardeLaser4());

        }
        if(waarde.substring(0,2).equals("53")){
            Platform.runLater(()->controller.setwaardeLaserStart());

        }
    }

    public String getLeesPoort() {
        return leesPoort;
    }

    public boolean setLeesPoort(String leesPoort) {
        this.leesPoort = leesPoort;
        ArduinoInit n = new ArduinoInit(leesPoort,this);
        arduino.add(n);
        if(n.Init()){
            return true;
        }
        else{
            return false;
        }
    }
    public int getTijd() {
        return tijd;
    }

    public void setTijd(int tijd) {
        this.tijd = tijd;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setPorts(ArrayList<String> ports) {
        this.ports = ports;
    }

    public void resetTellers(){
        for(ArduinoInit n: arduino){

                n.resetTellers();

        }

    }
    public ArrayList<String> getPorts() {

        return ports;
    }
}
