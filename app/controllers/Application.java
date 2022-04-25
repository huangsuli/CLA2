package controllers;

import client.KafkaProducerClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;


public class Application extends Controller {

    private HttpExecutionContext httpExecutionContext;

    KafkaProducerClient kafkaProducerClient = new KafkaProducerClient();

    @Inject
    public Application(HttpExecutionContext ec) {
        this.httpExecutionContext = ec;
    }

    public static Result increaseCounter() {
        return calculateResponse()
                .thenApplyAsync(
                        answer -> {
                            return ok("answer was " + answer).flash("info", "Response updated!");
                        },
                        httpExecutionContext.current());
    }

    private CompletionStage<Void> publishToJMS() {
        return CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                // Simulate a long-running Job
                try {
                    kafkaProducerClient.publish();
                } catch (Exception e) {
                    throw e;
                }
                System.out.println("I'll run in a separate thread than the main thread.");
            }
        });
    }
}
