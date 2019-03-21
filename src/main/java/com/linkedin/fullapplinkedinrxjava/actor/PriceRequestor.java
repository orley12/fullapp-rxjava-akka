package com.linkedin.fullapplinkedinrxjava.actor;

import akka.actor.AbstractActor;
import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.linkedin.fullapplinkedinrxjava.service.CoinBaseService;

public class PriceRequestor extends AbstractActor {

    private ActorRef printerActor;
    private CoinBaseService coinBaseService;

    public PriceRequestor(ActorRef printerActor, CoinBaseService coinBaseService) {
        this.printerActor = printerActor;
        this.coinBaseService = coinBaseService;
    }

    public static Props props(ActorRef printerActor, CoinBaseService coinBaseService){
        return Props.create(PriceRequestor.class, () -> new PriceRequestor(printerActor, coinBaseService));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(GetCryptoPrice.class, getCryptoPrice -> printerActor.tell(new Printer
                .CryptoPrice(coinBaseService
                .getCryptoPrice(getCryptoPrice.price)),getSelf())).build();
    }

    public static class GetCryptoPrice{

        private String price;

        public GetCryptoPrice(String price) {
            this.price = price;
        }
    }

}
