package model;

/**
 * Category is a simple, plain old java objects (POJO).
 */
public class Category {

  protected Integer categoryId;
  protected String type;

  public Category(Integer categoryId, String type) {
    this.categoryId = categoryId;
    this.type = type;
  }

  public Category(String type) {
    this.type = type;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
