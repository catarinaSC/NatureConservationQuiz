package com.example.catarina.natureconservationquiz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView name;
    TextView questionOneAnswerOne;
    TextView questionTwoAnswerOne;
    TextView questionTwoAnswerTwo;
    TextView questionThreeAnswerOne;
    TextView questionThreeAnswerTwo;
    TextView questionThreeAnswerFour;
    TextView questionFourAnswerFour;
    TextView questionFiveAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        questionOneAnswerOne = findViewById(R.id.question_one_answer_one);
        questionTwoAnswerOne = findViewById(R.id.question_two_answer_one);
        questionTwoAnswerTwo =findViewById(R.id.question_two_answer_two);
        questionThreeAnswerOne = findViewById(R.id.question_three_answer_one);
        questionThreeAnswerTwo = findViewById(R.id.question_three_answer_two);
        questionThreeAnswerFour = findViewById(R.id.question_three_answer_four);
        questionFourAnswerFour = findViewById(R.id.question_four_answer_four);
        questionFiveAnswer = findViewById(R.id.question_five_answer);

    }

    /**
     * This method is called when the Submit button is clicked.
     */

    public void checkAnswers(View view) {
        // Get user's name
        EditText nameField = (EditText) name;
        Editable nameEditable = nameField.getText();
        String user = nameEditable.toString();

        //Answers to the questions
        //Answer One. RadioButton
        RadioButton questionOneAnswerOneRadioButton = (RadioButton) questionOneAnswerOne;
        boolean isQuestionOneAnswerOne = questionOneAnswerOneRadioButton.isChecked();

        //Answer Two. CheckBox

        CheckBox questionTwoAnswerOneCheckBox = (CheckBox) questionTwoAnswerOne;
        boolean isQuestionTwoAnswerOne = questionTwoAnswerOneCheckBox.isChecked();
        CheckBox questionTwoAnswerTwoCheckBox = (CheckBox) questionTwoAnswerTwo;
        boolean isQuestionTwoAnswerTwo = questionTwoAnswerTwoCheckBox.isChecked();
        boolean isQuestionTwoTotal = isQuestionTwoAnswerOne && isQuestionTwoAnswerTwo;

        //Answer Three. CheckBox

        CheckBox questionThreeAnswerOneCheckBox = (CheckBox) questionThreeAnswerOne;
        boolean isQuestionThreeAnswerOne = questionThreeAnswerOneCheckBox.isChecked();
        CheckBox questionThreeAnswerTwoCheckBox = (CheckBox) questionThreeAnswerTwo;
        boolean isQuestionThreeAnswerTwo = questionThreeAnswerTwoCheckBox.isChecked();
        CheckBox questionThreeAnswerFourCheckBox = (CheckBox) questionThreeAnswerFour;
        boolean isQuestionThreeAnswerFour = questionThreeAnswerFourCheckBox.isChecked();
        boolean isQuestionThreeTotal = isQuestionThreeAnswerOne && isQuestionThreeAnswerTwo && isQuestionThreeAnswerFour;

        //Answer Four. RadioButton

        RadioButton questionFourAnswerFourRadioButton = (RadioButton) questionFourAnswerFour;
        boolean isQuestionFourAnswerFour = questionFourAnswerFourRadioButton.isChecked();

        //Answer Five. EditText

        EditText questionFiveAnswerEditText = (EditText) questionFiveAnswer;
        String questionFiveAnswer = questionFiveAnswerEditText.getText().toString();
        boolean isQuestionFiveAnswer = questionFiveAnswer.equalsIgnoreCase("Fontainebleu") || questionFiveAnswer.equalsIgnoreCase("Fontainebleu ");

        //Toast Message with the results

        Context context = getApplicationContext();
        CharSequence text = createAnswerSummary(user, isQuestionOneAnswerOne, isQuestionTwoTotal, isQuestionThreeTotal, isQuestionFourAnswerFour, isQuestionFiveAnswer);
        int duration = Toast.LENGTH_SHORT;
        Toast finalResult = Toast.makeText(context, text, duration);
        finalResult.show();
    }

    // Use an intent to launch an web page.
    public void webPage(View view) {
        String site = "https:www.iucn.org";
        Uri web = Uri.parse(site);
        Intent intent = new Intent(Intent.ACTION_VIEW, web);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //Counts the right answers

    public String createAnswerSummary(String user, boolean isQuestionOneAnswerOne, boolean isQuestionTwoTotal, boolean isQuestionThreeTotal, boolean isQuestionFourAnswerFour, boolean isQuestionFiveAnswer) {

        int counter = 0;

        if (isQuestionOneAnswerOne) {
            counter = counter + 1;
        }

        if (isQuestionTwoTotal) {
            counter = counter + 1;
        }

        if (isQuestionThreeTotal) {
            counter = counter + 1;
        }

        if (isQuestionFourAnswerFour) {
            counter = counter + 1;
        }

        if (isQuestionFiveAnswer) {
            counter = counter + 1;
        }

        String quizResultsMessage = getString(R.string.user_name, user);
        quizResultsMessage += "\n\n" + getString(R.string.counter, counter);
        quizResultsMessage += "\n\n" + getString(R.string.thank_you);
        return quizResultsMessage;
    }

}
