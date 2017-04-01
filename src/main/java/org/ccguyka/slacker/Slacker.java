package org.ccguyka.slacker;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;


class Slacker {

    private final Message message;

    public Slacker(Message message) {
        this.message = message;
    }

    public void send() {
        SlackApi api = new SlackApi(message.getDestination());
        api.call(createSlackMessageFor(message));
    }

    private SlackMessage createSlackMessageFor(Message message) {
        SlackMessage slackMessage = new SlackMessage("");
        slackMessage.addAttachments(createSlackAttachment(message));
        return slackMessage;
    }

    private SlackAttachment createSlackAttachment(Message message) {
        SlackAttachment attachment = new SlackAttachment();
        attachment.setFallback(message.getMessageText());
        attachment.setColor(message.getColor());
        attachment.setText(message.getMessageText());
        attachment.setAuthorName(message.getAuthor());
        attachment.addMarkdownAttribute("text");
        attachment.setTitle(message.getTitle());
        attachment.setTitleLink(message.getTitleLink());
        return attachment;
    }
}
