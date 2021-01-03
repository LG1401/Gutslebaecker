package exercise;

import java.util.concurrent.Semaphore;

public abstract class AMarktplatz {

    /* signalisiert die Verfügbarkeit der entsprechenden Zutat auf dem Marktplatz */
    protected final Semaphore mandelnSem;
    protected final Semaphore zuckerSem;
    protected final Semaphore schokoladenSem;

    /* Semaphor, um den Zugriff auf den Marktplatz zu regulieren */
    /* TODO: Initialisieren Sie das Attribut im Konstruktor der abgeleiteten Klasse Marktplatz */
    protected Semaphore markplatzSem;

    /* modelliert den Vorgang
     * der die entsprechenden Zutaten auf den Marktplatz nachfüllt,
     * sobald der Marktplatz frei ist
     */
    /* TODO: Initialisieren Sie die "Nachschubprozesse" im Konstruktor der abgeleiteten Klasse Marktplatz */
    protected Nachschub nachschubMandelZucker;
    protected Nachschub nachschubZuckerSchokolade;
    protected Nachschub nachschubMandelSchokolade;

    public AMarktplatz() {

        mandelnSem = new Semaphore(0);
        zuckerSem = new Semaphore(0);
        schokoladenSem = new Semaphore( 0);

    }

    public Semaphore getMarkplatzSem() {
        return markplatzSem;
    }

    public Semaphore getMandelnSem() {
        return mandelnSem;
    }

    public Semaphore getZuckerSem() {
        return zuckerSem;
    }

    public Semaphore getSchokoladenSem() {
        return schokoladenSem;
    }

    public abstract void starteNachschubproduktion();
}
