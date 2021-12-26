import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DbConn {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void conn() throws ClassNotFoundException, SQLException {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:Transactions.db");

        System.out.println("База Подключена!");
    }

    public static void createDB() throws SQLException {
        statement = connection.createStatement();
        statement.execute(
                "CREATE TABLE if not exists 'transactions' " +
                        "('Series_reference' text," +
                        "'Period' text," +
                        "'Data_value' FLOAT," +
                        "'Suppressed' text," +
                        "'STATUS' text," +
                        "'UNITS' text," +
                        "'Magnitude' text," +
                        "'Subject' text," +
                        "'Group' text," +
                        "'Series_title_1' text," +
                        "'Series_title_2' text," +
                        "'Series_title_3' text," +
                        "'Series_title_4' text," +
                        "'Series_title_5' text);");

        System.out.println("Таблица создана или уже существует.");
    }

    public static void writeDB(List<Transaction> transactions) throws SQLException {
        var pStmt = connection.prepareStatement(
                "INSERT INTO 'transactions' " +
                        "('Series_reference'," +
                        "'Period'," +
                        "'Data_value'," +
                        "'Suppressed'," +
                        "'STATUS'," +
                        "'UNITS'," +
                        "'Magnitude'," +
                        "'Subject'," +
                        "'Group'," +
                        "'Series_title_1'," +
                        "'Series_title_2'," +
                        "'Series_title_3'," +
                        "'Series_title_4'," +
                        "'Series_title_5') " +
                        "VALUES (?, ?, ROUND(?,1), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ");
        for (var transaction : transactions) {
            var period = new SimpleDateFormat(transaction.getDatePattern()).format(transaction.getPeriod());
            pStmt.setString(1, transaction.getSeriesReference());
            pStmt.setString(2, period);
            pStmt.setFloat(3, transaction.getDataValue());
            pStmt.setString(4, transaction.getSuppressed());
            pStmt.setString(5, transaction.getStatus());
            pStmt.setString(6, transaction.getUnits());
            pStmt.setString(7, transaction.getMagnitude());
            pStmt.setString(8, transaction.getSubject());
            pStmt.setString(9, transaction.getGroup());
            pStmt.setString(10, transaction.getSeriesTitle1());
            pStmt.setString(11, transaction.getSeriesTitle2());
            pStmt.setString(12, transaction.getSeriesTitle3());
            pStmt.setString(13, transaction.getSeriesTitle4());
            pStmt.setString(14, transaction.getSeriesTitle5());
            pStmt.executeUpdate();
        }
        System.out.println();
        System.out.println("Таблица заполнена");
        System.out.println();
    }

    public static List<Task_1> readDBTask1() throws SQLException {
        resultSet = statement.executeQuery(
                "SELECT Period, SUM(Data_value) AS Data_value, CAST(substr(Period, 1, 4) as INTEGER) as Year, UNITS " +
                        "FROM transactions " +
                        "WHERE UNITS = 'Dollars' AND YEAR=2020 " +
                        "GROUP BY Period ORDER BY Period");
        var list = new ArrayList<Task_1>();
        while (resultSet.next()) {
            var period = resultSet.getString("Period");
            var dataValue = resultSet.getFloat("Data_value");
            list.add(new Task_1(period, dataValue));
        }

        return list;
    }

    public static void readDBTask2() throws SQLException {
        resultSet = statement.executeQuery(
                "SELECT Period, avg(Data_value) AS Average, COUNT(Data_value) AS Count, UNITS " +
                        "FROM transactions " +
                        "WHERE UNITS = 'Dollars' " +
                        "GROUP BY Period ORDER BY Period");
        printTaskName("Задача 2", '~');
        printTaskName("Вывести в консоль средний размер переводов в долларах, " +
                        "а так же их количество, за каждый уникальный период.",
                '=');
        while (resultSet.next()) {
            var period = resultSet.getString("Period");
            var average = resultSet.getFloat("Average");
            var count = resultSet.getInt("Count");
            var units = resultSet.getString("UNITS");
            System.out.println("Period = " + period);
            System.out.println("Average = " + average);
            System.out.println("Count = " + count);
//            System.out.println("Units = " + units);
            System.out.println("------------------------");
        }
        System.out.println();
        System.out.println("Таблица выведена");
    }

    public static void readDBTask3() throws SQLException {
        resultSet = statement.executeQuery(
                "SELECT Period, MAX(Data_value) AS Max, Min(Data_value) AS Min, " +
                        "CAST(substr(Period, 1, 4) as INTEGER) as Year, UNITS " +
                        "FROM transactions " +
                        "WHERE UNITS = 'Dollars' AND (Year=2014 OR Year=2016 OR Year=2020)" +
                        "GROUP BY Period ORDER BY Period");
        printTaskName("Задача 3", '~');
        printTaskName("Вывести в консоль максимальный и минимальный перевод в долларах за 2014, 2016 и 2020 год.",
                '=');
        while (resultSet.next()) {
            var period = resultSet.getString("Period");
            var max = resultSet.getFloat("Max");
            var min = resultSet.getFloat("Min");
            var units = resultSet.getString("UNITS");
            System.out.println("Period = " + period);
            System.out.println("Min = " + min);
            System.out.println("Max = " + max);
            System.out.println("Units = " + units);
            System.out.println("------------------------");
        }
    }

    public static void closeDB() throws SQLException {
        connection.close();
        statement.close();
        resultSet.close();

        System.out.println("Соединения закрыты");
    }

    private static void printTaskName(String taskName, Character symbol) {
        var length = taskName.length();
        var sb = new StringBuilder();
        for (var i = 0; i < length; i++)
            sb.append(symbol);
        System.out.println(sb);
        System.out.println(taskName);
        System.out.println(sb);
    }
}