package lab6;

import akka.actor.ActorRef;
import org.apache.zookeeper.*;

public class ZooKeeper {

    ActorRef configActor;
    ZooKeeper zooKeeper;

    ZooKeeper(ActorRef configActor) {
        this.configActor = configActor;
        zooKeeper = new ZooKeeper("127")
    }
}
