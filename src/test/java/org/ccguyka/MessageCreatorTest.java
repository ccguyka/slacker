package org.ccguyka;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageCreatorTest {

    private MessageCreator messageCreator = new MessageCreator();

    @Test
    public void verifyNoMessageIsCreatedIfMandatoryParameterAreMissing() {
        Message message = messageCreator.from(new String[0]);

        assertThat(message).isNull();
    }

    @Test
    public void verifyNoMessageIsCreatedIfHelpParameterIsAvailable() {
        Message message = messageCreator.from(new String[] {"-h" , "-hk",  "foo", "-m" , "message"});

        assertThat(message).isNull();
    }

    @Test
    public void verifyMininmalMessageIsCreated() {
        Message message = messageCreator.from(new String[] {"-hk",  "foo", "-m" , "message"});

        assertThat(message).isEqualTo(new Message("foo", "message"));
    }
}
