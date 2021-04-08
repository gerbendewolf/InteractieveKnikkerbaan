package sample;


import java.sql.*;

public class DataBase {

    public DataBase(){
        String url = "jdbc:mysql://localhost/cursus";
        String username="root", password="";

        try {
            Connection connection =
                    DriverManager.getConnection( url,username,password );

            PreparedStatement s = connection.prepareStatement( "SELECT * FROM medewerker WHERE afd=?" );
            s.setInt(1, 10);  // eerste vraagteken
            ResultSet rs2 = s.executeQuery();


            //int gelukt =
             //       statement.executeUpdate(
             //               "INSERT INTO Persoon VALUES(1, 'Henk')");
            while( rs2.next() ) {
                int id = rs2.getInt(1); 	         // 1e kolom
                String naam = rs2.getString("Naam");  // kolom ‘Naam’
                //String ww = rs2.getString(3); 	   // 3e kolom

                System.out.println(id+" "+naam+" ");
            }
            s.close(); //sluit ook de resultset
            connection.close();






        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
