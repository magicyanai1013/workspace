package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "buyerReviews")
public class BuyerReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyerReviewId")
    private Long buyerReviewId;

    @Column(name = "orderId", nullable = false)
    private Long orderId;

    @Column(name = "buyerId", nullable = false)
    private int buyerId;

    @Column(name = "reviewerId", nullable = false)
    private int reviewerId;

    @Column(name = "evaluation", nullable = false)
    private int evaluation;

    @Column(name = "comment", length = 300)
    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "review_date", nullable = false)
    private Date reviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyerId", referencedColumnName = "userId", insertable = false, updatable = false)
    @JsonIgnore
    private UserInfo buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewerId", referencedColumnName = "userId", insertable = false, updatable = false)
    @JsonIgnore
    private UserInfo reviewer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", referencedColumnName = "order_id", insertable = false, updatable = false)
    @JsonIgnore
    private Order order;

    @PrePersist
    public void onCreate() {
        if (reviewDate == null) {
            reviewDate = new Date();
        }
    }

    // Getters and Setters
    public Long getBuyerReviewId() {
        return buyerReviewId;
    }

    public void setBuyerReviewId(Long buyerReviewId) {
        this.buyerReviewId = buyerReviewId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public UserInfo getBuyer() {
        return buyer;
    }

    public void setBuyer(UserInfo buyer) {
        this.buyer = buyer;
    }

    public UserInfo getReviewer() {
        return reviewer;
    }

    public void setReviewer(UserInfo reviewer) {
        this.reviewer = reviewer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}