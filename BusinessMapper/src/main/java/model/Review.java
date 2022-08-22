package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Review {

  protected int reviewId;
  protected BigDecimal rating;
  protected String content;
  protected Timestamp created;
  protected User user;
  protected YelpBusiness business;

  public Review(int reviewId, BigDecimal rating, String content, Timestamp created, User user,
      YelpBusiness business) {
    this.reviewId = reviewId;
    this.rating = rating;
    this.content = content;
    this.created = created;
    this.user = user;
    this.business = business;
  }

  public Review(int reviewId) {
    this.reviewId = reviewId;
  }

  public Review(BigDecimal rating, String content, Timestamp created, User user,
      YelpBusiness business) {
    this.rating = rating;
    this.content = content;
    this.created = created;
    this.user = user;
    this.business = business;
  }

  public int getReviewId() {
    return reviewId;
  }

  public void setReviewId(int reviewId) {
    this.reviewId = reviewId;
  }

  public BigDecimal getRating() {
    return rating;
  }

  public void setRating(BigDecimal rating) {
    this.rating = rating;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public User getUser() {
    return user;
  }

  public void setSuer(User user) {
    this.user = user;
  }

  public YelpBusiness getBusiness() {
    return business;
  }

  public void setBusiness(YelpBusiness business) {
    this.business = business;
  }


}
