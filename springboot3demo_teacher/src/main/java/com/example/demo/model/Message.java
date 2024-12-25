package com.example.demo.model;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MessageId")
    private Integer messageId;
    
    @Column(name = "MessageSort", nullable = false)
    private String messageSort;

    @Column(name = "MessageTitle", nullable = false)
    private String messageTitle;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    @Column(name = "MessageDate")
    @Temporal(TemporalType.DATE)
    private Date messageDate;
    
    @Column(name = "MessageContent")
    private String messageContent;  
    
    @Column(name = "MessageuserId")
    private Integer messageUserId;  
    
    
    // Getters and Setters
    public Integer getMessageId() {
        return messageId;
    }
    
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }
    
    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public Date getMessageDate() {
        return messageDate;
    }
    
    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageContent() {
        return messageContent;
    }
    
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
    
    public Integer getMessageUserId() {
        return messageUserId;
    }
    
    public void setMessageUserId(Integer messageUserId) {
        this.messageUserId = messageUserId;
    }
    
    public String getMessageSort() {
        return messageSort;
    }
    
    public void setMessageSort(String messageSort) {
        this.messageSort = messageSort;
    }
}