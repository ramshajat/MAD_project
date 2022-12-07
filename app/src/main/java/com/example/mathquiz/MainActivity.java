package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.nameET);
        button = findViewById(R.id.submitBT);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                if(name.isEmpty()) {
                    Toast.makeText(
                            MainActivity.this,
                            "Enter a Name to Continue",
                            Toast.LENGTH_SHORT).show();
                } else if(name.length()<4) {
                    Toast.makeText(
                            MainActivity.this,
                            "Minimum 4 Alpabets",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(
                            MainActivity.this,
                            PlayMenu.class);
                    intent.putExtra("name",
                            name.toUpperCase());
                    startActivity(intent);
                }
            }
        });
    }
}