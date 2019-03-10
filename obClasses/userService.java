package obClasses;

public class userService {
    private String logName;
    private String familya;
    private String imya;
    private String teleph;
    private String mail;
    private String compan;
    private String pass;

    public userService(String logName, String familya, String imya, String teleph, String mail, String compan, String pass) {
        this.logName = logName;
        this.familya = familya;
        this.imya = imya;
        this.teleph = teleph;
        this.mail = mail;
        this.compan = compan;
        this.pass = pass;
    }

    public userService(String logName, String familya, String imya, String teleph, String mail, String pass) {
        this.logName = logName;
        this.familya = familya;
        this.imya = imya;
        this.teleph = teleph;
        this.mail = mail;
        this.pass = pass;
    }

    public String getLogName() {
        return logName;
    }

    public String getFamilya() {
        return familya;
    }

    public String getImya() {
        return imya;
    }

    public String getTeleph() {
        return teleph;
    }

    public String getMail() {
        return mail;
    }

    public String getCompan() {
        return compan;
    }

    public String getPass() {
        return pass;
    }
}
