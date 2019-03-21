package com.linkedin.fullapplinkedinrxjava.actor;

import akka.actor.AbstractActorWithTimers;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.time.Duration;

public class Poller extends AbstractActorWithTimers {

    private static final Object TICK_KEY ="TickKey";
    private final ActorRef requestorActor;
    private final String cryptoName;

    public Poller(ActorRef requestorActor, String cryptoName) {
        this.requestorActor = requestorActor;
        this.cryptoName = cryptoName;
        getTimers().startSingleTimer(TICK_KEY, new FirstTick(),Duration.ofMillis(3000));
    }


    /**
     * the purpose of the Props is to describe how to construct this Actor
     * @param cryptoName crypto currency exchange we want
     * @param requestorActor contains information (sender, message, methods) about the Actor calling this actor
     * @return
     */
    public static Props props(String cryptoName, ActorRef requestorActor){
        return Props.create(Poller.class, () -> new Poller(requestorActor, cryptoName));
    }


    /**
     * this method is called when a new instance of the Poller Actor is created
     * @return
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(FirstTick.class, message ->{
           getTimers().startPeriodicTimer(TICK_KEY, new Tick(),Duration.ofSeconds(3));
        }).match(Tick.class, message -> requestorActor
                .tell(new PriceRequestor
                        .GetCryptoPrice(cryptoName),getSelf())).build();
    }


    /**
     * this represent the first tick(the first time this actor calls other actor to poll data) the message to be sent
     */
    private static final class FirstTick{

    }

    /**
     * this represent the subsequent tick after the first
     * (subsequent calls to other actor to poll data) the message to be sent
     */
    private static final class Tick{

    }
}
