package github.clyoudu.matrix;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/15 17:23
 * @Description SimpleMatrix
 */
public class SimpleMatrix<T> implements Matrix<T> {

    Object[][] data;

    @Override
    public void init(T[][] data) {
        this.data = data;
    }

    @Override
    public void print() {
        List<String> rows = new ArrayList<>();
        for (Object[] row : data) {
            rows.add(StringUtils.join(row," "));
        }
        System.out.println(StringUtils.join(rows,"\n"));
    }
}
