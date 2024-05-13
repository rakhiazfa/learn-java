import controllers.TeacherController;
import core.Database;
import core.ServiceContainer;
import models.Menu;
import models.Teacher;
import repositories.TeacherRepository;

import java.util.*;

public class Application {
    public static void registerServices() {
        try {
            Scanner scanner = new Scanner(System.in);
            Database database = new Database();

            ServiceContainer.set("scanner", scanner);
            ServiceContainer.set("database", database);

            ServiceContainer.register(TeacherRepository.class);
            ServiceContainer.register(TeacherController.class);
        } catch (Exception exception) {
            System.out.println("[Error] " + exception.getMessage());
        }
    }

    public static void menu(ArrayList<Menu> menus) {
        Scanner scanner = ServiceContainer.get("scanner");

        System.out.println("Choose Menu\n");

        int number = 1;
        for (Menu menu : menus) {
            System.out.println(number + ". " + menu.getLabel());
            number++;
        }

        int selectedMenu = scanner.nextInt();

        // Clear the buffer
        scanner.nextLine();

        number = 1;
        for (Menu menu : menus) {
            if(selectedMenu == number) {
                menu.select();
            }
            number++;
        }
    }

    public static void main(String[] args) {
        try {
            registerServices();

            Scanner scanner = ServiceContainer.get("scanner");
            ArrayList<Menu> menus = new ArrayList<>();

            menus.add(new Menu("Teacher", () -> {
                TeacherController controller = ServiceContainer.get(TeacherController.class);
                ArrayList<Menu> teacherMenus = new ArrayList<>();

                teacherMenus.add(new Menu("Get Teachers", () -> {
                    controller.getAll();
                    menu(teacherMenus);
                }));
                teacherMenus.add(new Menu("Create Teacher", () -> {
                    System.out.print("Name\t: ");
                    String name = scanner.nextLine();

                    System.out.print("Subject\t: ");
                    String subject = scanner.nextLine();

                    System.out.print("Email\t: ");
                    String email = scanner.nextLine();

                    Teacher teacher = new Teacher(null, name, subject, email);

                    controller.create(teacher);
                    menu(teacherMenus);
                }));
                teacherMenus.add(new Menu("Get Teacher By Id", () -> {
                    System.out.print("ID\t\t: ");
                    int id = scanner.nextInt();

                    // Clear the buffer
                    scanner.nextLine();

                    controller.getById(id);
                    menu(teacherMenus);
                }));
                teacherMenus.add(new Menu("Update Teacher", () -> {
                    System.out.print("ID\t: ");
                    Integer id = scanner.nextInt();

                    // Clear the buffer
                    scanner.nextLine();

                    System.out.print("Name\t: ");
                    String name = scanner.nextLine();

                    System.out.print("Subject\t: ");
                    String subject = scanner.nextLine();

                    System.out.print("Email\t: ");
                    String email = scanner.nextLine();

                    Teacher teacher = new Teacher(id, name, subject, email);

                    controller.update(teacher);
                    menu(teacherMenus);
                }));
                teacherMenus.add(new Menu("Delete Teacher", () -> {
                    System.out.print("ID\t\t: ");
                    int id = scanner.nextInt();

                    controller.delete(id);
                    menu(teacherMenus);
                }));
                teacherMenus.add(new Menu("Back", () -> {
                    menu(menus);
                }));

                menu(teacherMenus);
            }));
            menus.add(new Menu("Exit", () -> {
                System.exit(0);
            }));

            menu(menus);
        } catch (Exception exception) {
            System.out.println("[Error] " + exception.getMessage());
        }
    }
}