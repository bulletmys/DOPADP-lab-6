package lab6;

import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.http.javadsl.model.Uri;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import jdk.internal.vm.compiler.collections.Pair;

import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.regex.Pattern;

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
        return get(() ->
                parameter("URL", url ->
                        parameter("count", count -> {
                            int counter = Integer.parseInt(count);
                            if (counter == 0) {
                                return completeWithFuture(makeRequest(url));
                            }
                            return completeWithFuture(getRandReq(url, counter));
                        }))
        );
    }

    CompletionStage<HttpResponse> makeRequest(String url) {
        return http.singleRequest(HttpRequest.create(url));
    }

    CompletionStage<HttpResponse> getRandReq(String url, int count) {
        return Patterns.ask(configActor, new RandServer(), Duration.ofMillis(5000))
                .thenCompose(URL -> makeRequest(Uri.create(URL).query(
                        Query.create(
                                Pair.create("url", url),
                                Pair.create("count", count - 1)
                        ))))
    }


}
