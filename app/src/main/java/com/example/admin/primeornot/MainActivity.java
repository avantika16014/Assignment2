package com.example.admin.primeornot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button ansYesButton;
    private Button ansNoButton;
    private Button next;
    private TextView getNum;
    private TextView getScore;
    Random rand=new Random();
    int randomNo;
    int flag=0;
    int score=0;
    public static final String TAG="Result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.d(TAG, "Inside onCreate");

        getNum=(TextView) findViewById(R.id.getNumber);
        ansYesButton = (Button)findViewById(R.id.ansYes);
        ansNoButton = (Button)findViewById(R.id.ansNo);
        getScore=(TextView)findViewById(R.id.score);
        next = (Button)findViewById(R.id.next);
        //final Random rand=new Random();
        getScore.setText(" "+score+" ");
        randomNo=randInt(1,1000);
        getNum.setText(" "+randomNo+" ");

        ansYesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(flag==0) {
                    flag = 1;
                    if (isPrime(randomNo)) {
                        Log.d(TAG, "Correct!");
                        Toast.makeText(getApplicationContext(), "Awesome!",Toast.LENGTH_SHORT).show();
                        score=score+10;
                        getScore.setText(" "+score+" ");
                    } else {
                        Log.d(TAG, "Incorrect!");
                        Toast.makeText(getApplicationContext(), "Uh Oh!", Toast.LENGTH_SHORT).show();
                        if(score-5 < 0)
                            score=0;
                        else
                            score-=5;

                        getScore.setText(" "+score+" ");
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Already answered!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ansNoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(flag==0) {
                    flag=1;
                    if (isPrime(randomNo)) {
                        Log.d(TAG, "Incorrect!");
                        Toast.makeText(getApplicationContext(), "Uh Oh!", Toast.LENGTH_SHORT).show();
                        if(score-5 < 0)
                            score=0;
                        else
                            score-=5;
                        getScore.setText(" "+score+" ");
                    } else {
                        Log.d(TAG, "Correct!");
                        Toast.makeText(getApplicationContext(), "Awesome!", Toast.LENGTH_SHORT).show();
                        score=score+10;
                        getScore.setText(" "+score+" ");
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Already answered!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==0)
                    Toast.makeText(getApplicationContext(),"Please select an answer!",Toast.LENGTH_SHORT).show();
                else {
                    randomNo = randInt(1, 1000);
                    getNum.setText(" " + randomNo + " ");
                    flag=0;
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        //Log.d(TAG,"inside onSaveInstance");
    }

    int randInt(int min, int max) {

        return(rand.nextInt((max - min) + 1) + min);

    }
    boolean isPrime(int num) {

        int c=1;
        for(int i=2;i<=num;i++)
        {
            if(num%i==0)
                c++;
        }
        return (c==2);

    }
}
