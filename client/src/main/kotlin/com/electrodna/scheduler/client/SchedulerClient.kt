package com.electrodna.scheduler.client

import org.springframework.http.client.reactive.ClientHttpConnector
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.*
import org.springframework.web.util.DefaultUriBuilderFactory
import org.springframework.web.util.UriBuilderFactory
import reactor.netty.http.client.HttpClient
import java.net.URI
import java.util.*

class SchedulerClient(val baseUrl: String) {

    val uriBuilderFactory: UriBuilderFactory = DefaultUriBuilderFactory(baseUrl)

    fun fetch(id: UUID) {

        val httpClient: HttpClient = HttpClient.create();

        val clientHttpConnector: ClientHttpConnector = ReactorClientHttpConnector(httpClient);

        val webClient: WebClient = builder().clientConnector(clientHttpConnector).build();

        val uri: URI = uriBuilderFactory.builder().build();

        val uriSpec: RequestHeadersUriSpec<*> = webClient.get();

        uriSpec.uri(uri).retrieve()

    }
}