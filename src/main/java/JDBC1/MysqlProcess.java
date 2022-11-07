package JDBC1;

import org.testng.annotations.Test;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MysqlProcess extends MySqlProcessBase{

    @Test
    public void sql1() throws SQLException {

        resultSet=statement.executeQuery("SELECT * FROM actor LIMIT 5;");

        while (resultSet.next()){
            int actor_id= resultSet.getInt(1);
            String first_name=resultSet.getString("first_name");
            String last_name=resultSet.getString(3);
            System.out.printf("%-5d%-20s%-20s\n", actor_id, first_name, last_name);
        }
    }


    @Test
    public void sql2() throws SQLException {
        String sql="SELECT first_name,last_name,title" +
                "                        FROM actor" +
                "                        INNER JOIN film_actor ON actor.actor_id=film_actor.actor_id" +
                "                        INNER JOIN film ON film_actor.film_id=film.film_id" +
                "                        WHERE first_name LIKE 'A%';";

        resultSet=statement.executeQuery(sql);
        while (resultSet.next()){

            String first_name=resultSet.getString(1);
            String last_name=resultSet.getString(2);
            String title=resultSet.getString("title");

            System.out.printf("%-10s%-10s%-30s\n",  first_name,last_name,title);
        }
    }

    // actörlerin isimleri ve rol aldiklari film sayilari
    @Test
    public void sql3() throws SQLException {
        String sql = "SELECT first_name, last_name, COUNT(*) AS sayi" +
                " FROM actor" +
                " INNER JOIN film_actor ON actor.actor_id = film_actor.actor_id" +
                " WHERE first_name LIKE 'A%'" +
                " GROUP BY first_name, last_name" +
                " ORDER BY sayi DESC, first_name ASC, last_name ASC";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next())
            System.out.printf("%-20s%-20s%d\n",
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3));
    }

    @Test(description = "cinsiyetlere göre yas ortalamalari")
    public void sql4() throws SQLException {
        String sql="SELECT gender,ROUND(AVG (age),0) AS yasOrtalamasi" +
                " FROM personel" +
                " GROUP BY gender;";

        resultSet= statement.executeQuery(sql);

        while (resultSet.next())
            System.out.printf("%-20s%.2f\n",resultSet.getString(1),resultSet.getDouble(2));
    }

    @Test(description = "Field headers")
    public void test3() throws SQLException {
        String sql = "SELECT gender, AVG(age) AS sayi" +
                " FROM personel" +
                " GROUP BY gender";
        resultSet = statement.executeQuery(sql);
        ResultSetMetaData rsmd = resultSet.getMetaData();


        System.out.printf("%-20s%s\n",rsmd.getColumnName(1), rsmd.getColumnName(2));
        while (resultSet.next())
            System.out.printf("%-20s%.2f\n", resultSet.getString(1), resultSet.getDouble(2));
    }




    @Test(description = "consola field basliklari ile datalarini for(int i=1;...)  ile yazdirin")
    public void test4() throws SQLException {
        String sql = "SELECT id, first_name, last_name, age, gender FROM personel LIMIT 5;";
        resultSet = statement.executeQuery(sql);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int rowNum = rsmd.getColumnCount();

        for (int i = 1; i <= rowNum ; i++) {
            System.out.printf("%20s", rsmd.getColumnName(i));
        }
        System.out.println();
        while (resultSet.next()) {
            for (int i = 1; i <= rowNum; i++) {
                System.out.printf("%20s", resultSet.getString(i));
            }
            System.out.println();
        }
        //  id    first_name               first_name
    }


    // personel tablosunu SELECT * ile cagirin
    // consola field basliklari ile datalarini for(int i=1;...)  ile yazdirin
    @Test(description = "consola field basliklari ile datalarini for(int i=1;...)  ile yazdirin")
    public void test5() throws SQLException {
        String sql = "SELECT * FROM actor;";
        resultSet = statement.executeQuery(sql);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int rowNum = rsmd.getColumnCount();

        String format = "";
        String[] headers = new String[rowNum];
        for (int i = 1; i <= rowNum; i++) {
            int size = rsmd.getColumnDisplaySize(i) > 30 ? 30 : rsmd.getColumnDisplaySize(i);
            size = Math.max(size, rsmd.getColumnName(i).length());
            format += "%-" + size + "s";
            headers[i - 1] = rsmd.getColumnName(i);
        }
        format += "\n";
        System.out.printf(format, headers);

        String[] values = new String[rowNum];
        while (resultSet.next()) {
            for (int i = 1; i <= rowNum; i++) {
                values[i - 1] = resultSet.getString(i);
            }
            System.out.printf(format, values);
        }
    }
}
