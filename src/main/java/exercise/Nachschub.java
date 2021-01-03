package exercise;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Nachschub extends Thread {

    protected Semaphore marktplatzSem;

    protected AtomicInteger eventnummer;

    /* Zutaten, die nachgelegt werden, wenn der Marktplatz frei ist */
    protected Semaphore zutat1Sem;
    protected Semaphore zutat2Sem;

    protected String zutatenBeschreibung;

    public Nachschub(
            Semaphore marktplatzSem,
            Semaphore zutat1Sem,
            Semaphore zutat2Sem,
            String zutatenBeschreibung,
            AtomicInteger eventnummer
    ) {
        this.marktplatzSem = marktplatzSem;
        this.eventnummer = eventnummer;
        this.zutat1Sem = zutat1Sem;
        this.zutat2Sem = zutat2Sem;
        this.zutatenBeschreibung = zutatenBeschreibung;
    }

    public void gebeNachgelegteZutatenAufKonsoleAus(){
        var aktuelleEventnummer = eventnummer.incrementAndGet();
        System.out.println("Event " + aktuelleEventnummer + ": Auf den Marktplatz gelegt: " + zutatenBeschreibung);
    }

    public void run() {
        while (true){
            try {
                /* Platz auf dem Markt? */
                marktplatzSem.acquire();
                gebeNachgelegteZutatenAufKonsoleAus();
                /* Fülle Zutaten nach */
                zutat1Sem.release();
                zutat2Sem.release();

            } catch (InterruptedException ignored) { }
        }
    }
}
