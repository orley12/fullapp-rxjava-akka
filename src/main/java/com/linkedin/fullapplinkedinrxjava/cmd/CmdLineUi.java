package com.linkedin.fullapplinkedinrxjava.cmd;

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

        System.out.println("\n" + "Linkedin Learning Reactive Programming With Java 8" + "\n");

        Observable
                .interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
                .map(tickLong -> coinBaseService.getCryptoPrice("BTC-USD"))
                .subscribe(new ConsolePrintObserver());

        Observable
                .interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
                .map(tickLong -> coinBaseService.getCryptoPrice("ETH-USD"))
                .subscribe(new ConsolePrintObserver());

//        coinBaseService.getCryptoPrice("BTC-USD")
//                .subscribe(coinBaseResponse ->  System.out.println(coinBaseResponse.getData().getAmount()));
    }
}
