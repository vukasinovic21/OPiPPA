package fin.domaci1.models;

import java.io.Serializable;

public class Pretraga implements Serializable
{
    private int id;
    private int donjaGranica;
    private int gornjaGranica;
    private String vrsta;
    private String kljucnaRec;
    private int korisnikId;

    public Pretraga()
    {
    }

    public Pretraga(int id, int donjaGranica, int gornjaGranica, String vrsta, String kljucnaRec, int korisnikId)
    {
        this.id = id;
        this.donjaGranica = donjaGranica;
        this.gornjaGranica = gornjaGranica;
        this.vrsta = vrsta;
        this.kljucnaRec = kljucnaRec;
        this.korisnikId = korisnikId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDonjaGranica() {
        return donjaGranica;
    }

    public void setDonjaGranica(int donjaGranica) {
        this.donjaGranica = donjaGranica;
    }

    public int getGornjaGranica() {
        return gornjaGranica;
    }

    public void setGornjaGranica(int gornjaGranica) {
        this.gornjaGranica = gornjaGranica;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public String getKljucnaRec() {
        return kljucnaRec;
    }

    public void setKljucnaRec(String kljucnaRec) {
        this.kljucnaRec = kljucnaRec;
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
    }
}
