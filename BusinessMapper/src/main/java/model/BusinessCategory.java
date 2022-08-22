package model;

/**
 * BusinessCategory is a simple, plain old java objects (POJO).
 */
public class BusinessCategory {

  protected Integer businessCategoryId;
  protected String businessId;
  protected Category category;

  public BusinessCategory(Integer businessCategoryId, String businessId, Category category) {
    this.businessCategoryId = businessCategoryId;
    this.businessId = businessId;
    this.category = category;
  }

  public BusinessCategory(String businessId, Category category) {
    this.businessId = businessId;
    this.category = category;
  }

  public Integer getBusinessCategoryId() {
    return businessCategoryId;
  }

  public void setBusinessCategoryId(Integer businessCategoryId) {
    this.businessCategoryId = businessCategoryId;
  }

  public String getBusinessId() {
    return businessId;
  }

  public void setBusinessId(String businessId) {
    this.businessId = businessId;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }


}
