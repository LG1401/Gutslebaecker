package exercise;

public class Marktinventar {

    private Boolean hatMandeln;
    private Boolean hatZucker;
    private Boolean hatSchokolade;

    public Marktinventar() {
        this.hatMandeln = false;
        this.hatZucker = false;
        this.hatSchokolade = false;
    }

    public Boolean sindMandelnVerfügbar() {
        return hatMandeln;
    }

    public void setzeMandelverfügbarkeit(Boolean hatMandeln) {
        this.hatMandeln = hatMandeln;
    }

    public Boolean istZuckerVefügbar() {
        return hatZucker;
    }

    public void setzeZuckerverfügbarkeit(Boolean hatZucker) {
        this.hatZucker = hatZucker;
    }

    public Boolean istSchokoladeVerfügbar() {
        return hatSchokolade;
    }

    public void setzeSchokoladenverfügbarkeit(Boolean hatSchokolade) {
        this.hatSchokolade = hatSchokolade;
    }
}
