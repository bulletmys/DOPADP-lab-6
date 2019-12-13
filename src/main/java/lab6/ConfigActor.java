package lab6;

import akka.actor.AbstractActor;
import akka.actor.Props;

import java.util.ArrayList;
import java.util.Random;

public class ConfigActor extends AbstractActor {

    String[] servers;
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(RandServer.class, mail -> {
                    sender().tell(rand(), self());
                })
                .match(ArrayList.class, mail -> {
                    servers = (String[]) mail.toArray();
                })
                .build();
    }

    String rand() {
        return servers[new Random().nextInt(servers.length)];
    }

    public static Props props() {
        return Props.create(ConfigActor.class);
    }
}
