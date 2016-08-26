package com.example.admin.primeornot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button ansYesButton;
    private Button ansNoButton;
    private Button hint;
    private Button cheat;
    private Button next;
    private TextView getNum;
    private TextView getScore;
    private TextView flag;
    private TextView useCheatView;
    private TextView useHintView;
    Random rand=new Random();
    int randomNo;
    boolean useCheat;
    boolean useHint;
    //int flag=0;
    int score=0;
    public static final String TAG="Result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.d(TAG, "Inside onCreate");

        getNum=(TextView) findViewById(R.id.getNumber);
        ansYesButton = (Button)findViewById(R.id.ansYes);
        flag=(TextView)findViewById(R.id.flag);
        ansNoButton = (Button)findViewById(R.id.ansNo);
        hint=(Button)findViewById(R.id.hint);
        cheat=(Button)findViewById(R.id.cheat);
        useHintView=(TextView)findViewById(R.id.useHint);
        useCheatView=(TextView)findViewById(R.id.useCheat);
        getScore=(TextView)findViewById(R.id.score);
        next = (Button)findViewById(R.id.next);
        //final Random rand=new Random();
        if(savedInstanceState==null) {
            getScore.setText(" " + score + " ");
            randomNo = randInt(1, 1000);
            getNum.setText(" " + randomNo + " ");
            flag.setText(" " + 0 + " ");
        }
        ansYesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if(flag.getText().toString().equals(" 0 "))
                {
                    flag.setText(" "+1+" " );
                    if (isPrime(randomNo))
                    {
                        Log.d(TAG, "Correct!");
                        Toast.makeText(getApplicationContext(), "Awesome!",Toast.LENGTH_SHORT).show();
                        score=score+10;
                        getScore.setText(" "+score+" ");
                    }
                    else
                    {
                        Log.d(TAG, "Incorrect!");
                        Toast.makeText(getApplicationContext(), "Uh Oh!", Toast.LENGTH_SHORT).show();

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
                if(flag.getText().toString().equals(" 0 ")) {
                    flag.setText(" "+1+" " );
                    if (isPrime(randomNo)) {
                        Log.d(TAG, "Incorrect!");
                        Toast.makeText(getApplicationContext(), "Uh Oh!", Toast.LENGTH_SHORT).show();

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
                if(flag.getText().toString().equals(" 0 "))
                    Toast.makeText(getApplicationContext(),"Please select an answer!",Toast.LENGTH_SHORT).show();
                else {
                    randomNo = randInt(1, 1000);
                    getNum.setText(" " + randomNo + " ");
                    flag.setText(" "+0+" ");
                }
            }
        });

        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),HintActivity.class);
                startActivityForResult(intent,1);
            }
        });

        cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),CheatActivity.class);
                intent.putExtra("number",randomNo);
                intent.putExtra("ans",isPrime(randomNo));
                startActivityForResult(intent,2);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Bundle extras=data.getExtras();
            useHint=extras.getBoolean("hintUsed");
        }
        else if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Bundle extras=data.getExtras();
            useCheat=extras.getBoolean("cheatUsed");
        }
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
