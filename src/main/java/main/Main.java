package main;

import exercise.AGutslewirtschaft;
import exercise.AMarktplatz;
import solution.*;


import java.util.concurrent.atomic.AtomicInteger;

public class Main {



    public static void main(String[] args) {
        System.out.println("Start des Gutslebackens.");

        /* Zähler, um Reihenfolge der Konsolenausgaben nachzuvollziehen */
        AtomicInteger eventnummer = new AtomicInteger(0);

        AMarktplatz marktplatz = new Marktplatz(eventnummer);

        AGutslewirtschaft gutsleWirtschaft = new Gutslewirtschaft(
                marktplatz,
                eventnummer
        );
        gutsleWirtschaft.setzeWirtschaftInGang();
        marktplatz.starteNachschubproduktion();

        System.out.println("Gutslewirtschaft läuft.");
    }

}
