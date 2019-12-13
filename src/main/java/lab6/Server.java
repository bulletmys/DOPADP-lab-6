package lab6;

import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;

public class Server extends AllDirectives {
    private Http http;
    private int port;
    private ActorRef configActor;

    public Server(Http http, int port, ActorRef configActor) {
        this.http = http;
        this.port = port;
        this.configActor = configActor;
    }

    Route createRoute() {
        return get(() -> {
            parameter("URL", url ->
                    parameter("count", count -> {
                        int count = Integer.parseInt()
                    }))
        })
    }


}
