/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restful;

import java.io.Serializable;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author c0636081
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name")})
public class Message {



    private int messageId;
    private String title;
    private String content;
    private String author;
    private String sentTime;
    
    public Message() {        
    }

    public Message(int messageId, String title, String content, String author, String sentTime) {
        this.messageId = messageId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.sentTime = sentTime;
    }
    
    public Message(JsonObject json){
        messageId = json.getInt("messageId");
        title = json.getString("title");
        content = json.getString("content");
        author = json.getString("author");
        sentTime = json.getString("sentTime");
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }
    
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("messageId", messageId)
                .add("title", title)
                .add("content", content)
                .add("author", author)
                .add("sentTime", sentTime)
                .build();
    }
}

