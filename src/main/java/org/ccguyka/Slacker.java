package org.ccguyka;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;
import org.apache.commons.cli.*;


public class Slacker {

    private final Options options;
    private final Message message;

    public Slacker(Message message) {
        this.message = message;

        options = new Options();

        options.addRequiredOption("hk", "hook", true, "Slack to hook");
        options.addRequiredOption("m", "message", true, "Message to be sent");
        options.addOption("a", "author", true, "Author of the message");
        options.addOption("c", "color", true, "Color of the message, default: 'warning'");
        options.addOption("t", "title", true, "Title of the message");
        options.addOption("tl", "titlelink", true, "The link where title should refer to");
        options.addOption("h", "help", false, "print this message");
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
