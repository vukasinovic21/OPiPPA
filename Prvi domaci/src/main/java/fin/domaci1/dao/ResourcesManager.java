package fin.domaci1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourcesManager
{

    static
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/racunarska_oprema?user=root&password=");
        return con;
    }
    

    public static void closeResources(ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException
    {
        if (resultSet != null)
        {
            resultSet.close();
        }
        if (preparedStatement != null)
        {
            preparedStatement.close();
        }
    }

    public static void closeConnection(Connection con) throws Exception
    {
        if (con != null)
        {
            try
            {
                con.close();
            }
            catch (SQLException ex)
            {
                throw new Exception("Failed to close database connection.", ex);
            }
        }
    }

    public static void rollbackTransactions(Connection con) throws Exception
    {
        if (con != null)
        {
            try
            {
                con.rollback();
            }
            catch (SQLException ex)
            {
                throw new Exception("Failed to rollback database transactions.", ex);
            }
        }
    }
}
