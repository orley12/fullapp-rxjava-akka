package com.linkedin.fullapplinkedinrxjava.cmd;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.linkedin.fullapplinkedinrxjava.actor.Poller;
import com.linkedin.fullapplinkedinrxjava.actor.PriceRequestor;
import com.linkedin.fullapplinkedinrxjava.actor.Printer;
import com.linkedin.fullapplinkedinrxjava.model.CoinBaseResponse;
import com.linkedin.fullapplinkedinrxjava.observer.ConsolePrintObserver;
import com.linkedin.fullapplinkedinrxjava.service.CoinBaseService;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CmdLineUi implements CommandLineRunner {

    @Autowired
    private CoinBaseService coinBaseService;
    @Override
    public void run(String... args) throws Exception {
        final ActorSystem system = ActorSystem.create("helloAkka");

        System.out.println("\n" + "Linkedin Learning Reactive Programming With Java 8 Using Akka" + "\n");

        final ActorRef printer = system.actorOf(Printer.props(), "printerActor");

        final ActorRef priceRequestor = system.actorOf(PriceRequestor.props(printer, coinBaseService), "priceRequestor");

        final ActorRef poller = system.actorOf(Poller.props("BTC-USD", priceRequestor), "poller");


    }
}
