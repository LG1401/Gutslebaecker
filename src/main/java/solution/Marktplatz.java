package solution;

import exercise.AMarktplatz;
import exercise.Nachschub;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Marktplatz extends AMarktplatz {

    protected AtomicInteger eventnummer;

    public Marktplatz(AtomicInteger eventnummer) {
        markplatzSem = new Semaphore(1);
        this.eventnummer = eventnummer;
        nachschubMandelZucker = new Nachschub(this.markplatzSem, mandelnSem, zuckerSem, "Mandeln und Zucker", eventnummer);
        nachschubZuckerSchokolade = new Nachschub(this.markplatzSem, zuckerSem, schokoladenSem, "Zucker und Schokolade", eventnummer);
        nachschubMandelSchokolade = new Nachschub(this.markplatzSem, mandelnSem, schokoladenSem, "Mandeln und Schokolade", eventnummer);
    }

    @Override
    public void starteNachschubproduktion() {
        nachschubMandelZucker.start();
        nachschubMandelSchokolade.start();
        nachschubZuckerSchokolade.start();
    }
}
