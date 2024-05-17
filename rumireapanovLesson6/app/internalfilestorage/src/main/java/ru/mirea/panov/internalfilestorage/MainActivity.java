package ru.mirea.panov.internalfilestorage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "rusHistoryDateAndDesc.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText dateEditText = findViewById(R.id.dateEditText);
        EditText descriptionEditText = findViewById(R.id.descriptionEditText);
        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = dateEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                if (!date.isEmpty() && !description.isEmpty()) {
                    saveToFile(date, description);
                    dateEditText.setText("");
                    descriptionEditText.setText("");
                    Toast.makeText(MainActivity.this, "Запись сохранена", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Введите дату и описание", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveToFile(String date, String description) {
        try (FileOutputStream outputStream = openFileOutput(FILE_NAME, MODE_APPEND)) {
            String data = date + ": " + description + "\n";
            outputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка при сохранении записи", Toast.LENGTH_SHORT).show();
        }
    }

    private String readFromFile() {
        StringBuilder stringBuilder = new StringBuilder();

        try (FileInputStream inputStream = openFileInput(FILE_NAME)) {
            int character;
            while ((character = inputStream.read()) != -1) {
                stringBuilder.append((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка при чтении файла", Toast.LENGTH_SHORT).show();
        }

        return stringBuilder.toString();
    }
}
