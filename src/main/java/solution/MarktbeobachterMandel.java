package solution;

        import exercise.AMarktbeobachter;
        import exercise.Marktinventar;

        import java.util.concurrent.Semaphore;

public class MarktbeobachterMandel extends AMarktbeobachter {
    public MarktbeobachterMandel(Semaphore zutatSem, Semaphore marktsignalBäcker1Sem, Semaphore marktsignalBäcker2Sem, Semaphore inventarMutex, Marktinventar marktinventar) {
        super(zutatSem, marktsignalBäcker1Sem, marktsignalBäcker2Sem, inventarMutex, marktinventar);
    }

    @Override
    public void run() {
        while (true) {
            try {
                zutatSem.acquire();
                inventarMutex.acquire();
                marktinventar.setzeMandelverfügbarkeit(true);
                if (marktinventar.istSchokoladeVerfügbar()) {
                    marktsignalBäcker1Sem.release();
                    marktinventar.setzeSchokoladenverfügbarkeit(false);
                    marktinventar.setzeMandelverfügbarkeit(false);
                } else if (marktinventar.istZuckerVefügbar()) {
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
