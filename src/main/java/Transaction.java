import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements Serializable {
    private String seriesReference;
    private Date period;
    private float dataValue;
    private String suppressed;
    private String status;
    private String units;
    private String magnitude;
    private String subject;
    private String group;
    private String seriesTitle1;
    private String seriesTitle2;
    private String seriesTitle3;
    private String seriesTitle4;
    private String seriesTitle5;
    private final String datePattern = "yyyy.MM";

    public Transaction() {
    }

    public String getSeriesReference() {
        return seriesReference;
    }

    public Date getPeriod() {
        return period;
    }

    public float getDataValue() {
        return dataValue;
    }

    public String getSuppressed() {
        return suppressed;
    }

    public String getStatus() {
        return status;
    }

    public String getUnits() {
        return units;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getSubject() {
        return subject;
    }

    public String getGroup() {
        return group;
    }

    public String getSeriesTitle1() {
        return seriesTitle1;
    }

    public String getSeriesTitle2() {
        return seriesTitle2;
    }

    public String getSeriesTitle3() {
        return seriesTitle3;
    }

    public String getSeriesTitle4() {
        return seriesTitle4;
    }

    public String getSeriesTitle5() {
        return seriesTitle5;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setSeriesReference(String seriesReference) {
        this.seriesReference = seriesReference;
    }

    public void setPeriod(String period) {
        try {
            this.period = new SimpleDateFormat(datePattern).parse(period);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setDataValue(String dataValue) {
        if (dataValue.equals(""))
            this.dataValue = 0;
        else
            this.dataValue = Float.parseFloat(dataValue);
    }

    public void setSuppressed(String suppressed) {
        this.suppressed = suppressed;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setSeriesTitle1(String seriesTitle1) {
        this.seriesTitle1 = seriesTitle1;
    }

    public void setSeriesTitle2(String seriesTitle2) {
        this.seriesTitle2 = seriesTitle2;
    }

    public void setSeriesTitle3(String seriesTitle3) {
        this.seriesTitle3 = seriesTitle3;
    }

    public void setSeriesTitle4(String seriesTitle4) {
        this.seriesTitle4 = seriesTitle4;
    }

    public void setSeriesTitle5(String seriesTitle5) {
        this.seriesTitle5 = seriesTitle5;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "seriesReference='" + seriesReference + '\'' +
                ", period=" + period +
                ", dataValue=" + dataValue +
                ", suppressed='" + suppressed + '\'' +
                ", status='" + status + '\'' +
                ", units='" + units + '\'' +
                ", magnitude='" + magnitude + '\'' +
                ", subject='" + subject + '\'' +
                ", group='" + group + '\'' +
                ", seriesTitle1='" + seriesTitle1 + '\'' +
                ", seriesTitle2='" + seriesTitle2 + '\'' +
                ", seriesTitle3='" + seriesTitle3 + '\'' +
                ", seriesTitle4='" + seriesTitle4 + '\'' +
                ", seriesTitle5='" + seriesTitle5 + '\'' +
                ", datePattern='" + datePattern + '\'' +
                '}';
    }
}
