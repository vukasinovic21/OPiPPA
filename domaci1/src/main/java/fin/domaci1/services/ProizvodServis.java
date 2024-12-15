package fin.domaci1.services;

import fin.domaci1.dao.PretragaDao;
import fin.domaci1.dao.ProizvodDao;
import fin.domaci1.dao.ResourcesManager;
import fin.domaci1.dtos.ProizvodDto;
import fin.domaci1.models.Pretraga;
import fin.domaci1.models.Proizvod;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProizvodServis
{
    private static final ProizvodServis instance = new ProizvodServis();
    private final ProizvodDao proizvodDao = ProizvodDao.getInstance();
    private final PretragaDao pretragaDao = PretragaDao.getInstance();

    private ProizvodServis()
    {
    }

    public static ProizvodServis getInstance() {
        return instance;
    }

    public List<ProizvodDto> sviProizvodi() throws Exception
    {
        try (Connection con = ResourcesManager.getConnection())
        {
            return proizvodDao.sviProizvodi(con);
        }
        catch (SQLException ex)
        {
            throw new Exception("Greska u pretrazi svih proizvoda.", ex);
        }
    }
    
    public List<String> sveVrste() throws Exception
    {
        try (Connection con = ResourcesManager.getConnection())
        {
            return proizvodDao.sveVrste(con);
        }
        catch (SQLException ex)
        {
            throw new Exception("Greska u pretrazi svih vrsti proizvoda.", ex);
        }
    }
            
    public List<ProizvodDto> proizvodiPoVrsti(String vrsta) throws Exception
    {
        try (Connection con = ResourcesManager.getConnection())
        {
            return proizvodDao.proizvodiPoVrsti(vrsta, con);
        }
        catch (SQLException ex)
        {
            throw new Exception("Greska u pretrazi proizvoda iz po vrsti.", ex);
        }
    }        
            
    public List<ProizvodDto> pretragaProizvoda(Integer donjaGranica, Integer gornjaGranica, String vrsta, String kljucnaRec, Integer korisnikId) throws Exception
    {
        try (Connection con = ResourcesManager.getConnection())
        {
            var pretraga = napraviObjekatPretrage(donjaGranica, gornjaGranica, vrsta, kljucnaRec, korisnikId);
            pretragaDao.ubaciPretragu(pretraga, con);
            return proizvodDao.pretraziProizvode(donjaGranica, gornjaGranica, vrsta, kljucnaRec, korisnikId, con);
        }
        catch (SQLException ex)
        {
            throw new Exception("Greska u pretrazi proizvoda.", ex);
        }
    }

    public Proizvod nadjiProizvodPoId(int id) throws Exception
    {
        try (Connection con = ResourcesManager.getConnection())
        {
            return proizvodDao.nadjiProizvodPoId(id, con);
        }
        catch (SQLException ex)
        {
            throw new Exception("Greska pri pretrazi proizvoda po Idu.", ex);
        }
    }

    public Pretraga napraviObjekatPretrage(Integer donjaGranica, Integer gornjaGranica, String vrsta, String kljucnaRec, Integer korisnikId) throws Exception
    {
        var donjaGranica1 = donjaGranica != null ? donjaGranica : -1;
        var gornjaGranica1 = gornjaGranica != null ? gornjaGranica : -1;
        var vrsta1 = vrsta != null ? vrsta : "";
        var kljucnaRec1 = kljucnaRec != null ? kljucnaRec : "";
        if(korisnikId == null) throw new Exception("KorisnikId nije definisan.");

        return new Pretraga(-1, donjaGranica1, gornjaGranica1, vrsta1, kljucnaRec1, korisnikId);
    }
}
