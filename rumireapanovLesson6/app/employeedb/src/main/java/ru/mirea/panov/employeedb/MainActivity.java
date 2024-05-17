package ru.mirea.panov.employeedb;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "EmployeeDB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();

        // Создание объекта Employee
        Employee employee = new Employee();
        employee.name = "John Smith";
        employee.salary = 10000;
        employee.superpower = "Super strength";
        employee.height = 180;
        employee.weight = 80;

        // Запись супер-героя в базу данных
        employeeDao.insert(employee);

        // Загрузка всех супер-героев из базы данных
        List<Employee> employees = employeeDao.getAll();

        // Получение супер-героя с определенным id
        Employee employeeById = employeeDao.getById(1);

        // Обновление информации о супер-герое
        employeeById.salary = 20000;
        employeeDao.update(employeeById);

        // Вывод информации о супер-героях в лог
        for (Employee emp : employees) {
            Log.d(TAG, "Name: " + emp.name + ", Salary: " + emp.salary +
                    ", Superpower: " + emp.superpower +
                    ", Height: " + emp.height + " cm" +
                    ", Weight: " + emp.weight + " kg");
        }
    }
}
