package lab6;

import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletionStage;

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
                        int counter = Integer.parseInt(count);
                        if (counter == 0) {
                            return completeWithFuture()
                        }

                        return completeWithFuture()
                    }))
        })
    }

    CompletionStage<HttpResponse> makeRequest(String url) {
        return http.singleRequest(HttpRequest.create())
    }


}
