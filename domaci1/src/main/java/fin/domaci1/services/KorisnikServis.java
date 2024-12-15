package fin.domaci1.services;

import fin.domaci1.dao.KorisnikDao;
import fin.domaci1.dao.ResourcesManager;
import fin.domaci1.dtos.LoginDto;
import fin.domaci1.dtos.RegisterDto;
import fin.domaci1.models.Korisnik;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class KorisnikServis
{
    private static final KorisnikServis instance = new KorisnikServis();
    private final KorisnikDao userDao = KorisnikDao.getInstance();

    private KorisnikServis()
    {
    }

    public static KorisnikServis getInstance() {
        return instance;
    }

    public void registerUser(RegisterDto registerDto) throws Exception, NoSuchAlgorithmException
    {
        try (Connection con = ResourcesManager.getConnection())
        {
            KorisnikDao.dodajKorisnika(registerDto, con);
        }
        catch (SQLException ex)
        {
            throw new Exception("Greska pri registrovanju korisnika.", ex);
        }
    }

    public Korisnik login(LoginDto loginDto) throws Exception, NoSuchAlgorithmException
    {
        try (Connection con = ResourcesManager.getConnection())
        {
            return userDao.login(loginDto, con);
        }
        catch (SQLException ex)
        {
            throw new Exception("Error during login.", ex);
        }
    }
}
