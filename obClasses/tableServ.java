package obClasses;

public class tableServ {
    private int idDost;
    private String pibOtrym;
    private String adresOtrym;
    private String typVidpravk;
    private String dateVidpr;
    private String stan;
    private String dateOtrym;
    private Double vartist;

    public int getIdDost() {
        return idDost;
    }

    public void setIdDost(int idDost) {
        this.idDost = idDost;
    }

    public tableServ(int idDost, String pibOtrym, String adresOtrym, String typVidpravk, String dateVidpr, String stan, String dateOtrym, Double vartist) {
        this.idDost = idDost;
        this.pibOtrym = pibOtrym;
        this.adresOtrym = adresOtrym;
        this.typVidpravk = typVidpravk;
        this.dateVidpr = dateVidpr;
        this.stan = stan;
        this.dateOtrym = dateOtrym;
        this.vartist = vartist;
    }

    public String getPibOtrym() {
        return pibOtrym;
    }

    public void setPibOtrym(String pibOtrym) {
        this.pibOtrym = pibOtrym;
    }

    public String getAdresOtrym() {
        return adresOtrym;
    }

    public void setAdresOtrym(String adresOtrym) {
        this.adresOtrym = adresOtrym;
    }

    public String getTypVidpravk() {
        return typVidpravk;
    }

    public void setTypVidpravk(String typVidpravk) {
        this.typVidpravk = typVidpravk;
    }

    public String getDateVidpr() {
        return dateVidpr;
    }

    public void setDateVidpr(String dateVidpr) {
        this.dateVidpr = dateVidpr;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public String getDateOtrym() {
        return dateOtrym;
    }

    public void setDateOtrym(String dateOtrym) {
        this.dateOtrym = dateOtrym;
    }

    public Double getVartist() {
        return vartist;
    }

    public void setVartist(Double vartist) {
        this.vartist = vartist;
    }
}
