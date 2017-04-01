package org.ccguyka;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;
import org.apache.commons.cli.*;


public class Slacker {

    private final Message message;

    public Slacker(Message message) {
        this.message = message;
    }

    public static void main(String[] args) throws Exception {
        MessageCreator messageCreator = new MessageCreator();
        Message message = messageCreator.from(args);
        if (message == null) {
            messageCreator.printHelp();
            return;
        }
        Slacker slacker = new Slacker(message);
        slacker.send();
    }

    private void send() throws ParseException {
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
