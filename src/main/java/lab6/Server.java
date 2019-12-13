package lab6;

import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import akka.http.javadsl.server.AllDirectives;

public class Server extends AllDirectives {
    private Http http;
    private int port;
    private ActorRef configActor;

    public Server(Http http, int port, ActorRef configActor) {
        this.http = http;
        this.port = port;
        this.configActor = configActor;
    }

    public


}
