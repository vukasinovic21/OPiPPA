package fin.domaci1.dao;

import fin.domaci1.dtos.ProizvodDto;
import fin.domaci1.models.Proizvod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProizvodDao
{

    private static final ProizvodDao instance = new ProizvodDao();

    private ProizvodDao()
    {
    }

    public static ProizvodDao getInstance() {
        return instance;
    }


    public List<ProizvodDto> pretraziProizvode(Integer donjaGranica, Integer gornjaGranica, String vrsta, String kljucnaRec, Integer korisnikId, Connection con) throws SQLException
    {
        List<ProizvodDto> proizvodi = new ArrayList<>();
        String query = "SELECT naziv, cena FROM proizvod WHERE 1=1";

        if (donjaGranica != null) query += " AND cena >= ?";
        if (gornjaGranica != null) query += " AND cena <= ?";
        if (vrsta != null) query += " AND vrsta_opreme = ?";
        if (kljucnaRec != null) query += " AND naziv LIKE ?";

        try (PreparedStatement ps = con.prepareStatement(query))
        {
            int index = 1;
            if (donjaGranica != null) ps.setInt(index++, donjaGranica);
            if (gornjaGranica != null) ps.setInt(index++, gornjaGranica);
            if (vrsta != null) ps.setString(index++, vrsta);
            if (kljucnaRec != null) ps.setString(index++, "%" + kljucnaRec + "%");

            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    ProizvodDto proizvod = new ProizvodDto(
                            rs.getInt("proizvod_id"),
                            rs.getString("naziv"),
                            rs.getInt("cena")
                    );
                    proizvodi.add(proizvod);
                }
            }
        }
        return proizvodi;
    }

    public List<ProizvodDto> sviProizvodi(Connection con) throws SQLException
    {
        List<ProizvodDto> proizvodi = new ArrayList<>();
        String query = "SELECT proizvod_id, naziv, cena FROM proizvod WHERE 1=1";

        try (PreparedStatement ps = con.prepareStatement(query))
        {
            int index = 1;
            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    ProizvodDto proizvod = new ProizvodDto(
                            rs.getInt("proizvod_id"),
                            rs.getString("naziv"),
                            rs.getInt("cena")
                    );
                    proizvodi.add(proizvod);
                }
            }
        }
        return proizvodi;
    }
    

    public Proizvod nadjiProizvodPoId(int id, Connection con) throws SQLException
    {
        Proizvod proizvod = null;
        String query = "SELECT * FROM proizvod WHERE proizvod_id = ?";

        try (PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    proizvod = new Proizvod(
                            rs.getInt("proizvod_id"),
                            rs.getString("naziv"),
                            rs.getInt("cena"),
                            rs.getString("vrsta_opreme"),
                            rs.getInt("stanje_na_lageru")
                    );
                }
            }
        }
        return proizvod;
    }

    public void azurirajStanje(Proizvod proizvod, Connection con) throws SQLException
    {
        String query = "UPDATE proizvod SET stanje_na_lageru = ? WHERE proizvod_id = ?";

        try (PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setLong(1, proizvod.getStanje() - 1);
            ps.setLong(2, proizvod.getId());
            ps.executeUpdate();
        }
    }

}
