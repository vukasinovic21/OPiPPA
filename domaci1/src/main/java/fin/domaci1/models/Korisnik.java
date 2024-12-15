package fin.domaci1.models;

import java.io.Serializable;

public class Korisnik implements Serializable
{
    private int id;
    private String imePrezime;
    private String korisnickoIme;
    private String email;
    private String datumRodjenja;
    private int stanje;
    private int potroseno;

    public Korisnik()
    {}

    public Korisnik(int id, String imePrezime, String korisnickoIme, String email, String datumRodjenja, int stanje, int potroseno)
    {
        this.id = id;
        this.imePrezime = imePrezime;
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.datumRodjenja = datumRodjenja;
        this.stanje = stanje;
        this.potroseno = potroseno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return imePrezime;
    }

    public void setIme(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public int getStanje() {
        return stanje;
    }

    public void setStanje(int stanje) {
        this.stanje = stanje;
    }

    public int getPotroseno() {
        return potroseno;
    }

    public void setPotroseno(int potroseno) {
        this.potroseno = potroseno;
    }
}
