package lab6;

import akka.actor.ActorRef;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZooKeeperService {

    ActorRef configActor;
    ZooKeeper zooKeeper;

    ZooKeeperService(ActorRef configActor) throws IOException {
        this.configActor = configActor;
        zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, null);
    }

    void makeServer(String url) {
        zooKeeper.create("/servers/s", url, 
        )
    }

    void watcher() throws KeeperException, InterruptedException {
        List<String> serverNodes = zooKeeper.getChildren("/servers", watchedEvent -> {
            if (Watcher.Event.EventType.NodeChildrenChanged == watchedEvent.getType()) {
                try {
                    watcher();
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        ArrayList<String> server = new ArrayList<>();
        for (String node : serverNodes) {
            byte[] url = zooKeeper.getData("/servers/" + node, null, null);
            server.add(new String(url));
        }

        configActor.tell(server ,ActorRef.noSender());
    }
}
