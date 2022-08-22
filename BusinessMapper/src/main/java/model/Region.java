package model;

/**
 * Region is a simple, plain old java objects (POJO).
 * <p>
 * Region/RegionDao is the superclass for Administrators/AdministratorsDao and
 * BlogUsers/BlogUsersDao. Our implementation of Region is a concrete class. This allows us to
 * create records in the Region MySQL table without having the associated records in the
 * Administrators or BlogUsers MySQL tables. Alternatively, Region could be an interface or an
 * abstract class, which would force a Region record to be created only if an Administrators or
 * BlogUsers record is created, too.
 */
public class Region {

  protected String ZipCode;
  protected String StateName;
  protected String City;
  protected String CountyName;

  public Region(String ZipCode, String StateName, String City, String CountyName) {
    this.ZipCode = ZipCode;
    this.StateName = StateName;
    this.City = City;
    this.CountyName = CountyName;
  }

  public Region(String ZipCode) {
    this.ZipCode = ZipCode;
  }

  public String getZipCode() {
    return ZipCode;
  }

  public void setZipCode(String ZipCode) {
    this.ZipCode = ZipCode;
  }

  public String getStateName() {
    return StateName;
  }

  public void setStateName(String StateName) {
    this.StateName = StateName;
  }

  public String getCity() {
    return City;
  }

  public void setCity(String City) {
    this.City = City;
  }

  public String getCountyName() {
    return CountyName;
  }

  public void setCountyName(String CountyName) {
    this.CountyName = CountyName;
  }
}
