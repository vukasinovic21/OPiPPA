package fin.domaci1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KupovinaDao
{
    private static final KupovinaDao instance = new KupovinaDao();

    private KupovinaDao()
    {
    }

    public static KupovinaDao getInstance() {
        return instance;
    }

    public void dodajKupovinu(Integer proizvodId, Integer korisnikId, Connection con) throws SQLException
    {
        String query = "INSERT INTO kupovina (proizvod_id, korisnik_id) VALUES (?, ?)";

        try (PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setLong(1, proizvodId);
            ps.setLong(2, korisnikId);
            ps.executeUpdate();
        }
    }
}
