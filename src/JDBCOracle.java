import com.javatunes.util.JDBCUtilities;

import java.sql.*;

public class JDBCOracle {

    public static void main(String[] args){
        Connection conn = null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin@//ИМЯ_СЕРВЕРА:ПОРТ/ИМЯ_СЕРВИСА");
            DatabaseMetaData dbmd = conn.getMetaData();
            System.out.println(dbmd.getUserName());
            System.out.println(dbmd.getDriverName());
        }
        catch (ClassNotFoundException e){
            System.out.println(e);
        }
        catch(SQLException sq){
            JDBCUtilities.printSQLException(sq);
        }

        finally {
            try {conn.close();}
            catch(SQLException sq){};
        }

    }
}
