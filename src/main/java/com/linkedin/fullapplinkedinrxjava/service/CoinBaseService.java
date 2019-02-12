package com.linkedin.fullapplinkedinrxjava.service;

import com.linkedin.fullapplinkedinrxjava.model.CoinBaseResponse;
import reactor.core.publisher.Mono;

public interface CoinBaseService {

    Mono<CoinBaseResponse> getCryptoPrice(String priceName);
}
