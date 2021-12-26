import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task_1 {
    private Date period;
    private final float dataValue;

    public Task_1(String period, float dataValue) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy.MM").parse(period);
            this.period = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.dataValue = dataValue;
    }

    public Date getPeriod() {
        return period;
    }

    public float getDataValue() {
        return dataValue;
    }
}
