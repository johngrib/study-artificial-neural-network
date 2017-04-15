package rule;

import data.LearningDataSet;
import node.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by johngrib on 2017. 4. 14..
 */
public abstract class Rule {

    final double alpha; // 학습률
    final Node node;    // 노드 함수 phi
    final double expectErrorLevel;  // 원하는 수준의 오차 (이 값에 도달하면 학습을 멈춘다)
    final List<String> report = new LinkedList<>();
    final Integer epochLimit;

    Double lastError = Double.MAX_VALUE;
    Integer epoch = 0;

    public Rule(double alpha, Node node, double expectErrorLevel) {
        this.alpha = alpha;
        this.node = node;
        this.expectErrorLevel = expectErrorLevel;
        this.epochLimit = Integer.MAX_VALUE;
    }

    public Rule(double alpha, Node node, double expectErrorLevel, int epochLimit) {
        this.alpha = alpha;
        this.node = node;
        this.expectErrorLevel = expectErrorLevel;
        this.epochLimit = epochLimit;
    }

    /**
     * 학습을 수행한다.
     * @param dataSet 학습 데이터
     * @return 학습을 마친 Node
     */
    abstract Node run(LearningDataSet dataSet);

    Double getLastError() {
        return lastError;
    }

    Double getLastErrorAbs() {
        return Math.abs(lastError);
    }

    public String getReportString() {
        return report.stream().reduce((x, y) -> x + "\n" + y).get();
    }

    @Override
    public String toString() {
        return "DeltaSGD{" +
                "weight=" + node.getWeightValueList() +
                ", alpha=" + alpha +
                ", lastError=" + lastError +
                ", epoch=" + epoch +
                '}';
    }
}
