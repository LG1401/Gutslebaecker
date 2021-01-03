package exercise;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class ABäckerMitEinerZutat extends Thread {

    protected AtomicInteger eventnummer;

    /* über diesen Semaphor wird dem Bäcker von einem Marktbeobachter signalisiert,
     * dass die ihm fehlenden Zutaten auf dem Marktplatz verfügbar sind.
     */
    protected Semaphore marktsignalSem;

    /* über diesen Semaphor signalisiert der Bäcker, dass er die ihm fehlenden Zutaten vom Markt "genommen" hat
     */
    protected Semaphore marktplatzSem;

    /* Namen der Bäckerei (identifiziert Bäcker über Zutaten, z.B. Bäcker mit Zuckerrübenfeld " */
    protected String bäckereiName;

    public ABäckerMitEinerZutat(
            Semaphore marktsignalSem,
            Semaphore marktplatzSem,
            String bäckereiName,
            AtomicInteger eventnummer
        ) {
        this.marktsignalSem = marktsignalSem;
        this.marktplatzSem = marktplatzSem;
        this.bäckereiName = bäckereiName;
        this.eventnummer = eventnummer;
    }

    /* nachdem der Bäcker von seinem Marktbeobachter informiert wurde, dass die ihm fehlenden Zutaten verfügbar sind,
     * backt er.
     * Nach dem Backen signalisiert er, dass der Marktplatz frei ist.
     */
    public void backeGutsle() {
        var aktuelleEventnummer = eventnummer.incrementAndGet();
        try{
            System.out.println("Event " + aktuelleEventnummer + ": " + bäckereiName + " backt.");
            sleep(ThreadLocalRandom.current().nextLong(100,200));
        } catch (InterruptedException ignored) { }
    }

    /* Nachdem der Bäcker den freien Marktplatz signalisiert hat, ist er mit dem Verkaufen beschäftigt,
     * Nach dem Verkaufen wartet er darauf, dass sein Marktplatzbeobachter ihm signalisiert, dass seine (fehlenden)
     * Zutaten verfügbar sind.
     */
    public void verkaufeGutsle() {
        var aktuelleEventnummer = eventnummer.incrementAndGet();
        try{
            System.out.println("Event " + aktuelleEventnummer + ": " + bäckereiName + " verkauft Gutsle.");
            sleep(ThreadLocalRandom.current().nextLong(100,200));
        } catch (InterruptedException ignored) { }
    }
}
