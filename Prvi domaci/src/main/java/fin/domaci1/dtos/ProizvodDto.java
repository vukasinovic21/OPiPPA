package fin.domaci1.dtos;

public class ProizvodDto
{
    Integer id;
    String naziv;
    int cena;

    public ProizvodDto(Integer id, String naziv, int cena)
    {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
    }

    public String getNaziv() {
        return naziv;
    }

    public Integer getCena() {
        return cena;
    }

    public Integer getId() {
        return id;
    }
}
