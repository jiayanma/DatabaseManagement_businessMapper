package model;

public class Recommendation {

  protected int recommendationId;
  protected YelpBusiness business;
  protected User user;

  public Recommendation(int recommendationId, YelpBusiness business, User user) {
    this.recommendationId = recommendationId;
    this.business = business;
    this.user = user;
  }

  public Recommendation(int recommendationId) {
    this.recommendationId = recommendationId;
  }

  public Recommendation(YelpBusiness business, User user) {
    this.business = business;
    this.user = user;
  }

  public int getRecommendationId() {
    return recommendationId;
  }

  public void setRecommendationId(int recommendationId) {
    this.recommendationId = recommendationId;
  }

  public YelpBusiness getBusiness() {
    return business;
  }

  public void setBusiness(YelpBusiness business) {
    this.business = business;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }


}
