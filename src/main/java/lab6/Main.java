package lab6;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.io.IOException;

import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;

import java.util.concurrent.CompletionStage;

public class Main {
    private static final String SERVER_ONLINE_MSG = "Server online at http://localhost/\nPress RETURN to stop...";
    //    private static final int PORT = 8080;
    private static final String HOST = "localhost";
    private static final String ACTOR_SYSTEM_NAME = "routes";

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);

        ActorSystem system = ActorSystem.create(ACTOR_SYSTEM_NAME);
        ActorRef configActor = system.actorOf(ConfigActor.props());
        final Http http = Http.get(system);
        final ActorMaterializer materializer =
                ActorMaterializer.create(system);
        Server server = new Server(http, port, configActor);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = server.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost(HOST, port),
                materializer
        );
        System.out.println(SERVER_ONLINE_MSG);
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate()); 
    }
}