import github.clyoudu.list.CircularLinkedList;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/3 16:20
 * @Description TestCircularLinkedList
 */
public class TestCircularLinkedList {

    public static void main(String[] args){
        //约瑟夫环
        //已知n个人（以编号1，2，3...n分别表示）围坐在一张圆桌周围。
        //从编号为k的人开始报数，数到m的那个人出列；
        //他的下一个人又从1开始报数，数到m的那个人又出列；
        //依此规律重复下去，直到圆桌周围的人全部出列。
        //打印游戏过程。
        joseph(new CircularLinkedList<>("a","b","c","d","e"),3,5);
    }

    private static void joseph(CircularLinkedList<String> users,Integer startNo,Integer countNo){
        if(startNo > users.size()){
            throw new IndexOutOfBoundsException(startNo + " > " + users.size());
        }

        if(countNo <= 0){
            throw new IllegalArgumentException("m <= 0");
        }

        System.out.println("游戏开始>>>>>>>>>>>：" + users.toString());
        System.out.println();

        Integer currentNo = 0;
        Boolean start = false;
        Iterator<String> iterator = users.iterator();

        while(iterator.hasNext()){
            currentNo ++;
            String user = iterator.next();
            if(!start && currentNo.equals(startNo)){
                start = true;
                currentNo = 1;
            }
            if(currentNo.equals(countNo)){
                iterator.remove();
                currentNo = 0;

                System.out.println(user + ">>出列>>");
                System.out.println("剩余：" + users.toString());
                System.out.println();
            }
        }

        System.out.println("游戏结束>>>>>>>>>>>");
    }



}
