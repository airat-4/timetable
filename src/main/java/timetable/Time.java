package timetable;

/**
 * Created by admin on 11.05.16.
 */
public class Time {
    private int h;
    private int m;

    public Time(int h, int m) {
        this.h = h;
        this.m = m;
        if(h >= 24 || m >=60)
            throw new IllegalArgumentException();
    }

    public Time(String time) {
        String[] split = time.split(":");
        h = Integer.parseInt(split[0],10);
        m = Integer.parseInt(split[1],10);
        if(h >= 24 || m >=60)
            throw new IllegalArgumentException();
    }

    public Time(Time time){
        h = time.h;
        m = time.m;
    }

    public Time getSumTime(Time time){
        Time sumTime = new Time(this);
        sumTime.h += time.h;
        sumTime.m += time.m;
        if(sumTime.m >= 60){
            sumTime.h++;
            sumTime.m %= 60;
        }
        sumTime.h %= 24;
        return sumTime;
    }

    public Time getDifTime(Time time){
        Time difTime = new Time(this);
        difTime.h -= time.h;
        difTime.m -= time.m;
        if(difTime.m < 0){
            difTime.h--;
            difTime.m = 60 + difTime.m;
        }
        if(difTime.h<0){
            difTime.h = 24 + difTime.h;
        }
        return difTime;
    }

    public boolean larger(Time time){
        if(h != time.h)
            return h > time.h;
        else return m > time.m;
    }

    @Override
    public String toString() {
        return "" + h+ ':' +(m < 10? "0" : "")+m;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Time time = (Time) o;

        if (h != time.h) return false;
        return m == time.m;

    }

    @Override
    public int hashCode() {
        int result = h;
        result = 31 * result + m;
        return result;
    }
}
