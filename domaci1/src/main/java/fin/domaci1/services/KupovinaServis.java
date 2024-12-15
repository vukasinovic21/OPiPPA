package fin.domaci1.services;

import fin.domaci1.dao.KorisnikDao;
import fin.domaci1.dao.KupovinaDao;
import fin.domaci1.dao.ProizvodDao;
import fin.domaci1.dao.ResourcesManager;

import java.sql.Connection;
import java.sql.SQLException;

public class KupovinaServis
{
    private static final KupovinaServis instance = new KupovinaServis();
    private final KupovinaDao kupovinaDao = KupovinaDao.getInstance();
    private final KorisnikDao korisnikDao = KorisnikDao.getInstance();
    private final ProizvodDao proizvodDao = ProizvodDao.getInstance();


    private KupovinaServis()
    {
    }

    public static KupovinaServis getInstance() {
        return instance;
    }


    public void kupi(Integer proizvodId, Integer korisnikId) throws Exception
    {
        try (Connection con = ResourcesManager.getConnection())
        {
            var proizvod = proizvodDao.nadjiProizvodPoId(proizvodId, con);
            var korisnik = korisnikDao.nadjiKorisnikaPoId(korisnikId, con);
            if(proizvod.getStanje() == 0) throw new Exception("Proizvod nije na stanju.");
            if(korisnik.getStanje() < proizvod.getCena()) throw new Exception("Nedovoljno novca.");
            proizvodDao.azurirajStanje(proizvod, con);
            korisnikDao.azurirajStanje(proizvod.getCena(), korisnik, con);
            kupovinaDao.dodajKupovinu(proizvodId, korisnikId, con);
        }
        catch (SQLException ex)
        {
            throw new Exception("Greska prilikom kupovine.", ex);
        }
    }
}
