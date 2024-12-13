package fin.domaci1.models;

import java.io.Serializable;

public class Kupovina implements Serializable
{
    private int id;
    private int korisnikId;
    private int proizvodId;

    public Kupovina()
    {
    }

    public Kupovina(int id, int korisnikId, int proizvodId)
    {
        this.id = id;
        this.korisnikId = korisnikId;
        this.proizvodId = proizvodId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
    }

    public int getProizvodId() {
        return proizvodId;
    }

    public void setProizvodId(int proizvodId) {
        this.proizvodId = proizvodId;
    }
}
