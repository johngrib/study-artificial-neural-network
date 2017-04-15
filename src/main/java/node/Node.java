package node;

import util.MatrixUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 노드
 * Created by johngrib on 2017. 4. 14..
 */
public abstract class Node {

    protected List<Double> weightValueList = new ArrayList<Double>();
    protected List<Double> inputValueList = new ArrayList<Double>();

    public Double calc() {
        final Double v = MatrixUtils.simpleMultiply(inputValueList, weightValueList);
        return phi(v);
    }

    abstract public Double phi(final Double v);

    public Double getWeight(int index) {
        return weightValueList.get(index);
    }

    public List<Double> getWeightValueList() {
        return Collections.unmodifiableList(weightValueList);
    }

    public Node setInputValues(List<Double> inputValues) {
        inputValueList = inputValues;
        return this;
    }

    public Node setWeightValues(List<Double> weightValues) {
        this.weightValueList = weightValues;
        return this;
    }


    @Override
    public String toString() {
        return "Node{" +
                "weightValueList=" + weightValueList +
                ", inputValueList=" + inputValueList +
                '}';
    }
}
