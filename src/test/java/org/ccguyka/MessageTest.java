package org.ccguyka;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageTest {

    @Test
    public void verifyMessage() {
        Message message = new Message("destination", "message");

        assertThat(message.getDestination()).isEqualTo("destination");
        assertThat(message.getMessageText()).isEqualTo("message");
    }
}
