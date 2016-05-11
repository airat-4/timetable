package timetable;

/**
 * Created by admin on 11.05.16.
 */
public class Order extends Node{


    public Order(String openTime, String closeTime) {
        this.openTime = new Time(openTime);
        this.closeTime = new Time(closeTime);
    }



    public Time getWaitingTime(Time startTime) throws OrderFailException {
        Time endTime = startTime.getSumTime(orderTime);
        if(endTime.larger(closeTime))
            throw new OrderFailException(endTime.getDifTime(closeTime));
        if(openTime.larger(startTime)){
            Time waitingTime = openTime.getDifTime(startTime);
            return waitingTime.getSumTime(nextNode.getWaitingTime(openTime.getSumTime(orderTime).getSumTime(nextNodeTime)));
        }else{
            return nextNode.getWaitingTime(endTime.getSumTime(nextNodeTime));
        }
    }

    @Override
    public void print(Time prevEndTime, Time nextStartTime, int orderNumber) {
        if(openTime.larger(nextStartTime)){
            Time waitingTime = openTime.getDifTime(nextStartTime);
            System.out.println("Ожидание " + prevEndTime + "-" + prevEndTime.getSumTime(waitingTime));
            prevEndTime = prevEndTime.getSumTime(waitingTime);
            nextStartTime = nextStartTime.getSumTime(waitingTime);
        }
        System.out.println("Поездка к заказу "+ orderNumber+ " " + prevEndTime+ "-" + nextStartTime);
        Time sumTime = nextStartTime.getSumTime(orderTime);
        System.out.println("Выполнениее заказа "+ orderNumber + " " + nextStartTime + "-" + sumTime);
        orderNumber++;
        nextNode.print(sumTime, sumTime.getSumTime(nextNodeTime),orderNumber);
    }


}
