/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2004-8 LearningPatterns Inc.
 */

package com.javatunes.util;

import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;
import java.math.BigDecimal;

public class ItemDAO
{
   // connection to the database (assumed to be open)
   private Connection m_conn = null;
   
   //// PreparedStatement Lab ////
   //-- declare the PreparedStatement for searchByKeyword --//
   
   
   //// Update Lab ////
   //-- declare the PreparedStatement for create --//
   
   
   
   // constructor
   public ItemDAO(Connection conn)
   throws SQLException
   {
      // store the connection
      m_conn = conn;
      
      //// PreparedStatement Lab ////
      //-- define the ?-SQL for searchByKeyword --//
      
      
      //-- prepare the ?-SQL with the DBMS and initialize the PreparedStatement --//
      
      
      //// Update Lab ////
      //-- define the ?-SQL for create --//
      
      
      //-- prepare the ?-SQL with the DBMS and initialize the PreparedStatement --//
      
   }
   
   
   //// DAO Lab ////
   public MusicItem searchById(Long id)
   throws SQLException
   {
      // declare return value
      MusicItem result = null;
      
      // declare JDBC objects
      PreparedStatement pstm = null;
      
      //-- build the SQL statement --//
      String sql = "select * from GUEST.ITEM where ITEM_ID = ?";
      
      try
      {
         //-- initialize the Statement object --//
         
         //-- execute the SQL statement, get a ResultSet back --//
         pstm = m_conn.prepareStatement(sql);
         pstm.setLong(1, id);


         ResultSet rs = (ResultSet)pstm.executeQuery();

         while(rs.next ()) {
            Long item_id = rs.getLong("ITEM_ID");
            
            //-- if ID found, extract data from the ResultSet and initialize the ItemValue return value --//
            if (!rs.wasNull()) {
               Long id_n = rs.getLong("ITEM_ID");
               String title = rs.getString("TITLE");
               String artist = rs.getString("artist");
               java.util.Date releaseDate = rs.getDate("releasedate");
               BigDecimal listPrice = rs.getBigDecimal("listPrice");
               BigDecimal price = rs.getBigDecimal("price");

               result = new MusicItem(id_n, title, artist, releaseDate, listPrice, price);
            }

            //-- if ID not found, the return value remains null --//
            else {
               result = null;
            }
         }
         
      }
      finally
      {
         //-- close the Statement - this closes its ResultSet --//
         pstm.close();
      }
      
      // return the value object
      return result;
   }
   
   
   //// PreparedStaement Lab ////
   public Collection<MusicItem> searchByKeyword(String keyword)
   throws SQLException
   {
      // create storage for the results
      Collection<MusicItem> result = new ArrayList<MusicItem>();
      
      // create the %keyword% wildcard syntax used in SQL LIKE operator
      String wildcarded = "%" + keyword + "%";
      String sql = "SELECT * from item where title like ?";
      PreparedStatement pstm = null;

      try {
         pstm = m_conn.prepareStatement(sql);
         pstm.setString(1, wildcarded);

         //-- execute the PreparedStatement, get a ResultSet back --//
         ResultSet rs = pstm.executeQuery();

         while(rs.next ()) {
            Long item_id = rs.getLong("ITEM_ID");

            //-- if ID found, extract data from the ResultSet and initialize the ItemValue return value --//
            if (!rs.wasNull()) {
               String title = rs.getString("TITLE");
               String artist = rs.getString("artist");
               java.util.Date releaseDate = rs.getDate("releasedate");
               BigDecimal listPrice = rs.getBigDecimal("listPrice");
               BigDecimal price = rs.getBigDecimal("price");
               result.add(new MusicItem(item_id, title, artist, releaseDate, listPrice, price));

            }

            //-- if ID not found, the return value remains null --//
            else {
               result = null;
            }
         }

      }
      finally
      {
         //-- close the Statement - this closes its ResultSet --//
         pstm.close();
      }
      
      // return the Collection
      return result;
   }
   
   
   //// Update Lab ////
   public void create(MusicItem item)
   throws SQLException
   {
      String sql = "INSERT INTO ITEM(title, artist, releasedate, listPrice, price, version)" +
              "VALUES(?,?,?,?,?, 1)";
      java.sql.Date releaseDate = new java.sql.Date(item.getReleaseDate().getTime());

      PreparedStatement stmt = null;
      try {

         stmt = m_conn.prepareStatement(sql);
         stmt.setString(1, item.getTitle());
         stmt.setString(2,item.getArtist());
         stmt.setDate(3, releaseDate);
         stmt.setBigDecimal(4, item.getListPrice());
         stmt.setBigDecimal(5,item.getPrice());

         System.out.println(stmt.executeUpdate());
         m_conn.commit();
      }
      finally
      {
         //-- close the Statement - this closes its ResultSet --//
         stmt.close();
      }
   }
   
   
   //// PreparedStatement and Update Labs ////
   public void close()
   {
      /*
      REMOVE this comment in PreparedStatement Lab
      try
      {
         //// PreparedStatement Lab ////
         //-- close the PreparedStatement for searchByKeyword --//
         
         
         //// Update Lab ////
         //-- close the PreparedStatement for create --//
         
      }
      catch (SQLException sqle)
      {
         JDBCUtilities.printSQLException(sqle);
      }
      REMOVE this comment in the PreparedStatement Lab
      */
   }
}
