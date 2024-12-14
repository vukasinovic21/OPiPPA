package fin.domaci1.dao;

import fin.domaci1.models.Pretraga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PretragaDao
{
    private static final PretragaDao instance = new PretragaDao();

    private PretragaDao()
    {
    }

    public static PretragaDao getInstance() {
        return instance;
    }

    public void ubaciPretragu(Pretraga pretraga, Connection con) throws SQLException
    {
        String query = "INSERT INTO podesavanje_pretrage (donja_granica_obima_cene, gornja_granica_obima_cene, vrsta_opreme, kljucna_rec, korisnikId) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setLong(1, pretraga.getDonjaGranica());
            ps.setLong(2, pretraga.getGornjaGranica());
            ps.setString(3, pretraga.getVrsta());
            ps.setString(4, pretraga.getKljucnaRec());
            ps.setLong(5, pretraga.getKorisnikId());
            ps.executeUpdate();
        }
    }

}
