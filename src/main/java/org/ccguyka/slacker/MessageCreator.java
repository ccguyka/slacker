package org.ccguyka.slacker;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

class MessageCreator {

    private final Options options;

    public MessageCreator() {
        options = new Options();

        options.addRequiredOption("hk", "hook", true, "Slack to hook");
        options.addRequiredOption("m", "message", true, "Message to be sent");
        options.addOption("a", "author", true, "Author of the message");
        options.addOption("c", "color", true, "Color of the message, default: 'warning'");
        options.addOption("t", "title", true, "Title of the message");
        options.addOption("tl", "titlelink", true, "The link where title should refer to");
        options.addOption("h", "help", false, "print this message");
    }

    public Message from(String[] args) {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch( ParseException exp ) {
            return null;
        }

        if (cmd.hasOption("help")) {
            return null;
        }

        return createMessage(cmd);
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

    public void printHelp() {
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "java -jar slacker.jar", options );
    }
}
