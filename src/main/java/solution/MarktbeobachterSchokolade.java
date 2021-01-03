package solution;

import exercise.AMarktbeobachter;
import exercise.Marktinventar;

import java.util.concurrent.Semaphore;

public class MarktbeobachterSchokolade extends AMarktbeobachter {
    public MarktbeobachterSchokolade(Semaphore zutatSem, Semaphore marktsignalBäcker1Sem, Semaphore marktsignalBäcker2Sem, Semaphore inventarMutex, Marktinventar marktinventar) {
        super(zutatSem, marktsignalBäcker1Sem, marktsignalBäcker2Sem, inventarMutex, marktinventar);
    }

    @Override
    public void run() {
        while (true) {
            try {
                zutatSem.acquire();
                inventarMutex.acquire();
                marktinventar.setzeSchokoladenverfügbarkeit(true);
                if (marktinventar.sindMandelnVerfügbar()) {
                    marktsignalBäcker1Sem.release();
                    marktinventar.setzeSchokoladenverfügbarkeit(false);
                    marktinventar.setzeMandelverfügbarkeit(false);
                } else if (marktinventar.istZuckerVefügbar()) {
                    marktsignalBäcker2Sem.release();
                    marktinventar.setzeZuckerverfügbarkeit(false);
                    marktinventar.setzeSchokoladenverfügbarkeit(false);
                }
                inventarMutex.release();
            } catch (InterruptedException e) {
            }

        }
    }
}
