package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      // Создание пользователей с машинами
      userService.add(new User("John", "Dirr", "user1@mail.ru", new Car("Tesla", 2022)));
      userService.add(new User("Michel", "Jackson", "user2@mail.ru", new Car("Model2", 2021)));
      userService.add(new User("Ivan", "Rheon", "user3@mail.ru", new Car("Zhiguli", 2020)));

      // Вывод всех пользователей
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car Model = " + user.getCar().getModel());
         System.out.println("Car Series = " + user.getCar().getSeries());
         System.out.println();
      }

      // Поиск пользователя по модели и серии машины
      User foundUser = userService.getUserByCarModelAndSeries("Model2", 2021);
      if (foundUser != null) {
         System.out.println("Найденный пользователь:");
         System.out.println("Id = " + foundUser.getId());
         System.out.println("First Name = " + foundUser.getFirstName());
         System.out.println("Last Name = " + foundUser.getLastName());
         System.out.println("Email = " + foundUser.getEmail());
         System.out.println("Car Model = " + foundUser.getCar().getModel());
         System.out.println("Car Series = " + foundUser.getCar().getSeries());
      } else {
         System.out.println("Пользователь с такой машиной не найден.");
      }

      context.close();
   }
}