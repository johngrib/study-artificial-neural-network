package node;

/**
 * Sigmoid 함수를 사용하는 노드
 * Created by johngrib on 2017. 4. 14..
 */
public class Sigmoid extends Node {

    @Override
    public Double phi(Double v) {

        final Double e_x = Math.pow(Math.E, (-1 * v));

        return (1 / (1 + e_x));
    }

}
