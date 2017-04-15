package node;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class NodeTest {

    final Node linear = new Linear();
    final Node sigmoid = new Sigmoid();

    @Before
    public void prepare() {
    }

    @Test
    public void Liear_함수_테스트() {
        // 선형 함수는 입력된 값을 그대로 리턴한다.
        assertThat(linear.phi(103d), is(103d));
        assertThat(linear.phi(Double.MAX_VALUE), is(Double.MAX_VALUE));
        assertThat(linear.phi(Double.MIN_VALUE), is(Double.MIN_VALUE));
    }

    @Test
    public void Sigmoid_함수_테스트() {
        // sigmoid 함수는 출력이 0 ~ 1 사이로 제한된다.
        assertThat(sigmoid.phi(0d), is(1 / 2d));
        assertTrue(sigmoid.phi(Double.MAX_VALUE) <= 1);
        assertTrue(sigmoid.phi(Double.MAX_VALUE) >= 0);
        assertTrue(sigmoid.phi(Double.MIN_VALUE) <= 1);
        assertTrue(sigmoid.phi(Double.MIN_VALUE) >= 0);
        assertTrue(sigmoid.phi(0d) < sigmoid.phi(1d));
        assertTrue(sigmoid.phi(-1d) < sigmoid.phi(0d));
    }
}