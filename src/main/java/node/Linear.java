package node;

/**
 * 선형 함수 노드
 * Created by johngrib on 2017. 4. 14..
 */
public class Linear extends Node {

    @Override
    public Double phi(final Double v) {
        return v + bias;
    }
}
