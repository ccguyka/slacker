package org.ccguyka.slacker;


import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.ContainsPattern;
import org.junit.Rule;
import org.junit.Test;

public class SlackerTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(9999));

    @Test
    public void verifyMessageIsSent() {
        stubFor(post(urlEqualTo("/webhook"))
                .willReturn(aResponse()
                        .withBody("ok!")));


        Slacker slacker = new Slacker(new Message("http://localhost:9999/webhook", "message"));

        slacker.send();

        verify(postRequestedFor(urlEqualTo("/webhook"))
                .withRequestBody(new ContainsPattern("message")));
    }

    @Test
    public void verifyMessageIsSentInMainMethod() throws Exception {
        stubFor(post(urlEqualTo("/webhook"))
                .willReturn(aResponse()
                        .withBody("ok!")));

        Slacker.main(new String[]{"-hk", "http://localhost:9999/webhook", "-m", "message"});

        verify(postRequestedFor(urlEqualTo("/webhook"))
                .withRequestBody(new ContainsPattern("message")));
    }

    @Test
    public void verifyMessageIsNotSentIfMandatoryParameterIsMissing() throws Exception {
        stubFor(post(urlEqualTo("/webhook"))
                .willReturn(aResponse()
                        .withBody("ok!")));

        Slacker.main(new String[0]);

        verify(0, postRequestedFor(urlEqualTo("/webhook"))
                .withRequestBody(new ContainsPattern("message")));
    }

    @Test
    public void verifyMessageIsNotSentIfHelpParameterIsSpecified() throws Exception {
        stubFor(post(urlEqualTo("/webhook"))
                .willReturn(aResponse()
                        .withBody("ok!")));

        Slacker.main(new String[]{"-hk", "http://localhost:9999/webhook", "-m", "message", "-h"});

        verify(0, postRequestedFor(urlEqualTo("/webhook"))
                .withRequestBody(new ContainsPattern("message")));
    }
}
