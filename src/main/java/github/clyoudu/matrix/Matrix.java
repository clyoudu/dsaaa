package github.clyoudu.matrix;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/15 17:22
 * @Description Matrix
 */
public interface Matrix<T> {

    /**
     * 初始化
     * @param data
     */
    void init(T[][] data);

    /**
     * 打印
     */
    void print();

}
