package github.clyoudu.matrix;

import github.clyoudu.list.ArrayList;
import github.clyoudu.list.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/15 17:38
 * @Description SymmetricMatrix
 */
public class SymmetricMatrix<T> implements Matrix<T> {

    Object[] data;

    @Override
    public void init(T[][] data) {
        int length = data.length * (data.length + 1) / 2;
        this.data = new Object[length];
        int index = 0;

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(i >= j){
                    T element = data[i][j];
                    if(element != null && ((Number)element).doubleValue() != 0D){
                        this.data[index ++] = element;
                    }
                }

            }
        }
    }

    @Override
    public void print() {
        List<String> rows = new ArrayList<>();

        int n = new Double(Math.sqrt(2D * data.length + 1 / 4) - 1 / 2).intValue();
        for (int i = 0; i < n; i++) {
            List<T> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int max = Math.max(i,j);
                int min = Math.min(i,j);

                int index = max * (max + 1) / 2 + min;
                row.add((T) data[index]);
            }
            rows.add(StringUtils.join(row," "));
        }

        System.out.println(StringUtils.join(rows,"\n"));
    }
}
