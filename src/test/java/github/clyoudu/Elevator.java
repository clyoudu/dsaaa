package github.clyoudu;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/11 14:08
 * @Description Elevator
 */
public class Elevator {

    public static void main(String[] args) {

        //乘客队列
        Map<Integer, Map<Integer,Queue<Passenger>>> passengersQueue = new HashMap<>();

        //构造电梯
        LinkedList<Integer> elevator = new LinkedList<>();
        for (int i = 1; i <= 21; i++) {
            elevator.add(i);
            Map<Integer,Queue<Passenger>> map = new HashMap<>();
            map.put(1,new ArrayBlockingQueue<>(100));
            map.put(-1,new ArrayBlockingQueue<>(100));
            passengersQueue.put(i, map);
        }

        //最大负载
        int maxLoad = 5;
        //当前乘客列表
        List<Passenger> passenger = new ArrayList<>();

        //运行
        new Thread(() -> {
            int currentFloor = 1;
            int direction = 1;
            Iterator<Integer> iterator = elevator.iterator();
            while (true) {
                if ((!iterator.hasNext() && direction == 1) || ((direction == 1) && (currentFloor = iterator.next()) == 20)) {
                    direction = -1;
                    iterator = elevator.descendingIterator();
                } else if ((!iterator.hasNext() && direction == -1) || ((direction == -1) && (currentFloor = iterator.next()) == 2)) {
                    direction = 1;
                    iterator = elevator.iterator();
                }

                System.out.println("Elevator: Floor " + currentFloor);
                System.out.println("Elevator: Direction " + direction);

                Iterator<Passenger> passengerIterator = passenger.iterator();
                List<Passenger> toUnLoad = new ArrayList<>();
                while (passengerIterator.hasNext()) {
                    Passenger passengerOn = passengerIterator.next();
                    if (passengerOn.to == currentFloor) {
                        toUnLoad.add(passengerOn);
                        passengerIterator.remove();
                    }
                }
                if (!toUnLoad.isEmpty()) {
                    System.out.println("Elevator: Out  >> " + StringUtils.join(toUnLoad.stream().map(Passenger::toString).collect(Collectors.toList()), ","));
                }

                if (passenger.size() < maxLoad && !passengersQueue.get(currentFloor).get(direction).isEmpty()) {
                    int maxIn = maxLoad - passenger.size();
                    Queue<Passenger> queue = passengersQueue.get(currentFloor).get(direction);
                    List<Passenger> toLoad = new ArrayList<>();
                    while (toLoad.size() < maxIn && !queue.isEmpty()) {
                        toLoad.add(queue.poll());
                    }
                    passenger.addAll(toLoad);
                    System.out.println("Elevator: In   >> " + StringUtils.join(toLoad.stream().map(Passenger::toString).collect(Collectors.toList()), ","));
                }

                System.out.println("Elevator: Load >> " + StringUtils.join(passenger.stream().map(Passenger::toString).collect(Collectors.toList()), ","));
                System.out.println("==========================================================");
                System.out.println();

                sleep(500L);
            }
        }).start();

        //模拟乘坐
        /*Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine();
            if(StringUtils.isNotBlank(input)){
                String[] user = input.split(",");
                if(user.length == 4){
                    Passenger passengerEx = new Passenger(user[0],Integer.parseInt(user[1]),Integer.parseInt(user[2]),Integer.parseInt(user[3]));
                    passengersQueue.get(passengerEx.from).add(passengerEx);
                }
            }
        } while (!"-1".equals(input));*/

        addPassenger(passengersQueue,new Passenger("1",1,6,12));
        sleep(5000L);
        addPassenger(passengersQueue,new Passenger("bbb",-1,6,1));
        addPassenger(passengersQueue,new Passenger("2",1,1,5));
        addPassenger(passengersQueue,new Passenger("3",1,1,6));
        sleep(5000L);
        addPassenger(passengersQueue,new Passenger("4",1,1,16));
        addPassenger(passengersQueue,new Passenger("5",1,1,6));
        sleep(5000L);
        addPassenger(passengersQueue,new Passenger("6",1,1,21));
        addPassenger(passengersQueue,new Passenger("7",1,1,8));
        sleep(5000L);
        addPassenger(passengersQueue,new Passenger("8",1,1,7));
        addPassenger(passengersQueue,new Passenger("9",1,1,20));
        sleep(5000L);
        addPassenger(passengersQueue,new Passenger("10",1,1,7));
        addPassenger(passengersQueue,new Passenger("11",1,1,8));
        sleep(5000L);
        addPassenger(passengersQueue,new Passenger("12",1,1,15));
    }

    private static void addPassenger(Map<Integer, Map<Integer, Queue<Passenger>>> passengersQueue, Passenger passenger) {
        passengersQueue.get(passenger.from).get(passenger.direction).add(passenger);
    }

    private static void sleep(long mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Passenger {
        private String name;
        private Integer direction;
        private Integer from;
        private Integer to;

        public Passenger(String name, Integer direction, Integer from, Integer to) {
            this.name = name;
            this.direction = direction;
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[name: " + name + ",direction: " + direction + ",from: " + from + ",to: " + to + "]";
        }
    }

}
