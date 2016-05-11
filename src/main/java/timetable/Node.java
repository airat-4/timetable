package timetable;

/**
 * Created by admin on 11.05.16.
 */
public abstract class Node {
    protected Time openTime;
    protected Time closeTime;
    protected Node nextNode;
    protected Time nextNodeTime;
    protected Time orderTime;
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
    public void setOrderTime(String orderTime) {
        this.orderTime = new Time(orderTime);
    }

    public void setNextNodeTime(String nextNodeTime) {
        this.nextNodeTime = new Time(nextNodeTime);
    }
    protected abstract Time getWaitingTime(Time startTime) throws OrderFailException;
    protected abstract void print(Time prevEndTime,Time nextStartTime, int orderNumber);
}
