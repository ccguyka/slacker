package org.ccguyka;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;
import org.apache.commons.cli.*;


public class Slacker {

    private final Options options;

    public Slacker() {
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
        Slacker slacker = new Slacker();
        slacker.send(args);
    }

    private void send(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch( ParseException exp ) {
            printHelp();

            return;
        }

        if (cmd.hasOption("help")) {
            // automatically generate the help statement
            printHelp();
            return;
        }

        Message message = createMessage(cmd);

        SlackApi api = new SlackApi(message.getDestination());
        api.call(createSlackMessageFor(message));
    }

    private Message createMessage(CommandLine cmd) {
        String messageText = cmd.getOptionValue("message");
        String destination = cmd.getOptionValue("hook");
        Message message = new Message(destination, messageText);
        message.setColor(cmd.getOptionValue("color", "warning"));
        message.setAuthor(cmd.getOptionValue("author", null));
        message.setTitle(cmd.getOptionValue("title", null));
        message.setTitleLink(cmd.getOptionValue("titlelink", null));
        return message;
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

    private void printHelp() {
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "java -jar slacker.jar", options );
    }
}
