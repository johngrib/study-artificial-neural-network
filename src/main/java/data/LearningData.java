package data;

import java.util.Arrays;
import java.util.List;

/**
 * 학습 데이터
 * Created by johngrib on 2017. 4. 14..
 */
public class LearningData<T> {

    final List<T> input;
    final T expect;

    public LearningData(final T expect, final List<T> input) {
        this.input = input;
        this.expect = expect;
    }

    public List<T> getInputList() {
        return input;
    }

    public T get(final int index) {
        return input.get(index);
    }

    public T getExpect() {
        return expect;
    }

    public static LearningData create(final Double expect, final List<Double> input) {
        return new LearningData<Double>(expect, input);
    }

    public static LearningData create(final Double expect, final Double... input) {
        return new LearningData<Double>(expect, Arrays.asList(input));
    }
}
