import com.javatunes.util.JDBCUtilities;

import java.sql.*;

public class JBBCTest {

    public static void main(String[] args){

        Connection conn = null;

        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/JavaTunesDB",
                    "guest", "lkl");

            DatabaseMetaData dbmd = conn.getMetaData();
            System.out.println(dbmd.getDriverName());
            System.out.println(dbmd.getUserName());
        }
        catch(ClassNotFoundException e){
            System.out.println(e);
        }

        catch (SQLException sqle) {
            JDBCUtilities.printSQLException(sqle);
        }

        finally {
            try {
                conn.close();
            }
            catch (SQLException ignored){}
        }
    }
}
