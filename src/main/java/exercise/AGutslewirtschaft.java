package exercise;

import java.util.concurrent.Semaphore;

public abstract class AGutslewirtschaft {

    /* signalisiert dem Bäcker, dass er Zutaten vom Marktplatz holen kann */
    protected Semaphore marktsignalMandelbäckerSem;
    protected Semaphore marktsignalZuckerbäckerSem;
    protected Semaphore marktsignalSchokoladenbäckerSem;

    /* Semaphor, um Veränderungen am Marktinventar zu synchronisieren */
    protected Semaphore inventarMutex;

    /* auf dem Markt verfügbare Zutaten */
    protected Marktinventar marktinventar;

    /* Bäcker mit eigener Mandelversorgung, d.h. benötigt nur Zucker und Schokolade vom Markt */
    protected ABäckerMitEinerZutat mandelbäcker;

    /* Bäcker mit eigener Zuckerversorgung, d.h. benötigt nur Mandeln und Schokolade vom Markt */
    protected ABäckerMitEinerZutat zuckerbäcker;

    /* Bäcker mit eigener Schokoladenversorgung, d.h. benötigt nur Mandeln und Zucker vom Markt */
    protected ABäckerMitEinerZutat schokoladenbäcker;

    /* beobachten den Markt, und signalisiert ggfs. Bäcker, dass seine Zutaten auf dem Marktplatz sind */
    protected AMarktbeobachter marktbeobachterMandeln;
    protected AMarktbeobachter marktbeobachterZucker;
    protected AMarktbeobachter marktbeobachterSchokolade;

    public abstract void setzeWirtschaftInGang();

}
