package org.forestpark.joshuascorekeeper;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;


public class MainActivity extends AppCompatActivity {


    private EditText teamNameLeft, teamNameRight;
    private TextView scoreDisplayLeft, scoreDisplayRight, timeTextView;
    private Button touchdownLeft, fieldGoalLeft, resetLeft;
    private Button touchdownRight, fieldGoalRight, resetRight;
    private Button subtractLeft1, subtractLeft5, subtractLeft10;
    private Button subtractRight1, subtractRight5, subtractRight10;
    private Button startTimerButton, pauseTimerButton, resetAllButton;


    private int scoreLeft = 0;
    private int scoreRight = 0;
    private CountDownTimer timer;
    private boolean isTimerRunning = false;
    private long timeLeftInMillis = 600000; // 10 minutes in milliseconds


    MediaPlayer mysound;
    View mainScreen;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        // Initialize UI components
        teamNameLeft = findViewById(R.id.teamNameLeft);
        teamNameRight = findViewById(R.id.teamNameRight);
        scoreDisplayLeft = findViewById(R.id.scoreDisplayLeft);
        scoreDisplayRight = findViewById(R.id.scoreDisplayRight);
        timeTextView = findViewById(R.id.timeTextView);


        touchdownLeft = findViewById(R.id.touchdownLeft);
        fieldGoalLeft = findViewById(R.id.fieldGoalLeft);
        resetLeft = findViewById(R.id.resetLeft);
        subtractLeft1 = findViewById(R.id.subtractLeft1);
        subtractLeft5 = findViewById(R.id.subtractLeft5);
        subtractLeft10 = findViewById(R.id.subtractLeft10);


        touchdownRight = findViewById(R.id.touchdownRight);
        fieldGoalRight = findViewById(R.id.fieldGoalRight);
        resetRight = findViewById(R.id.resetRight);
        subtractRight1 = findViewById(R.id.subtractRight1);
        subtractRight5 = findViewById(R.id.subtractRight5);
        subtractRight10 = findViewById(R.id.subtractRight10);


        startTimerButton = findViewById(R.id.startTimerButton);
        pauseTimerButton = findViewById(R.id.pauseTimerButton);
        resetAllButton = findViewById(R.id.resetAllButton);
        mainScreen = (View) findViewById(R.id.MainScreen);
        mainScreen.setBackgroundResource(R.drawable.background);


        // Button listeners
        touchdownLeft.setOnClickListener(v -> {
            scoreLeft += 6;
            updateScore();
            mysound.start();
        });


        fieldGoalLeft.setOnClickListener(v -> {
            scoreLeft += 2; // Field goal adds 2 points now
            updateScore();
            mysound.start(); //MY SOUND
        });


        subtractLeft1.setOnClickListener(v -> {
            if (scoreLeft > 0) scoreLeft -= 1;
            updateScore();
        });


        subtractLeft5.setOnClickListener(v -> {
            if (scoreLeft >= 5) scoreLeft -= 5;
            updateScore();
        });


        subtractLeft10.setOnClickListener(v -> {
            if (scoreLeft >= 10) scoreLeft -= 10;
            updateScore();
        });


        resetLeft.setOnClickListener(v -> {
            scoreLeft = 0;
            updateScore();
        });


        touchdownRight.setOnClickListener(v -> {
            scoreRight += 6;
            updateScore();
            mysound.start();
        });


        fieldGoalRight.setOnClickListener(v -> {
            scoreRight += 2; // Field goal adds 2 points now
            updateScore();
            mysound.start();
        });


        subtractRight1.setOnClickListener(v -> {
            if (scoreRight > 0) scoreRight -= 1;
            updateScore();
        });


        subtractRight5.setOnClickListener(v -> {
            if (scoreRight >= 5) scoreRight -= 5;
            updateScore();
        });


        subtractRight10.setOnClickListener(v -> {
            if (scoreRight >= 10) scoreRight -= 10;
            updateScore();
        });


        resetRight.setOnClickListener(v -> {
            scoreRight = 0;
            updateScore();
        });


        startTimerButton.setOnClickListener(v -> startTimer());


        pauseTimerButton.setOnClickListener(v -> {
            if (isTimerRunning) {
                pauseTimer();
            } else {
                Log.d("MainActivity", "Timer is already paused");
            }
        });


        resetAllButton.setOnClickListener(v -> {
            scoreLeft = 0;
            scoreRight = 0;
            timeLeftInMillis = 600000;
            updateScore();
            updateTimerText();
            if (isTimerRunning) pauseTimer();
        });


        mysound = MediaPlayer.create(MainActivity.this,R.raw.audio1);




    }


    private void updateScore() {
        scoreDisplayLeft.setText(String.valueOf(scoreLeft));
        scoreDisplayRight.setText(String.valueOf(scoreRight));
    }


    private void startTimer() {
        if (isTimerRunning) return; // Prevent starting multiple timers


        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }


            @Override
            public void onFinish() {
                isTimerRunning = false;
            }
        }.start();


        isTimerRunning = true;
    }


    private void pauseTimer() {
        if (timer != null && isTimerRunning) {
            timer.cancel(); // Stop the timer
            isTimerRunning = false; // Update status
            Log.d("MainActivity", "Timer paused at: " + timeLeftInMillis + " ms remaining");
        } else {
            Log.d("MainActivity", "Timer is null or already paused");
        }
    }


    private void updateTimerText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        timeTextView.setText(String.format("Time: %02d:%02d", minutes, seconds));
    }


    public void playIt(View v){
    }


}
