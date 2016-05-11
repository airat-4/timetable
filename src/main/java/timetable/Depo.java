package timetable;

/**
 * Created by admin on 11.05.16.
 */
public class Depo extends Node{

    public Depo(String openTime, String closeTime) {
        this.openTime = new Time(openTime);
        this.closeTime = new Time(closeTime);
    }

    public Time getOptimalStartTime() throws OrderFailException {
        Time optimalStartTime  =new Time(openTime);
        Time waitingTime = nextNode.getWaitingTime(optimalStartTime.getSumTime(nextNodeTime).getSumTime(orderTime));
        optimalStartTime = optimalStartTime.getSumTime(waitingTime);
        for(;;){
            try {
                nextNode.getWaitingTime(optimalStartTime.getSumTime(nextNodeTime).getSumTime(orderTime));
                return optimalStartTime;
            }catch (OrderFailException e){
                optimalStartTime = optimalStartTime.getDifTime(e.getLatenessTime());
            }
        }
    }

    public Time getWaitingTime(Time startTime) throws OrderFailException {
        if(startTime.larger(closeTime))
            throw new OrderFailException(startTime.getDifTime(closeTime));
        return new Time(0, 0);
    }

    @Override
    public void print(Time prevEndTime, Time nextStartTime, int orderNumber) {
        System.out.println("Возвращение в депо "+ prevEndTime + "-" + nextStartTime);
    }

    public void printTimetable(Time startTime){
        Time sumTime = startTime.getSumTime(orderTime);
        System.out.println("Загрузка в депо " + startTime + "-" + sumTime);
        Time nextStartTime = sumTime.getSumTime(nextNodeTime);
        nextNode.print(sumTime, nextStartTime, 1);
    }

}
