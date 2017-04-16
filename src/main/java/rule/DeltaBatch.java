package rule;

import data.LearningData;
import data.LearningDataSet;
import node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by johngrib on 2017. 4. 14..
 */
public class DeltaBatch extends Rule {

    private static final Logger log = LoggerFactory.getLogger(DeltaBatch.class);

    public DeltaBatch(double alpha, Node node, double expectErrorLevel) {
        super(alpha, node, expectErrorLevel);
    }

    public DeltaBatch(double alpha, Node node, double expectErrorLevel, int epochLimit) {
        super(alpha, node, expectErrorLevel, epochLimit);
    }

    /**
     * 학습을 수행한다.
     *
     * @param dataSet 학습 데이터
     * @return 학습을 끝낸 Node
     */
    public Node run(final LearningDataSet dataSet) {

        final List<Double> weightList = node.getWeightValueList();

        if (!validate(dataSet, weightList)) {
            throw new RuntimeException("학습을 수행할 수 없습니다.");
        }

        lastError = Double.MAX_VALUE;
        epoch = 0;

        while (Math.abs(lastError) > expectErrorLevel && epoch < epochLimit) {

            final List<Double> sumWeight = weightList.stream()
                    .map(x -> 0d)
                    .collect(Collectors.toList());

            lastError = 0d;
            for (final LearningData<Double> inputX : dataSet.list()) {

                final Double expectD = inputX.getExpect();
                final Double resultY = node.setInputValues(inputX.getInputList()).calc();

                final Double error = expectD - resultY;
                if(Math.abs(error) > Math.abs(lastError)) {
                    lastError = error;
                }

                log.info(
                        "입력:" + inputX.getInputList() +
                        ", 정답:" + expectD +
                        ", 결과:" + resultY +
                        ", 오차:" + error
                );

                for (int i = 0; i < weightList.size(); i++) {
                    final Double weight = node.getWeight(i);
                    final Double dW = getDeltaWeight(alpha, error, inputX.get(i));
                    sumWeight.set(i, sumWeight.get(i) + weight + dW);
                }
            }

            epoch += 1;


            final List<Double> newWeightList =  sumWeight.stream()
                    .map(w -> w / dataSet.list().size())
                    .collect(Collectors.toList());

            node.setWeightValues(newWeightList);
            log.info("*** 가중치 갱신:" + newWeightList + "\n");
        }
        log.info(this.toString());
        return node;
    }

    /**
     * weight(가중치) 갱신값을 구한다.
     * @param beforeW 이전 가중치
     * @param alpha 학습률
     * @param errorE 오차
     * @param inputX 입력값
     * @return
     */
    private Double getNewWeight(Double beforeW, Double alpha, Double errorE, Double inputX) {
        return beforeW + alpha * errorE * inputX;
    }

    /**
     * 학습 데이터와 가중치를 검증한다.
     * @param dataSet
     * @param weightList
     * @return
     */
    private boolean validate(LearningDataSet dataSet, List<Double> weightList) {
        if (weightList == null) {
            throw new RuntimeException("Weight(가중치)가 null 입니다.");
        }
        final boolean sizeMismatch = dataSet.list()
                .stream()
                .anyMatch(data -> data.getInputList().size() != weightList.size());

        if (sizeMismatch) {
            throw new RuntimeException("Weight(가중치)가 null 입니다.");
        }
        return true;
    }

    /**
     * weight(가중치) 갱신값을 위한 가중치 변화량 delta Weight 를 구한다.
     * @param alpha 학습률
     * @param errorE 오차
     * @param inputX 입력값
     * @return
     */
    private Double getDeltaWeight(Double alpha, Double errorE, Double inputX) {
        return alpha * errorE * inputX;
    }
}
