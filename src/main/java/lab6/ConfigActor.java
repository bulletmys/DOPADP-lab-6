package lab6;

import akka.actor.AbstractActor;
import akka.actor.Props;

import java.util.ArrayList;

public class ConfigActor extends AbstractActor {

    ArrayList<>
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(RandServer.class, mail -> {
                    sender().tell(rand(), self());
                })
                .match(ArrayList.class, mail -> {

                })
                .build();
    }

    String rand() {

    }

    public static Props props() {
        return Props.create(ConfigActor.class);
    }
}
