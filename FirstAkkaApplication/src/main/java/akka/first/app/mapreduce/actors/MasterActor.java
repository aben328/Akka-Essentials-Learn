package akka.first.app.mapreduce.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;


/**
 * Created by liubin on 16/8/21.
 */
public class MasterActor extends UntypedActor{

    private ActorRef aggregateActor = getContext().actorOf(
            new Props(AggregateActor.class), "aggregate");

    private ActorRef reduceActor = getContext().actorOf(
            new Props(new UntypedActorFactory() {
                public UntypedActor create() {
                    return new ReduceActor(aggregateActor);
                }
            }), "reduce");

    private ActorRef mapActor = getContext().actorOf(
            new Props(new UntypedActorFactory() {
                public UntypedActor create() {
                    return new MapActor(reduceActor);
                }
            }), "map");

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            mapActor.tell(message);
        } else if (message instanceof Result) {
            aggregateActor.tell(message);
        } else
            unhandled(message);
    }
}
