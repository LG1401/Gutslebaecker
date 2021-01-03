package solution;

import exercise.ABäckerMitEinerZutat;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class BäckerMitEinerZutat extends ABäckerMitEinerZutat {



    public BäckerMitEinerZutat(Semaphore marktsignalSem, Semaphore marktplatzSem, String bäckereiName, AtomicInteger eventnummer) {
        super(marktsignalSem, marktplatzSem, bäckereiName, eventnummer);
    }

    @Override
    public void run() {
        while(true){
            try {
                marktsignalSem.acquire();
                backeGutsle();
                verkaufeGutsle();
            } catch (InterruptedException e) {}
        }
    }

    @Override
    public void backeGutsle() {
        var aktuelleEventnummer = eventnummer.incrementAndGet();
        try{
            System.out.println("Event " + aktuelleEventnummer + ": " + bäckereiName + " backt.");
            sleep(ThreadLocalRandom.current().nextLong(100,200));
        } catch (InterruptedException ignored) { }
        finally {
            marktplatzSem.release();
        }
    }

    @Override
    public void verkaufeGutsle() {
        var aktuelleEventnummer = eventnummer.incrementAndGet();
        try{
            System.out.println("Event " + aktuelleEventnummer + ": " + bäckereiName + " verkauft Gutsle.");
            sleep(ThreadLocalRandom.current().nextLong(100,200));
        } catch (InterruptedException ignored) { }
    }
}
