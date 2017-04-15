package data;

import java.util.ArrayList;
import java.util.List;

/**
 * 학습 데이터 셋
 * Created by johngrib on 2017. 4. 14..
 */
public class LearningDataSet {

    final List<LearningData> dataList = new ArrayList<LearningData>();

    public LearningDataSet add(LearningData data) {
        dataList.add(data);
        return this;
    }

    public List<LearningData> list() {
        return dataList;
    }
}
