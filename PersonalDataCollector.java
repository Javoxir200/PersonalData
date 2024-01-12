import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PersonalDataCollector {

    private static final int REQUIRED_DATA_COUNT = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the data
        System.out.println("Введите следующие данные через пробел:");
        System.out.println("Фамилия Имя Отчество Дата рождения (дд.мм.гггг) Номер телефона Пол (f или m)");

        // Read the data from the user
        String[] data = scanner.nextLine().split(" ");

        // Check the quantity of data entered
        if (data.length != REQUIRED_DATA_COUNT) {
            System.out.println("Ошибка: введено неверное количество данных. Пожалуйста, введите точно " + REQUIRED_DATA_COUNT + " параметры.");
            return;
        }

        // Parse the data
        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        LocalDate dateOfBirth;
        try {
            dateOfBirth = LocalDate.parse(data[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка: неверный формат даты. Пожалуйста, введите дату рождения в формате дд.мм.гггг.");
            return;
        }
        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(data[4]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Неверный формат номера телефона. Пожалуйста, введите номер телефона как целое число без знака.");
            return;
        }
        char gender = data[5].charAt(0);
        if (gender != 'f' && gender != 'm') {
            System.out.println("Ошибка: неверный пол. Пожалуйста, введите «f» для женщин или «m» для мужчин.");
            return;
        }

        // Create the file name
        String fileName = lastName + ".txt";

        // Write the data to the file
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("Фамилия: " + lastName + "\n");
            fileWriter.write("Имя: " + firstName + "\n");
            fileWriter.write("Отчество: " + middleName + "\n");
            fileWriter.write("Дата рождения: " + dateOfBirth + "\n");
            fileWriter.write("Номер телефона: " + phoneNumber + "\n");
            fileWriter.write("Пол: " + gender + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка: Не удалось записать данные в файл..");
            return;
        }

    }
}