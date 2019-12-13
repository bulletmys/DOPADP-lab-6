package lab6;

import akka.actor.ActorRef;

public class ZooKeeper {

    ActorRef configActor;

    ZooKeeper(ActorRef configActor) {
        this.configActor = configActor;
    }
}
