package com.example.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, false),
            new Question(R.string.question_3, true)
    };

    private void checkAnswer(boolean userPressed){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messResId = 0;

        if(userPressed == answerIsTrue){
            messResId = R.string.Correct_toast;
        }else{
            messResId=R.string.Incorrect_toast;
        }
        Toast.makeText(this, messResId, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
//        int question = mQuestionBank[mCurrentIndex].getTextResId();
//        mQuestionTextView.setText(question);
            updateQuestion();

        mTrueButton = (Button) findViewById(R.id.True_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Toast.makeText(QuizActivity.this, R.string.Correct_toast, Toast.LENGTH_SHORT).show();
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.False_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Toast.makeText(QuizActivity.this, R.string.Incorrect_toast, Toast.LENGTH_SHORT).show();
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.Next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPreviousButton = (Button) findViewById(R.id.Previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(mCurrentIndex>0) {
                 mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                 updateQuestion();
             }else{

             }
            }
        });

        mCheatButton = (Button) findViewById(R.id.Cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start cheatActivity
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                startActivity(i);

            }
        });

    }


}
