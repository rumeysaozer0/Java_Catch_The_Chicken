package com.rumeysaozer.javacatchthechicken;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.rumeysaozer.javacatchthechicken.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
       imageView = binding.imageView;
       imageView2 = binding.imageView2;
       imageView3 = binding.imageView3;
       imageView5 = binding.imageView5;
       imageView6 = binding.imageView6;
       imageView7 = binding.imageView7;
       imageView9 = binding.imageView9;
       imageView10 = binding.imageView10;
       imageView11 = binding.imageView11;
       imageView13 = binding.imageView13;
       imageView14 = binding.imageView14;
       imageView15 = binding.imageView15;
     imageArray = new ImageView[]{
          imageView,imageView2,imageView3,imageView5,imageView6,imageView7,imageView9,imageView10,imageView11,imageView13,imageView14,imageView15
     };
     hideImages();
        score = 0;
        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long l) {
                binding.timeText.setText("Time: "+ l/1000);
            }

            @Override
            public void onFinish() {
                binding.timeText.setText("Time off");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure you want to restart the game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Game Over!!", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();
    }
    public void increaseScore (View view){
        score++;
        binding.scoreText.setText("Score: "+score);
    }
    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
}