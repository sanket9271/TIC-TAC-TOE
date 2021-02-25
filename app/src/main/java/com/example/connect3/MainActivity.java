package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0=Yellow ,1=Red
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer=0;
    int count=0;
    boolean gameActive=true;
    public void dropin(View view)
    {
        ImageView counter=(ImageView) view;

        int tappedCounter=Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameActive==true)
        {
            counter.setTranslationY(-1500);
            gameState[tappedCounter] = activePlayer;
            count++;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotationBy(3600).setDuration(500);

            if(count==9) {
                Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
                TextView winnerTextView=(TextView)findViewById(R.id.winnertext);
                winnerTextView.setText( "Bad Luck nobody won!! ");
                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);
                count=0;
        }



            for (int[] winningPisition : winningPositions) {
                if (gameState[winningPisition[0]] == gameState[winningPisition[1]] && gameState[winningPisition[1]] == gameState[winningPisition[2]] && gameState[winningPisition[0]] != 2) {
                    String winner;
                    gameActive=false;
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }


                    Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
                    TextView winnerTextView=(TextView)findViewById(R.id.winnertext);

                        winnerTextView.setText(winner + " has won!!");
                        Toast.makeText(this, winner +" has won!!", Toast.LENGTH_SHORT).show();
                        playAgainButton.setVisibility(View.VISIBLE);
                        winnerTextView.setVisibility(View.VISIBLE);

                    count=0;
                }
            }
        }


    }

    public void playAgain(View view)
    {
        Log.i("Info","PlayAgain method");
        Button button=(Button)findViewById(R.id.playAgainButton);
        button.setVisibility(View.INVISIBLE);
        TextView textView=(TextView)findViewById(R.id.winnertext);
        textView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }

        activePlayer=0;
        gameActive=true;
        count=0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}