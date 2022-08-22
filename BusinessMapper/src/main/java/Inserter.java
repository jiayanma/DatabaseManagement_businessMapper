import dao.FavoriteBusinessDao;
import dao.UserDao;
import java.sql.SQLException;
import java.util.List;
import model.FavoriteBusiness;
import model.User;

public class Inserter {

  public static void main(String[] args) throws SQLException {
    // Dao instance
    UserDao userDao = UserDao.getInstance();
    FavoriteBusinessDao favoriteBusinessDao = FavoriteBusinessDao.getInstance();

//    // INSERT
//    User user1 = new User("Xiaohong", "123456", "Xiaohong", "Chen", "seanchenxh@gmail.com",
//        "1111111111");
//    User user2 = new User("Jack", "654321", "Jack", "Chen", "jackchenj@gmail.com", "2222222222");
//    userDao.create(user1);
//    userDao.create(user2);
//
//    FavoriteBusiness favoriteBusiness1  = new FavoriteBusiness(null, "Xiaohong", "__1uG7MLxWGFIv2fCGPiQQ");
//    favoriteBusinessDao.create(favoriteBusiness1);
//
//    //Read
//    User user = userDao.getUserByUserName("Xiaohong");
//    System.out.println(user);

//    FavoriteBusiness favoriteBusiness = favoriteBusinessDao.getFavoriteBusinessById(1);
//    System.out.println(favoriteBusiness);

    List<FavoriteBusiness> favoriteBusinessList0 = favoriteBusinessDao.getFavoriteBusinessByUserName("Sean");
    for (FavoriteBusiness f : favoriteBusinessList0) {
      System.out.println(f);
    }

//    List<FavoriteBusiness> favoriteBusinessList1 = favoriteBusinessDao.getFavoriteBusinessByBusinessId(
//        "__1uG7MLxWGFIv2fCGPiQQ");
//    for (FavoriteBusiness f : favoriteBusinessList1) {
//      System.out.println(f);
//    }

    //Delete
//    userDao.delete(user1);
//    userDao.delete(user2);
//    favoriteBusinessDao.delete(favoriteBusiness1);

  }
}
