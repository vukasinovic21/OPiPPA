package fin.domaci1.dao;

import fin.domaci1.dtos.LoginDto;
import fin.domaci1.dtos.RegisterDto;
import fin.domaci1.models.Korisnik;
import fin.domaci1.util.HashUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public Korisnik login(LoginDto loginDto, Connection con) throws SQLException, Exception, NoSuchAlgorithmException
    {
        String query = "select * from korisnik where username = ? and sifra = ?";
        Korisnik korisnik = null;
        var sifra = HashUtil.getSHA256(loginDto.getPassword());
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, loginDto.getUsername());
            ps.setString(2, sifra);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    korisnik = new Korisnik(
                            rs.getInt("id"),
                            rs.getString("imePrezime"),
                            rs.getString("korisnickoIme"),
                            rs.getString("email"),
                            rs.getString("datumRodjenja"),
                            rs.getInt("stanje"),
                            rs.getInt("potroseno")
                    );
                }
            }
        }
        if(korisnik == null) throw new Exception("Pogresan username ili sifra.");
        return korisnik;
    }
}
