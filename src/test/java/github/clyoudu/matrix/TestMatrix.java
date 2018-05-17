package github.clyoudu.matrix;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/15 17:27
 * @Description TestMatrix
 */
public class TestMatrix {

    public static void main(String[] args){
        Integer[][] data = {
                {1,3,1,7,3,1},
                {3,5,2,2,6,3},
                {1,2,3,3,2,7},
                {7,2,3,3,2,1},
                {3,6,2,2,5,3},
                {1,3,7,1,3,6}
        };

        Matrix<Integer> simpleMatrix = new SimpleMatrix<>();
        simpleMatrix.init(data);
        simpleMatrix.print();

        System.out.println("==========================================================");

        Matrix<Integer> symmetricMatrix = new SymmetricMatrix<>();
        symmetricMatrix.init(data);
        symmetricMatrix.print();

        System.out.println("==========================================================");

        Matrix<Integer> sparseMatrix = new SparseMatrix<>();
        sparseMatrix.init(data);
        sparseMatrix.print();

        System.out.println("==========================================================");

        Matrix<Integer> diagonalMatrix = new DiagonalMatrix<>();
        diagonalMatrix.init(new Integer[][]{
                {1,0,0,0,0,0},
                {0,1,0,0,0,0},
                {0,0,1,0,0,0},
                {0,0,0,1,0,0},
                {0,0,0,0,1,0},
                {0,0,0,0,0,1}
        });
        diagonalMatrix.print();
    }

}
