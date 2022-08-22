package model;

public class FavoriteBusiness {

  private Integer FavoriteId;
  private String UserName;
  private String BusinessId;

  public FavoriteBusiness() {
  }

  public FavoriteBusiness(Integer favoriteId, String userName, String businessId) {
    FavoriteId = favoriteId;
    UserName = userName;
    BusinessId = businessId;
  }

  public Integer getFavoriteId() {
    return FavoriteId;
  }

  public void setFavoriteId(Integer favoriteId) {
    FavoriteId = favoriteId;
  }

  public String getBusinessId() {
    return BusinessId;
  }

  public void setBusinessId(String businessId) {
    BusinessId = businessId;
  }

  public String getUserName() {
    return UserName;
  }

  public void setUserName(String userName) {
    UserName = userName;
  }

  @Override
  public String toString() {
    return "FavoriteBusiness{" +
        "FavoriteId=" + FavoriteId +
        ", BusinessId='" + BusinessId + '\'' +
        ", UserName='" + UserName + '\'' +
        '}';
  }
}
