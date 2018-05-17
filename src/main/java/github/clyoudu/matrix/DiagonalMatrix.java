package github.clyoudu.matrix;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/16 10:18
 * @Description DiagonalMatrix
 */
public class DiagonalMatrix<T> implements Matrix<T> {

    Object[] data;

    @Override
    public void init(T[][] data) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(i == j){
                    T element = data[i][j];
                    result.add(element);
                }
            }
        }
        this.data = result.toArray();
    }

    @Override
    public void print() {
        List<String> rows = new ArrayList<>();
        for (int i = 0;i < data.length;i ++) {
            List row = new ArrayList();
            for (int j = 0; j < data.length; j++) {
                if(i==j){
                    row.add(data[i]);
                }else{
                    row.add(data[0] instanceof Number ? 0 : null);
                }
            }
            rows.add(StringUtils.join(row," "));
        }
        System.out.println(StringUtils.join(rows,"\n"));
    }
}
