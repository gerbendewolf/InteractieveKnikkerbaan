package sample;


import java.sql.*;

public class DataBase {

    public DataBase(){
        String url = "jdbc:mysql://localhost/VoorbeeldDatabase";
        String username="henk", password="henkie";

        try {
            Connection connection =
                    DriverManager.getConnection( url,username,password );
            Statement statement = connection.createStatement();
            ResultSet rs =
                    statement.executeQuery(
                            "SELECT AUTHORNAME FROM AUTHORS");
            PreparedStatement s = connection.prepareStatement( "SELECT * FROM users WHERE user=? AND passwd=?" );
            s.setString(1, "Henk");  // eerste vraagteken
            s.setString(2, "Plop");  // tweede vraagteken
            ResultSet rs2 = s.executeQuery();


            int gelukt =
                    statement.executeUpdate(
                            "INSERT INTO Persoon VALUES(1, 'Henk')");
            while( rs.next() ) {
                int id = rs.getInt(1); 	         // 1e kolom
                String naam = rs.getString("Naam");  // kolom ‘Naam’
                String ww = rs.getString(3); 	   // 3e kolom

                System.out.println(id+" "+naam+" "+ww);
            }
            statement.close(); //sluit ook de resultset
            connection.close();






        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
