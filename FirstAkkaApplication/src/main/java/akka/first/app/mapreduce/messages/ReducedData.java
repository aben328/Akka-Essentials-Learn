package akka.first.app.mapreduce.messages;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Ben on 2016/8/19.
 */
public class ReducedData {

    private Map<String,Integer> reduceDataList;

    public Map<String, Integer> getReduceDataList() {
        return reduceDataList;
    }

    public ReducedData(Map<String, Integer> reduceDataList) {
        this.reduceDataList = Collections.unmodifiableMap(reduceDataList);
    }
}
