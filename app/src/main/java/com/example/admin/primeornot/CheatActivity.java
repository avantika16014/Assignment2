package com.example.admin.primeornot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private Button showCheat;
    private TextView cheatView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        final Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        final int num=extras.getInt("number");
        final boolean ans=extras.getBoolean("ans");
        showCheat=(Button)findViewById(R.id.cheatButton);
        cheatView=(TextView)findViewById(R.id.cheatView);
        showCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ans==true)
                    cheatView.setText("The number "+num+" is prime.");
                else
                    cheatView.setText("The number "+num+" is not prime.");
                intent.putExtra("cheatUsed",true);
                setResult(RESULT_OK,intent);
            }
        });
    }
}
