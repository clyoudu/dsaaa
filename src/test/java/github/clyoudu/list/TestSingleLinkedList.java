package github.clyoudu.list;

import github.clyoudu.list.SingleLinkedList;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2018/4/26
 * @time 23:01
 * @desc github.clyoudu.list.TestSingleLinkedList
 */
public class TestSingleLinkedList {

    public static void main(String[] args){
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        System.out.println(list.remove(51));
        System.out.println(list.indexOf(50));

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            iterator.remove();
        }

        System.out.println(list.size());
    }

}
