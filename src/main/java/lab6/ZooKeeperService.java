package lab6;

import akka.actor.ActorRef;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.ArrayList;

public class ZooKeeperService {

    ActorRef configActor;
    ZooKeeper zooKeeper;

    ZooKeeperService(ActorRef configActor) throws IOException {
        this.configActor = configActor;
        zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, null);
    }

    void watcher() {
        ArrayList<String> servers = zooKeeper.getChildren("/servers", watchedEvent -> {
            
        })
    }
}
