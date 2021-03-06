package solution;

import exercise.AMarktbeobachter;
import exercise.Marktinventar;

import java.util.concurrent.Semaphore;

public class MarktbeobachterZucker extends AMarktbeobachter {
    public MarktbeobachterZucker(Semaphore zutatSem, Semaphore marktsignalBäcker1Sem, Semaphore marktsignalBäcker2Sem, Semaphore inventarMutex, Marktinventar marktinventar) {
        super(zutatSem, marktsignalBäcker1Sem, marktsignalBäcker2Sem, inventarMutex, marktinventar);
    }

    @Override
    public void run() {
        while (true) {
            try {
                zutatSem.acquire();
                inventarMutex.acquire();
                marktinventar.setzeZuckerverfügbarkeit(true);
                if (marktinventar.istSchokoladeVerfügbar()) {
                    marktsignalBäcker1Sem.release();
                    marktinventar.setzeSchokoladenverfügbarkeit(false);
                    marktinventar.setzeZuckerverfügbarkeit(false);
                } else if (marktinventar.sindMandelnVerfügbar()) {
                    marktsignalBäcker2Sem.release();
                    marktinventar.setzeZuckerverfügbarkeit(false);
                    marktinventar.setzeMandelverfügbarkeit(false);
                }
                inventarMutex.release();
            } catch (InterruptedException e) {
            }

        }
    }
}
