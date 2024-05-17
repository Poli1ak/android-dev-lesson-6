package ru.mirea.panov.notebook;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Notebook";
    private static final String FILE_NAME = "FAVORITE_QUOTE.txt";

    private EditText fileNameEditText;
    private EditText quoteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileNameEditText = findViewById(R.id.fileNameEditText);
        quoteEditText = findViewById(R.id.quoteEditText);
        Button saveButton = findViewById(R.id.saveButton);
        Button loadButton = findViewById(R.id.loadButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromFile();
            }
        });
    }

    private void saveToFile() {
        String fileName = fileNameEditText.getText().toString().trim();
        String quote = quoteEditText.getText().toString().trim();

        if (!fileName.isEmpty() && !quote.isEmpty()) {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(path, fileName);

            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(quote.getBytes());
                Toast.makeText(MainActivity.this, "Цитата сохранена в файл", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Log.e(TAG, "Ошибка при сохранении цитаты", e);
                Toast.makeText(MainActivity.this, "Ошибка при сохранении цитаты", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Введите название файла и цитату", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFromFile() {
        String fileName = fileNameEditText.getText().toString().trim();

        if (!fileName.isEmpty()) {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(path, fileName);

            StringBuilder stringBuilder = new StringBuilder();

            try (FileInputStream inputStream = new FileInputStream(file);
                 InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(inputStreamReader)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                String quote = stringBuilder.toString();
                quoteEditText.setText(quote);
                Toast.makeText(MainActivity.this, "Цитата загружена из файла", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                Log.e(TAG, "Ошибка при загрузке цитаты из файла", e);
                Toast.makeText(MainActivity.this, "Ошибка при загрузке цитаты из файла", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Введите название файла", Toast.LENGTH_SHORT).show();
        }
    }
}
