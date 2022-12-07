package com.example.mathquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PlayGround extends AppCompatActivity {

    String name;
    String[] operatorAR = {"+", "-", "X", "/"};
    int[] optionValues = new int[4];
    int[] optionsAR = {R.id.optionOne, R.id.optionTwo, R.id.optionThree, R.id.optionFour};
    int difficulty, score, right, wrong, level, ans, a, b, randomPlace;
    TextView playerNameTV, scoreTV, rightAnswerTV, wrongAnswerTV, operatorTV, numOneTV, numTwoTV;
    RadioGroup optionsRG;
    Button checkBT;
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_ground);
        getData();
        initGame();
    }

    private void initGame() {
        playerNameTV = findViewById(R.id.playerNameTV);
        scoreTV = findViewById(R.id.scoreTV);
        rightAnswerTV = findViewById(R.id.rightTV);
        wrongAnswerTV = findViewById(R.id.wrongTV);
        operatorTV = findViewById(R.id.operatorTV);
        optionsRG = findViewById(R.id.optionsRG);
        checkBT = findViewById(R.id.checkBT);
        numOneTV = findViewById(R.id.numOneTV);
        numTwoTV = findViewById(R.id.numTwoTV);
        playerNameTV.setText(name);
        level = 1;
        generateQues();
        checkBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((optionsRG.getCheckedRadioButtonId() != R.id.optionOne)
                        && (optionsRG.getCheckedRadioButtonId() != R.id.optionTwo)
                        && (optionsRG.getCheckedRadioButtonId() != R.id.optionThree)
                        && (optionsRG.getCheckedRadioButtonId() != R.id.optionFour)) {
                    Toast.makeText(PlayGround.this, "Please Select an Answer", Toast.LENGTH_SHORT).show();
                } else if (optionsRG.getCheckedRadioButtonId() == optionsAR[randomPlace]) {
                    Toast.makeText(PlayGround.this, "Answer is Correct", Toast.LENGTH_SHORT).show();
                    right++;
                    score += difficulty * level + 1;
                    if (right % 10 == 0) {
                        level++;
                    }
                    generateQues();

                } else {
                    Toast.makeText(PlayGround.this, "Wrong Answer !!!", Toast.LENGTH_SHORT).show();
                    wrong++;
                    generateQues();
                    if (wrong == 5 * difficulty) {
                        setContentView(R.layout.game_over);
                        TextView textView = findViewById(R.id.highScoreTV);
                        textView.setText(Integer.toString(score));
                    }
                }

            }
        });
    }

    private void generateQues() {
        optionsRG.clearCheck();
        scoreTV.setText(Integer.toString(score));
        rightAnswerTV.setText(Integer.toString(right));
        wrongAnswerTV.setText(Integer.toString(wrong));
        rand = new Random();
        int operatorNum = rand.nextInt(4);
        operatorTV.setText(operatorAR[operatorNum]);
        switch (operatorNum) {
            case 0:
                a = rand.nextInt(100 * difficulty * level) + 2;
                b = rand.nextInt(100 * difficulty * level) + 1;
                ans = a + b;
                break;
            case 1:
                a = rand.nextInt(100 * difficulty * level) + 2;
                b = rand.nextInt(100 * difficulty * level) + 1;
                if (a < b) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                ans = a - b;
                break;
            case 2:
                a = rand.nextInt(100 * difficulty * level) + 1;
                b = rand.nextInt(10 * difficulty * level) + 2;
                ans = a * b;
                break;
            case 3:
                a = rand.nextInt(100 * difficulty * level) + 1;
                b = rand.nextInt(10 + 2 * difficulty * level) + 2;
                a = a - a % b;
                ans = a / b;
                break;
        }
        numOneTV.setText(Integer.toString(a));
        numTwoTV.setText(Integer.toString(b));
        setRandomOptions();
    }

    private void setRandomOptions() {
        Random rand = new Random();
        randomPlace = rand.nextInt(4);
        getAnswerSet();
        for (int i = 0; i < 4; i++) {
            RadioButton option = findViewById(optionsAR[i]);
            option.setText(Integer.toString(optionValues[i]));
        }
    }

    public void getData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        difficulty = intent.getIntExtra("difficulty", 1);
    }

    public void getAnswerSet() {
        for (int i = 0; i < 4; i++) {
            if (i == randomPlace) {
                optionValues[i] = ans;
            } else {
                optionValues[i] = ans + rand.nextInt(ans % 10 + 10) - rand.nextInt(ans % 10 + 5);
            }
            for (int j = 0; j < i; j++) {
                if (optionValues[i] == optionValues[j]) {
                    if (i != randomPlace) {
                        optionValues[i] += (rand.nextInt(10) + 1);
                    } else {
                        optionValues[j] += (rand.nextInt(10) + 1);
                    }
                }
            }
        }
    }

}
