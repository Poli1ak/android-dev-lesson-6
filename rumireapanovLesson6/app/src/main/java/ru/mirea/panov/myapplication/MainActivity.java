package ru.mirea.panov.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText groupNumberEditText;
    private EditText listNumberEditText;
    private EditText favoriteMovieEditText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        loadDataFromPreferences();

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view -> saveDataToPreferences());
    }

    private void initializeViews() {
        groupNumberEditText = findViewById(R.id.groupNumberEditText);
        listNumberEditText = findViewById(R.id.listNumberEditText);
        favoriteMovieEditText = findViewById(R.id.favoriteMovieEditText);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    }

    private void saveDataToPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("groupNumber", groupNumberEditText.getText().toString());
        editor.putString("listNumber", listNumberEditText.getText().toString());
        editor.putString("favoriteMovie", favoriteMovieEditText.getText().toString());
        editor.apply();
    }

    private void loadDataFromPreferences() {
        groupNumberEditText.setText(sharedPreferences.getString("groupNumber", ""));
        listNumberEditText.setText(sharedPreferences.getString("listNumber", ""));
        favoriteMovieEditText.setText(sharedPreferences.getString("favoriteMovie", ""));
    }
}
