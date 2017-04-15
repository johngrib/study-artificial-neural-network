package util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * 행렬 연산 유틸리티 테스트
 * Created by johngrib on 2017. 4. 16..
 */
public class MatrixUtilsTest {

    @Test
    public void test() {
        final List<Double> left = Arrays.asList(new Double[]{2d, 6d, 3d});
        final List<Double> right = Arrays.asList(new Double[]{1d, 5d, 7d});
        final Double result = MatrixUtils.simpleMultiply(left, right);
        assertThat(result, is(2d*1d + 6d*5d + 3d*7d));
    }

}