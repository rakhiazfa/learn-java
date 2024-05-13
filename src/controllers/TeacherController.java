package controllers;

import core.ServiceContainer;
import dnl.utils.text.table.TextTable;
import models.Teacher;
import repositories.TeacherRepository;

import java.util.ArrayList;

public class TeacherController {
    private TeacherRepository teacherRepository;

    public TeacherController() {
        this.teacherRepository = ServiceContainer.get(TeacherRepository.class);
    }

    public void getAll() {
        try {
            ArrayList<Teacher> teachers = this.teacherRepository.getAll();
            Object[][] data = new Object[teachers.size()][4];

            int index = 0;
            for (Teacher teacher : this.teacherRepository.getAll()) {
                data[index][0] = teacher.id();
                data[index][1] = teacher.name();
                data[index][2] = teacher.subject();
                data[index][3] = teacher.email();

                index++;
            }

            String[] columns = {"ID", "Name", "Subject", "Email"};
            TextTable table = new TextTable(columns, data);

            table.printTable();
            System.out.println();
        } catch (Exception exception) {
            System.out.println("[Error] " + exception.getMessage());
        }
    }

    public void create(Teacher teacher) {
        try {
            boolean isSuccess = this.teacherRepository.create(teacher);

            if(isSuccess) {
                System.out.println("\nSuccessfully created a new teacher\n");
            } else {
                System.out.println("\nFailed to create teacher\n");
            }
        } catch (Exception exception) {
            System.out.println("[Error] " + exception.getMessage());
        }
    }

    public void getById(int id) {
        try {
            Teacher teacher = this.teacherRepository.getById(id);

            if(teacher != null) {
                System.out.println("\nID\t\t: " + teacher.id());
                System.out.println("Name\t: " + teacher.name());
                System.out.println("Subject\t: " + teacher.subject());
                System.out.println("Email\t: " + teacher.email() + "\n");
            } else {
                System.out.println("\nTeacher not found\n");
            }
        } catch (Exception exception) {
            System.out.println("[Error] " + exception.getMessage());
        }
    }

    public void update(Teacher teacher) {
        try {
            boolean isSuccess = this.teacherRepository.update(teacher);

            if(isSuccess) {
                System.out.println("\nSuccessfully updated teacher\n");
            } else {
                System.out.println("\nFailed to update teacher\n");
            }
        } catch (Exception exception) {
            System.out.println("[Error] " + exception.getMessage());
        }
    }

    public void delete(int id) {
        try {
            boolean isSuccess = this.teacherRepository.delete(id);

            if(isSuccess) {
                System.out.println("\nSuccessfully delete teacher\n");
            } else {
                System.out.println("\nFailed to delete teacher\n");
            }
        } catch (Exception exception) {
            System.out.println("[Error] " + exception.getMessage());
        }
    }
}
