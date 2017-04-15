package util;

import java.util.ArrayList;
import java.util.List;

/**
 * 행렬 연산 유틸리티
 * Created by johngrib on 2017. 4. 15..
 */
public class MatrixUtils {

    /**
     * 간단한 X*1 행렬과 1*X 행렬의 곱을 수행한다.
     * @param left
     * @param right
     * @return
     */
    public static Double simpleMultiply(List<Double> left, List<Double> right) {

        final List<Double> rs = new ArrayList<>(left.size());

        for (int i = 0; i < left.size(); i++) {
            rs.add(left.get(i) * right.get(i));
        }

        final Double result = rs.stream()
                .reduce((x, y) -> x + y)
                .get();

        return result;
    }
}
