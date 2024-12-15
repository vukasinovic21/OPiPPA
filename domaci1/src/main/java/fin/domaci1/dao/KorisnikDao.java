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
            ps.setString(4, korisnik.getDatumRodjenja());
            ps.setString(5, sifra);
            ps.setInt(6, korisnik.getStanje());
            ps.setInt(7, 0);
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
                            rs.getInt("korisnik_id"),
                            rs.getString("ime_i_prezime"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("datum_rodjenja"),
                            rs.getInt("stanje_racuna"),
                            rs.getInt("kolicina_potrosenog_novca")
                    );
                }
            }
        }
        if(korisnik == null) throw new Exception("Pogresan username ili sifra.");
        return korisnik;
    }

    public Korisnik nadjiKorisnikaPoId(int id, Connection con) throws SQLException
    {
        Korisnik korisnik = null;
        String query = "SELECT * FROM korisnik WHERE korisnik_id = ?";

        try (PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    korisnik = new Korisnik(
                            rs.getInt("korisnik_id"),
                            rs.getString("ime_i_prezime"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("datum_rodjenja"),
                            rs.getInt("stanje_racuna"),
                            rs.getInt("kolicina_potrosenog_novca")
                    );
                }
            }
        }
        return korisnik;
    }

    public void azurirajStanje(int cena, Korisnik korisnik, Connection con) throws SQLException
    {
        String query = "UPDATE korisnik SET stanje_racuna = ?, kolicina_potrosenog_novca = ? WHERE korisnik_id = ?";

        try (PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setLong(1, korisnik.getStanje() - cena);
            ps.setLong(2, korisnik.getPotroseno() + cena);
            ps.setLong(3, korisnik.getId());
            ps.executeUpdate();
        }
    }
}
