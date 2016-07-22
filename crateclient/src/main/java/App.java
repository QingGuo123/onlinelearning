
import java.sql.Connection;
import java.sql.ResultSet;
import java.lang.*;
import java.sql.*;
import io.crate.client.CrateClient;


public class App {

    public static void main(String[] args) {


        String sql = "SELECT * from binlog.gulfstream where within("
                + "starting_posi,{ TYPE = 'Polygon', coordinates = "
                + "[[[112.59217326829788,26.91737252586985],[118.53524868246734,26.91737252586985],"
                + "[118.53524868246734,30.903524128071187], [112.59217326829788,31.903524128071187], "
                + "[110.59217326829788,27.91737252586985],[112.59217326829788,26.91737252586985]]"
                + "] }) limit 100";

        System.out.println(sql);
        ResultSet rs = null;
        Statement statement = null;
        Connection conn = null;

        try{

            Class.forName("io.crate.client.jdbc.CrateDriver");
            conn = DriverManager.getConnection("jdbc:crate://localhost:4300");
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            String txt=null,response=null;
            while(rs.next()){
                txt = rs.getString("arrive_time");
                response = response + txt;
                System.out.println(response);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}
