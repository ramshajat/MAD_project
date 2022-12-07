package com.example.mathquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayMenu extends AppCompatActivity implements View.OnClickListener {

    String name;
    TextView nameTV;
    Button easyBT, mediumBT, hardBT;
    int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_menu);
        getData();
        setName();
        initAll();
    }

    private void initAll() {
        easyBT = findViewById(R.id.easyBT);
        mediumBT = findViewById(R.id.mediumBT);
        hardBT = findViewById(R.id.hardBT);
        easyBT.setOnClickListener(this);
        mediumBT.setOnClickListener(this);
        hardBT.setOnClickListener(this);
    }

    private void setName() {
        nameTV = findViewById(R.id.nameTV);
        nameTV.setText(name);
    }

    public void getData() {
        Intent i = getIntent();
        name = i.getStringExtra("name");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id) {
            case R.id.easyBT:
                difficulty = 2;
                break;
            case R.id.mediumBT:
                difficulty = 4;
                break;
            case R.id.hardBT:
                difficulty = 6;
                break;
        }
        Intent intent = new Intent(PlayMenu.this,PlayGround.class);
        intent.putExtra("difficulty",difficulty);
        intent.putExtra("name",name);
        startActivity(intent);
    }

}
