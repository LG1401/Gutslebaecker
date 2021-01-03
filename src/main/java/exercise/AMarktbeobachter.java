package exercise;

import java.util.concurrent.Semaphore;

public abstract class AMarktbeobachter extends Thread {

    protected Semaphore zutatSem;
    protected Semaphore marktsignalBäcker1Sem;
    protected Semaphore marktsignalBäcker2Sem;

    protected Semaphore inventarMutex;
    protected Marktinventar marktinventar;

    public AMarktbeobachter(
            Semaphore zutatSem,
            Semaphore marktsignalBäcker1Sem,
            Semaphore marktsignalBäcker2Sem,
            Semaphore inventarMutex,
            Marktinventar marktinventar
        ) {
        this.zutatSem = zutatSem;
        this.marktsignalBäcker1Sem = marktsignalBäcker1Sem;
        this.marktsignalBäcker2Sem = marktsignalBäcker2Sem;
        this.inventarMutex = inventarMutex;
        this.marktinventar = marktinventar;
    }
}
