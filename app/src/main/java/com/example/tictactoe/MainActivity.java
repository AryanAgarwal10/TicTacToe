package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {

    int turn=0;//0 is player 0; 1 is X
    int[] played={2,2,2,2,2,2,2,2,2};
    final int win[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};//only winning positions
    public void work(View view)
    {
       // alerting();
        ImageView imageView=(ImageView) view;
        int a=Integer.parseInt(imageView.getTag().toString());

        if(played[a]==2) {
            played[a]=turn;
            if (turn == 0) {
                imageView.setImageResource(R.drawable.o);
                turn = 1;
            } else {
                imageView.setImageResource(R.drawable.x);
                turn = 0;
            }
            imageView.setScaleX(0f);
            imageView.setScaleY(0f);
            imageView.animate()
                    .alpha(1f)
                    .scaleX(1)
                    .rotation(360)
                    .scaleY(1)
                    .setDuration(300).start();
            if(turn==0)
            {
                if(winningCheck(1)==3)
                {
                    alerting(R.string.winTwo);
                }
                else if(endCheck()) {

                    alerting(R.string.end);
                }
            }
            else {
                if(winningCheck(0)==3) {

                    alerting(R.string.winOne);
                }
                else if(endCheck()) {

                    alerting(R.string.end);
                }
                }
        }

    }
    public void retry(){
        turn=0;
        for(int x=0;x<=8;x++)
        {
            played[x]=2;
            ConstraintLayout constraintLayout=findViewById(R.id.cl);
            ImageView view= (ImageView) constraintLayout.findViewWithTag(x+"");
            view.animate().alpha(0f)
                .scaleX(0)
                .rotation(0)
                .scaleY(0);
        }
    }
    public void alerting(int title)
    {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage("What do you want to do?")
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        retry();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setCancelable(false)
                .show();
    }
    public int winningCheck(int player){
        int c=0;
        for (int x=0;x<=7;x++) {
            for(int y=0;y<=2;y++) {
                if(played[win[x][y]] == player)
                    c++;
                else
                    break;
            }
            if(c==3)
            {
                return c;
            }
            else
                c=0;
        }
    return 0;
    }
    public boolean endCheck(){
        Boolean c=true;
        for(int x=0;x<=8;x++)
        {
            if(played[x]==2)
            {
                c=false;
                break;
            }
        }
        return c;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            played = savedInstanceState.getIntArray("played");
            turn = savedInstanceState.getInt("turn");
            ConstraintLayout constraintLayout = findViewById(R.id.cl);
            ImageView view;
            for (int x = 0; x <= 8; x++) {

                view = (ImageView) constraintLayout.findViewWithTag(x + "");

                if (played[x] == 0) {
                    view.animate().alpha(1f)
                            .scaleX(1)
                            .rotation(0)
                            .scaleY(1);
                    view.setImageResource(R.drawable.o);

                } else if (played[x] == 1) {
                    view.animate().alpha(1f)
                            .scaleX(1)
                            .rotation(0)
                            .scaleY(1);
                    view.setImageResource(R.drawable.x);

                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("played",played);
        outState.putInt("turn",turn);
    }
}