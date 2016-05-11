package timetable;

/**
 * Created by admin on 11.05.16.
 */
public class OrderFailException extends java.lang.Exception {
    private Time latenessTime;

    public OrderFailException(Time latenessTime) {
        this.latenessTime = latenessTime;
    }

    public Time getLatenessTime() {
        return latenessTime;
    }
}
