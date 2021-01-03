package solution;

import exercise.AGutslewirtschaft;
import exercise.AMarktplatz;
import exercise.Marktinventar;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Gutslewirtschaft extends AGutslewirtschaft {
    public Gutslewirtschaft(AMarktplatz marktplatz, AtomicInteger eventnummer) {
        inventarMutex = new Semaphore(1);
        marktinventar = new Marktinventar();
        marktsignalMandelbäckerSem = new Semaphore(1);
        marktsignalSchokoladenbäckerSem = new Semaphore(1);
        marktsignalZuckerbäckerSem= new Semaphore(1);
        mandelbäcker = new BäckerMitEinerZutat(marktsignalMandelbäckerSem, marktplatz.getMarkplatzSem(), "Mandelhof", eventnummer);
        zuckerbäcker = new BäckerMitEinerZutat(marktsignalZuckerbäckerSem, marktplatz.getMarkplatzSem(), "Zuckerfeld", eventnummer);
        schokoladenbäcker = new BäckerMitEinerZutat(marktsignalSchokoladenbäckerSem, marktplatz.getMarkplatzSem(), "Schokoladental", eventnummer);
        marktbeobachterMandeln = new MarktbeobachterMandel(marktplatz.getMandelnSem(), marktsignalZuckerbäckerSem, marktsignalSchokoladenbäckerSem, inventarMutex, marktinventar);
        marktbeobachterZucker = new MarktbeobachterZucker(marktplatz.getZuckerSem(), marktsignalMandelbäckerSem, marktsignalSchokoladenbäckerSem, inventarMutex, marktinventar);
        marktbeobachterSchokolade = new MarktbeobachterSchokolade(marktplatz.getSchokoladenSem(), marktsignalZuckerbäckerSem, marktsignalMandelbäckerSem, inventarMutex, marktinventar);

    }

    @Override
    public void setzeWirtschaftInGang() {
        try {
            marktsignalZuckerbäckerSem.acquire();
            marktsignalMandelbäckerSem.acquire();
            marktsignalSchokoladenbäckerSem.acquire();
        } catch (InterruptedException e) {
        }
        mandelbäcker.start();
        zuckerbäcker.start();
        schokoladenbäcker.start();
        marktbeobachterMandeln.start();
        marktbeobachterSchokolade.start();
        marktbeobachterZucker.start();
    }
}
