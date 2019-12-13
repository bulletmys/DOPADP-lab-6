package lab6;

import akka.actor.ActorRef;

public class ZooKeeper {

    ActorRef configActor;
    ZooKeeper zooKeeper;

    ZooKeeper(ActorRef configActor) {
        this.configActor = configActor;
        zooKeeper = createZooKeeper();
    }
}
