package rule;

import data.LearningData;
import data.LearningDataSet;
import node.Node;
import node.Sigmoid;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by johngrib on 2017. 4. 14..
 */
public class RuleTest {

    @Before
    public void prepare() {

    }

    @Test
    public void DeltaSGC_테스트() {

        final Double alpha = 0.9d;
        final Double expectErrorLevel = 0.01;

        final List<Double> randomWeight = Arrays.asList(
                new Double[]{Math.random(), Math.random(), Math.random()}
        );

        final Node node = new Sigmoid().setWeightValues(randomWeight);
        final Rule rule = new DeltaSGD(alpha, node, expectErrorLevel);  // SGD 학습 방식을 사용한다.

        final LearningDataSet dataSet = new LearningDataSet()
                .add(LearningData.create(0d, Arrays.asList(new Double[]{0d, 0d, 1d})))
                .add(LearningData.create(0d, Arrays.asList(new Double[]{0d, 1d, 1d})))
                .add(LearningData.create(1d, Arrays.asList(new Double[]{1d, 0d, 1d})))
                .add(LearningData.create(1d, Arrays.asList(new Double[]{1d, 1d, 1d})));

        rule.run(dataSet);

        assertTrue(rule.getLastErrorAbs() <= expectErrorLevel);
    }

    @Test
    public void DeltaBatch_테스트() {

        final Double alpha = 0.9d;
        final Double expectErrorLevel = 0.01;

        final List<Double> randomWeight = Arrays.asList(
                new Double[]{Math.random(), Math.random(), Math.random()}
        );

        final Node node = new Sigmoid().setWeightValues(randomWeight);
        final Rule rule = new DeltaBatch(alpha, node, expectErrorLevel);    // Batch 학습 방법을 사용한다.

        final LearningDataSet dataSet = new LearningDataSet()
                .add(LearningData.create(0d, Arrays.asList(new Double[]{0d, 0d, 1d})))
                .add(LearningData.create(0d, Arrays.asList(new Double[]{0d, 1d, 1d})))
                .add(LearningData.create(1d, Arrays.asList(new Double[]{1d, 0d, 1d})))
                .add(LearningData.create(1d, Arrays.asList(new Double[]{1d, 1d, 1d})));

        rule.run(dataSet);

        assertTrue(rule.getLastErrorAbs() <= expectErrorLevel);
    }

    @Test
    public void Delta_규칙의_한계() {
        final Double alpha = 0.9d;
        final Double expectErrorLevel = 0.01;
        final Integer epochLimit = 10000;   // 끝없이 돌아갈 수 있으므로 limit 를 정해준다.

        final List<Double> randomWeight = Arrays.asList(
                new Double[]{Math.random(), Math.random(), Math.random()}
        );

        final Node node = new Sigmoid().setWeightValues(randomWeight);
        final Rule rule = new DeltaBatch(alpha, node, expectErrorLevel, epochLimit);    // Batch 방식을 사용한다.

        final LearningDataSet dataSet = new LearningDataSet()
                .add(LearningData.create(0d, Arrays.asList(new Double[]{0d, 0d, 1d})))
                .add(LearningData.create(1d, Arrays.asList(new Double[]{0d, 1d, 1d})))
                .add(LearningData.create(1d, Arrays.asList(new Double[]{1d, 0d, 1d})))
                .add(LearningData.create(0d, Arrays.asList(new Double[]{1d, 1d, 1d})));

        rule.run(dataSet);

        // 위와 같은 데이터로는 원하는 오차 수준에 도달하지 못한다.
        assertFalse(rule.getLastErrorAbs() <= expectErrorLevel);
    }
}