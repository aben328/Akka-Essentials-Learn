package akka.first.app.mapreduce.messages;

import java.util.List;

/**
 * Created by Ben on 2016/8/19.
 */
public final class MapData {

    //final类型没有setter...
    private final List<WordCount> dataList;

    public List<WordCount> getDataList() {
        return dataList;
    }

    public MapData(List<WordCount> dataList) {
        this.dataList = dataList;
    }
}
