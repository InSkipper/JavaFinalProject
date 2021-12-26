import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        var transactions = parseTransactionCsv("D:\\Загрузки\\Переводы.csv");
        DbConn.conn();
        DbConn.createDB();
//        DbConn.writeDB(transactions);
//        makeTask1(DbConn.readDBTask1());
        makeTask2();
        makeTask3();
        DbConn.closeDB();
    }

    private static void makeTask1(List<Task_1> list) {
        var dataset = new TimeSeries("Доллары по месяцам");
        list.forEach(period -> dataset.add(new Month(period.getPeriod()), period.getDataValue()));
        var chart = ChartFactory.createTimeSeriesChart(
                "Распределение сумм всех переводов за 2020 год",
                "Month",
                "Amount of Dollars",
                new TimeSeriesCollection(dataset));
        try {
            ChartUtilities.saveChartAsJPEG(new File("chart.JPEG"), chart, 1000, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makeTask2() {
        try {
            DbConn.readDBTask2();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void makeTask3() {
        try {
            DbConn.readDBTask3();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Transaction> parseTransactionCsv(String path) {
        var filePath = Paths.get(path);
        var transactions = new ArrayList<Transaction>();
        try (var reader = Files.newBufferedReader(filePath)) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                var split = line.split(",", -1);
                var transaction = new Transaction();
                transaction.setSeriesReference(split[0]);
                transaction.setPeriod(split[1]);
                transaction.setDataValue(split[2]);
                transaction.setSuppressed(split[3]);
                transaction.setStatus(split[4]);
                transaction.setUnits(split[5]);
                transaction.setMagnitude(split[6]);
                transaction.setSubject(split[7]);
                transaction.setGroup(split[8]);
                transaction.setSeriesTitle1(split[9]);
                transaction.setSeriesTitle2(split[10]);
                transaction.setSeriesTitle3(split[11]);
                transaction.setSeriesTitle4(split[12]);
                transaction.setSeriesTitle5(split[13]);
                transactions.add(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}

