package model;

import java.math.BigDecimal;

public class YelpBusiness {

  protected String BusinessId;
  protected String Name;
  protected String Address;
  protected String ZipCode;
  protected Double Latitude;
  protected Double Longitude;
  protected Double Rating;
  protected Integer ReviewCount;
  protected Boolean isOpen;

  public YelpBusiness() {
  }

  public YelpBusiness(String businessId, String name, String address, String zipCode,
      Double latitude, Double longitude, Double rating, Integer reviewCount, Boolean isOpen) {
    BusinessId = businessId;
    Name = name;
    Address = address;
    ZipCode = zipCode;
    Latitude = latitude;
    Longitude = longitude;
    Rating = rating;
    ReviewCount = reviewCount;
    this.isOpen = isOpen;
  }

  public String getBusinessId() {
    return BusinessId;
  }

  public void setBusinessId(String businessId) {
    BusinessId = businessId;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getAddress() {
    return Address;
  }

  public void setAddress(String address) {
    Address = address;
  }

  public String getZipCode() {
    return ZipCode;
  }

  public void setZipCode(String zipCode) {
    ZipCode = zipCode;
  }

  public Double getLatitude() {
    return Latitude;
  }

  public void setLatitude(Double latitude) {
    Latitude = latitude;
  }

  public Double getLongitude() {
    return Longitude;
  }

  public void setLongitude(Double longitude) {
    Longitude = longitude;
  }

  public Double getRating() {
    return Rating;
  }

  public void setRating(Double rating) {
    Rating = rating;
  }

  public Integer getReviewCount() {
    return ReviewCount;
  }

  public void setReviewCount(Integer reviewCount) {
    ReviewCount = reviewCount;
  }

  public Boolean getOpen() {
    return isOpen;
  }

  public void setOpen(Boolean open) {
    isOpen = open;
  }

  @Override
  public String toString() {
    return "YelpBusiness{" +
        "BusinessId='" + BusinessId + '\'' +
        ", Name='" + Name + '\'' +
        ", Address='" + Address + '\'' +
        ", ZipCode='" + ZipCode + '\'' +
        ", Latitude=" + Latitude +
        ", Longitude=" + Longitude +
        ", Rating=" + Rating +
        ", ReviewCount=" + ReviewCount +
        ", isOpen=" + isOpen +
        '}';
  }
}
