package org.ccguyka.slacker;


import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Message {

    private final String destination;
    private final String messageText;
    private String color = "warning";
    private String author;
    private String title;
    private String titleLink;

    public Message(String destination, String messageText) {
        this.destination = destination;
        this.messageText = messageText;
    }

    public String getDestination() {
        return destination;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitleLink(String titleLink) {
        this.titleLink = titleLink;
    }

    public String getTitleLink() {
        return titleLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        return Objects.equal(destination, message.destination) &&
                Objects.equal(messageText, message.messageText) &&
                Objects.equal(color, message.color) &&
                Objects.equal(author, message.author) &&
                Objects.equal(title, message.title) &&
                Objects.equal(titleLink, message.titleLink);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(destination, messageText, color, author, title, titleLink);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("destination", destination)
                .add("messageText", messageText)
                .add("color", color)
                .add("author", author)
                .add("title", title)
                .add("titleLink", titleLink)
                .toString();
    }
}
