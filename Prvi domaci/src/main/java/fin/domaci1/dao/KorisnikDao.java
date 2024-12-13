package fin.domaci1.dao;

import fin.domaci1.dtos.RegisterDto;
import fin.domaci1.util.HashUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KorisnikDao
{

    private static final KorisnikDao instance = new KorisnikDao();

    private KorisnikDao()
    {
    }

    public static KorisnikDao getInstance() {
        return instance;
    }

    public static void dodajKorisnika(RegisterDto korisnik, Connection con) throws SQLException, NoSuchAlgorithmException
    {
        var sifra = HashUtil.getSHA256(korisnik.getSifra());

        String query = "INSERT INTO korisnik (ime_i_prezime, username, email, datum_rodjenja, sifra, stanje_racuna, kolicina_potrosenog_novca) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(query))
        {
            String ime_prezime = korisnik.getIme() + " " + korisnik.getPrezime();
            ps.setString(1, ime_prezime);
            ps.setString(2, korisnik.getKorisnickoIme());
            ps.setString(3, korisnik.getEmail());
            ps.setString(5, korisnik.getDatumRodjenja());
            ps.setString(6, sifra);
            ps.setInt(7, korisnik.getStanje());
            ps.setInt(8, 0);
            ps.executeUpdate();
        }
    }
}
