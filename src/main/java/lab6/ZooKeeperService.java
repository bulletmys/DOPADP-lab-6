package lab6;

import akka.actor.ActorRef;
import org.apache.zookeeper.*;

public class ZooKeeperService {

    ActorRef configActor;
    ZooKeeper zooKeeper;

    ZooKeeperService(ActorRef configActor) {
        this.configActor = configActor;
        zooKeeper = new ZooKeeper("127.0.0.1:")
    }
}
