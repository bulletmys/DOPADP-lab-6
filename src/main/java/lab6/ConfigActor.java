package lab6;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class ConfigActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return 
    }

    public static Props props() {
        return Props.create(ConfigActor.class);
    }
}
