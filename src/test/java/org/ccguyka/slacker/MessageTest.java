package org.ccguyka.slacker;


import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.assertj.core.api.Assertions.assertThat;

public class MessageTest {

    @Test
    public void verifyMessage() {
        Message message = new Message("destination", "message");

        assertThat(message.getDestination()).isEqualTo("destination");
        assertThat(message.getMessageText()).isEqualTo("message");
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Message.class).usingGetClass().suppress(NONFINAL_FIELDS).verify();
    }
}
