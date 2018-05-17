package github.clyoudu.matrix;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/16 9:52
 * @Description SparseMatrix
 */
public class SparseMatrix<T> implements Matrix<T> {

    Object[][] data;

    @Override
    public void init(T[][] data) {
        List<Object[]> list = new ArrayList<>();
        int nonZeroNum = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                T element = data[i][j];
                if (element != null && ((Number) element).doubleValue() != 0D) {
                    nonZeroNum ++;
                    list.add(new Object[]{i,j,element});
                }
            }
        }

        list.add(0,new Object[]{data.length,data[0].length,nonZeroNum});
        this.data = new Object[list.size()][3];
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = list.get(i);
        }
    }

    @Override
    public void print() {
        T[][] result = (T[][]) new Object[(int) data[0][0]][(int) data[0][1]];
        for (int i = 1; i < data.length; i++) {
            result[(int) data[i][0]][(int) data[i][1]] = (T) data[i][2];
        }

        List<String> rows = new ArrayList<>();
        for (Object[] row : result) {
            rows.add(StringUtils.join(row," "));
        }
        System.out.println(StringUtils.join(rows,"\n"));
    }
}
