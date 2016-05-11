package timetable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by admin on 11.05.16.
 */
public class Timetable {
    Depo depo;
    public static void main(String[] args) {
        Timetable timetable = new Timetable();
        timetable.printTimeTable();
    }

    private void printTimeTable() {
        loadData();
        try {
            Time optimalStartTime = depo.getOptimalStartTime();
            depo.printTimetable(optimalStartTime);
        } catch (OrderFailException e) {
            System.out.println("Нельзя построить рассписание в заданных временных рамках");
        }
    }

    private void loadData() {
        try {
            Scanner scanner = new Scanner(new File("data.txt"));
            String depoTime = scanner.nextLine();
            String[] split = depoTime.split("-");
            depo = new Depo(split[0], split[1]);
            depo.setOrderTime(scanner.nextLine());
            int amountOrder = scanner.nextInt();
            scanner.nextLine();
            Order[] orders = new Order[amountOrder];
            for (int i = 0; i < orders.length; ++i){
                String orderOpenCloseTime = scanner.nextLine();
                split = orderOpenCloseTime.split("-");
                orders[i] = new Order(split[0], split[1]);
            }
            for (int i = 0; i < orders.length; ++i){
                String orderTime = scanner.nextLine();
                orders[i].setOrderTime(orderTime);
            }
            split = scanner.nextLine().split(" ");
            depo.setNextNode(orders[Integer.parseInt(split[0]) - 1]);
            for (int i = 0; i < split.length - 1; i++) {
                orders[Integer.parseInt(split[i]) - 1].setNextNode(orders[Integer.parseInt(split[i + 1]) - 1]);
            }
            orders[Integer.parseInt(split[split.length - 1]) - 1].setNextNode(depo);
            split = scanner.nextLine().split(" ");
            depo.setNextNodeTime(split[0]);
            for (int i = 1; i < split.length; i++) {
                orders[i - 1].setNextNodeTime(split[i]);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл data.txt не найден");
        }
    }
}
