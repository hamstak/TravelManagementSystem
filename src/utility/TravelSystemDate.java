package utility;

/**
 * Created by moth on 6/7/16.
 */
public class TravelSystemDate {
    private int month;
    private int day;
    private int year;
    private int minute;
    private int hour;
    
    public TravelSystemDate(int y, int m, int d, int h, int min){
        year = y;
        month = m;
        day = d;
        hour = h;
        minute = min;
    }
    
    public static TravelSystemDate createDateFromInput(){
        return new TravelSystemDate(UserUtil.getUserPositiveInt("Year"),
                UserUtil.getUserPositiveInt("Month"),
                UserUtil.getUserPositiveInt("Day"),
                UserUtil.getUserPositiveInt("Hour"),
                UserUtil.getUserPositiveInt("Minute"));
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }
    
    @Override
    public String toString(){
        return month + "/" + day + "/" + year + ", " + hour + ":" + minute;
    }
}
