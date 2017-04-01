package org.ccguyka.slacker;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class MessageCreatorTest {

    private final MessageCreator messageCreator = new MessageCreator();

    @Test
    public void verifyNoMessageIsCreatedIfMandatoryParameterAreMissing() {
        Message message = messageCreator.from(new String[0]);

        assertThat(message).isNull();
    }

    @Test
    public void verifyNoMessageIsCreatedIfHelpParameterIsAvailable() {
        Message message = messageCreator.from(new String[]{"-h", "-hk", "foo", "-m", "message"});

        assertThat(message).isNull();
    }

    @Test
    public void verifyMinimalMessageIsCreated() {
        Message message = messageCreator.from(new String[]{"-hk", "hook", "-m", "message"});

        assertThat(message).isEqualTo(new Message("hook", "message"));
    }

    @Test
    public void verifyTitleIsSet() {
        Message message = messageCreator
            .from(new String[]{"-hk", "hook", "-m", "message", "-t", "title"});

        assertThat(message.getTitle()).isEqualTo("title");
    }

    @Test
    public void verifyTitleLinkIsSet() {
        Message message = messageCreator
            .from(new String[]{"-hk", "hook", "-m", "message", "-tl", "titlelink"});

        assertThat(message.getTitleLink()).isEqualTo("titlelink");
    }

    @Test
    public void verifyAuthorIsSet() {
        Message message = messageCreator
            .from(new String[]{"-hk", "hook", "-m", "message", "-a", "author"});

        assertThat(message.getAuthor()).isEqualTo("author");
    }

    @Test
    public void verifyColorIsSet() {
        Message message = messageCreator
            .from(new String[]{"-hk", "hook", "-m", "message", "-c", "green"});

        assertThat(message.getColor()).isEqualTo("green");
    }
}
