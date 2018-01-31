package jdbchfdst04;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Vb1 {
    private final static String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
    private final static String USER = "cursist";
    private final static String PASSWORD = "cursist";
    private final static String SELECT_ALLE_LEVERANCIERS = "select id, naam from leveranciers order by id";
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                /*
                De Statement method executeQuery geeft een ResultSet object.
                Je moet een ResultSet na gebruik sluiten.
                ResultSet erft van AutoCloseable.
                Je maakt de ResultSet daarom ook tussen de ronde haakjes van de try opdracht.
                De compiler maakt dan zelf code die deze ResultSet sluit in een finally blok.
                */
                ResultSet resultSet = statement.executeQuery(SELECT_ALLE_LEVERANCIERS)) {
            // Itereert over de ResultSet rijen
            while(resultSet.next()) {
//                // Leest met de method getInt de int waarde in de eerste kolom (id).
//                System.out.println(resultSet.getInt(1) + " " +
//                                    // Leest met de method getString de String waarde in de tweede kolom (naam).
//                                    resultSet.getString(2));
                /*
                Vermijdt de nadelen van het aanduiden van de kolommen met hun volgnummer,
                door de kolommen aan te duiden met hun naam.
                */
                // Leest de int waarde in kolom 'id'
                System.out.println(resultSet.getInt("id") + " " 
                        // Leest de String waarde van de kolom naam
                        + resultSet.getString("naam"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
