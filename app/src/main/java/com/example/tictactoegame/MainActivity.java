package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * MainActivity.java
 * <P> Tic Tac Toe Android Game, User Vs. Bot </p>
 * Date: 2019-08-24
 * Time: 18:45
 * Package: com.example.tictactoegame
 *
 * @author Mohamad Jayyusi - https://github.com/jayyusi
 * @version 1.0
 */


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    enum Status {X, O}

    //local variables
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;

    //reset button
    private Button reset;

    private TextView title;
    private TextView output;

    private int random;
    private int resID;
    private String buttonID;
    private Button butt;
    private boolean gameOver;


    /**********************************/
    private int movesCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the buttons
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);

        //reset the game
        reset = findViewById(R.id.reset);

        output = (TextView) findViewById(R.id.output);

        //register listeners for each button
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);

        //register a listener for the reset button
        reset.setOnClickListener(this);

        /**********************************/

        //initialize buttons to ""
        initButtons();

        //bot move 1
        botPlay();

        /**********************************/
    }

    @Override
    public void onClick(View view) {
        Button butt = (Button) view;
        boolean userPlayed = false;
        if ((butt.getText().toString()).equals("Reset")) {
            initButtons();
            output.setText("Reset Clicked");
            //make the first bot move
            botPlay();
        } else if(butt.getText().equals("")){
            butt.setText(Status.O.toString());
            userPlayed = true;
            movesCounter++;
            butt.setEnabled(false);
            disableButtons();
        } else if(butt.getText().equals(Status.X.toString())){
            output.setText("Illegal Move");
        }
        if(userPlayed && isWinner() == -1){
            botPlay();
        }else if(isWinner() == 1){ //user won
            output.setText("You Won !");
            disableButtons();
        }
    }

    public void initButtons() {
        movesCounter = 0;
        gameOver = false;
        output.setTextSize(25);
        for (int i = 1; i <= 9; i++) {
            String buttonID = "b" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            Button butt = findViewById(resID);
            butt.setText("");
            butt.setTextSize(30);
        }
        enableButtons();
        b1.setBackgroundColor(Color.LTGRAY);
        b2.setBackgroundColor(Color.LTGRAY);
        b3.setBackgroundColor(Color.LTGRAY);
        b4.setBackgroundColor(Color.LTGRAY);
        b5.setBackgroundColor(Color.LTGRAY);
        b6.setBackgroundColor(Color.LTGRAY);
        b7.setBackgroundColor(Color.LTGRAY);
        b8.setBackgroundColor(Color.LTGRAY);
        b9.setBackgroundColor(Color.LTGRAY);
    }

    public void disableButtons() {
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(false);
        b9.setEnabled(false);
    }

    public void enableButtons() {
        for (int i = 1; i <= 9; i++) {
            String buttonID = "b" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            Button butt = findViewById(resID);
            if (butt.getText().equals("")) {
                butt.setEnabled(true);
            }
        }
    }

    public void botPlay(){
        do {
            random = (int) (9 * Math.random()+1);
            buttonID = "b" + random; // if random is 1, 2, 3, etc, then b1, b2, b3, etc.
            resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            butt = findViewById(resID);
        } while (!(butt.getText().equals("")) && !gameOver);
        butt.setText(Status.X.toString());
        butt.setEnabled(false);
        movesCounter++;
        output.setText("Bot played " + random);
        enableButtons();
        if(isWinner() == 0){
            output.setText("Bot Won");
            disableButtons();
        }
    }

    //0 is bot, 1 is user, -1 no winner
    public int isWinner() {
        int result = -1;

        //check row 1
        if (b1.getText().equals(b2.getText()) &&
                b2.getText().equals(b3.getText())) {
            if (b1.getText().equals(Status.X.toString())) {
                b1.setBackgroundColor(Color.RED);
                b2.setBackgroundColor(Color.RED);
                b3.setBackgroundColor(Color.RED);
                result = 0;
            } else if (b1.getText().equals(Status.O.toString())) {
                b1.setBackgroundColor(Color.GREEN);
                b2.setBackgroundColor(Color.GREEN);
                b3.setBackgroundColor(Color.GREEN);
                result = 1;
            }
        }
        //check row 2
        if (b4.getText().equals(b5.getText()) &&
                b5.getText().equals(b6.getText())) {
            if (b4.getText().equals(Status.X.toString())) {
                b4.setBackgroundColor(Color.RED);
                b5.setBackgroundColor(Color.RED);
                b6.setBackgroundColor(Color.RED);
                result = 0;
            } else if (b4.getText().equals(Status.O.toString())) {
                b4.setBackgroundColor(Color.GREEN);
                b5.setBackgroundColor(Color.GREEN);
                b6.setBackgroundColor(Color.GREEN);
                result = 1;
            }
        }
        //check row 3
        if (b7.getText().equals(b8.getText()) &&
                b8.getText().equals(b9.getText())) {
            if (b7.getText().equals(Status.X.toString())) {
                b7.setBackgroundColor(Color.RED);
                b8.setBackgroundColor(Color.RED);
                b9.setBackgroundColor(Color.RED);
                result = 0;
            } else if (b7.getText().equals(Status.O.toString())) {
                b7.setBackgroundColor(Color.GREEN);
                b8.setBackgroundColor(Color.GREEN);
                b9.setBackgroundColor(Color.GREEN);
                result = 1;
            }
        }
        //check col 1
        if (b1.getText().equals(b4.getText()) &&
                b4.getText().equals(b7.getText())) {
            if (b1.getText().equals(Status.X.toString())){
                b1.setBackgroundColor(Color.RED);
                b4.setBackgroundColor(Color.RED);
                b7.setBackgroundColor(Color.RED);
                result = 0;
            } else if (b1.getText().equals(Status.O.toString())) {
                b1.setBackgroundColor(Color.GREEN);
                b4.setBackgroundColor(Color.GREEN);
                b7.setBackgroundColor(Color.GREEN);
                result = 1;
            }
        }
        //check col 2
        if (b2.getText().equals(b5.getText()) &&
                b5.getText().equals(b8.getText())) {
            if (b2.getText().equals(Status.X.toString())) {
                b2.setBackgroundColor(Color.RED);
                b5.setBackgroundColor(Color.RED);
                b8.setBackgroundColor(Color.RED);
                result = 0;
            } else if (b2.getText().equals(Status.O.toString())) {
                b2.setBackgroundColor(Color.GREEN);
                b5.setBackgroundColor(Color.GREEN);
                b8.setBackgroundColor(Color.GREEN);
                result = 1;
            }
        }
        //check col 3
        if (b3.getText().equals(b6.getText()) &&
                b6.getText().equals(b9.getText())) {
            if (b3.getText().equals(Status.X.toString())) {
                b3.setBackgroundColor(Color.RED);
                b6.setBackgroundColor(Color.RED);
                b9.setBackgroundColor(Color.RED);
                result = 0;
            } else if (b3.getText().equals(Status.O.toString())) {
                b3.setBackgroundColor(Color.GREEN);
                b6.setBackgroundColor(Color.GREEN);
                b9.setBackgroundColor(Color.GREEN);
                result = 1;
            }
        }
        //check diagonal \
        if (b1.getText().equals(b5.getText()) &&
                b5.getText().equals(b9.getText())) {
            if (b1.getText().equals(Status.X.toString())) {
                b1.setBackgroundColor(Color.RED);
                b5.setBackgroundColor(Color.RED);
                b9.setBackgroundColor(Color.RED);
                result = 0;
            } else if (b1.getText().equals(Status.O.toString())) {
                b1.setBackgroundColor(Color.GREEN);
                b5.setBackgroundColor(Color.GREEN);
                b9.setBackgroundColor(Color.GREEN);
                result = 1;
            }
        }
        //check diagonal /
        if (b3.getText().equals(b5.getText()) &&
                b5.getText().equals(b7.getText())) {
            if (b3.getText().equals(Status.X.toString())) {
                b3.setBackgroundColor(Color.RED);
                b5.setBackgroundColor(Color.RED);
                b7.setBackgroundColor(Color.RED);
                result = 0;
            } else if (b3.getText().equals(Status.O.toString())) {
                b3.setBackgroundColor(Color.GREEN);
                b5.setBackgroundColor(Color.GREEN);
                b7.setBackgroundColor(Color.GREEN);
                result = 1;
            }
        }

        //No winners ?
        if(result == -1 && movesCounter == 9){
            output.setText("Game Over - No Winners");
            gameOver = true;
            disableButtons();
        }
        return result;
    }// End of isWinner()
}
