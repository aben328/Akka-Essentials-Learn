package akka.first.app.mapreduce.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.first.app.mapreduce.messages.MapData;
import akka.first.app.mapreduce.messages.ReducedData;
import akka.first.app.mapreduce.messages.WordCount;

import java.util.HashMap;
import java.util.List;

/**
 * Map actor will send the  MapData message to the Master actor, who passes it to the
 * Reduce actor. The Reduce actor will go through the list of words and reduce for
 * duplicate words, and accordingly increase the number of instances counted for such
 * words. The reduced list is then sent back to the Master actor
 * Created by Ben on 2016/8/19.
 */
public class ReduceActor extends UntypedActor{

    private ActorRef aggregateActor = null;

    public ReduceActor(ActorRef aggregateActor) {
        this.aggregateActor = aggregateActor;
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o instanceof MapData){
            MapData mapData = (MapData) o;

            //reduce the incoming data.
            ReducedData reducedData = reduce(mapData.getDataList());

            //forward the result to aggregate actor...
            aggregateActor.tell(reducedData,getSelf());
        }else
            unhandled(o);
    }

    private ReducedData reduce(List<WordCount> dataList) {
        HashMap<String,Integer> reduceMap = new HashMap<>();
        for(WordCount wordCount : dataList){
            if(reduceMap.containsKey(wordCount.getWord())){
                Integer value = reduceMap.get(wordCount.getWord());
                value++;
                reduceMap.put(wordCount.getWord(),value);
            } else {
                reduceMap.put(wordCount.getWord(),Integer.valueOf(1));
            }
        }
        
        return new ReducedData(reduceMap);
    }
}
