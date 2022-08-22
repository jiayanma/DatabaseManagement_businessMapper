package model;

public class User {

  protected String UserName;
  protected String Password;
  protected String FirstName;
  protected String LastName;
  protected String Email;
  protected String PhoneNumber;

  public User() {
  }

  public User(String userName, String password, String firstName, String lastName,
      String email, String phoneNumber) {
    UserName = userName;
    Password = password;
    FirstName = firstName;
    LastName = lastName;
    Email = email;
    PhoneNumber = phoneNumber;
  }

  public String getUserName() {
    return UserName;
  }

  public void setUserName(String userName) {
    UserName = userName;
  }

  public String getPassword() {
    return Password;
  }

  public void setPassword(String password) {
    Password = password;
  }

  public String getFirstName() {
    return FirstName;
  }

  public void setFirstName(String firstName) {
    FirstName = firstName;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String lastName) {
    LastName = lastName;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String email) {
    Email = email;
  }

  public String getPhoneNumber() {
    return PhoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    PhoneNumber = phoneNumber;
  }

  @Override
  public String toString() {
    return "User{" +
        "UserName='" + UserName + '\'' +
        ", Password='" + Password + '\'' +
        ", FirstName='" + FirstName + '\'' +
        ", LastName='" + LastName + '\'' +
        ", Email='" + Email + '\'' +
        ", PhoneNumber='" + PhoneNumber + '\'' +
        '}';
  }
}
