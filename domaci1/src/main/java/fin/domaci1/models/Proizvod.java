package fin.domaci1.models;

import java.io.Serializable;

public class Proizvod implements Serializable
{
    private int id;
    private String naziv;
    private int cena;
    private String vrsta;
    private int stanje;

    public Proizvod()
    {}

    public Proizvod(int id, String naziv, int cena, String vrsta, int stanje)
    {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.vrsta = vrsta;
        this.stanje = stanje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public int getStanje() {
        return stanje;
    }

    public void setStanje(int stanje) {
        this.stanje = stanje;
    }
}
