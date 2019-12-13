package lab6;

import akka.actor.ActorRef;
import org.apache.zookeeper.*;

import java.io.IOException;

public class ZooKeeperService {

    ActorRef configActor;
    ZooKeeper zooKeeper;

    ZooKeeperService(ActorRef configActor) throws IOException {
        this.configActor = configActor;
        zooKeeper = new ZooKeeper("127.0.0.1:2050", 5000, null);
    }
}
