package akka.first.app.mapreduce.actors;

import akka.actor.UntypedActor;
import akka.first.app.mapreduce.messages.ReducedData;
import akka.first.app.mapreduce.messages.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Aggregate actor receives the reduced data list from the Master actor and aggregates
 * it into one big list. Aggregate actor will maintain a state variable that will hold the
 * list of words and get updated on receipt of the reduced data list message:
 * Created by Ben on 2016/8/19.
 */
public class AggregateActor extends UntypedActor{

    private Map<String,Integer> finalReduceMap = new HashMap<>();

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o instanceof ReducedData){
            ReducedData reducedData = (ReducedData) o;
            aggregateInMemoryReduce(reducedData.getReduceDataList());
        } else  if (o instanceof Result){
            System.out.println(finalReduceMap.toString());
        }
            unhandled(o);
    }

    private void aggregateInMemoryReduce(Map<String, Integer> reduceDataList) {
        Integer count = null;
//        for
    }


}
