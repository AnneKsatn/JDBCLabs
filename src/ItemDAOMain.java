import com.javatunes.util.ItemDAO;
import com.javatunes.util.JDBCUtilities;
import com.javatunes.util.MusicItem;

import java.sql.*;

public class ItemDAOMain {
    public static void main(String[] args){
        MusicItem item;
        Connection conn = null;

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/JavaTunesDB",
                    "guest", "lkl");

            DatabaseMetaData dbmd = conn.getMetaData();

            ItemDAO itDao = new ItemDAO(conn);
            System.out.println(itDao.searchById((long)1));
            System.out.println(itDao.searchById((long)100));

            System.out.println(itDao.searchByKeyword("of").size());
            System.out.println(itDao.searchByKeyword("Gay").size());

            item = itDao.searchById((long)1);
            itDao.create(item);
        }



        catch(ClassNotFoundException e){
            System.out.println(e);
        }

        catch(SQLException e){
            JDBCUtilities.printSQLException(e);
        }


    }
}