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

        // Вывод всех пользователей с forEach()
        List<User> users = userService.listUsers();
        users.forEach(System.out::println);

        // Поиск пользователя по модели и серии машины
        User foundUser = userService.getUserByCarModelAndSeries("Model2", 2021);
        if (foundUser != null) {
            System.out.println("Найденный пользователь:");
            System.out.println(foundUser);
        } else {
            System.out.println("Пользователь с такой машиной не найден.");
        }

        context.close();
    }

}