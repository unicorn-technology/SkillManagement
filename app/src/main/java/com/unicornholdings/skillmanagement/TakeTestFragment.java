package com.unicornholdings.skillmanagement;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;



import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class TakeTestFragment extends AppCompatDialogFragment {

    private TextView questionBox;
    private RadioGroup radioGroup;
    private Button submit;
    HashMap<Integer,String> questions=new HashMap<Integer,String>();
    HashMap<Integer,ArrayList<String>> options= new HashMap<>();
    HashMap<Integer,String> answers=new HashMap<Integer,String>();

    private static final String TAG = "Player Info";


    private ViewPager mViewPager;
    private Integer currentQuestion = 1;
    private int currentMatrix = 0 ;
    private User user;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return super.onCreateDialog(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(1400 ,2000);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.taketestpopup, container,false);
        Log.d(TAG, "onCreate: Starting.");
        radioGroup = v.findViewById(R.id.radiogroup );
        questionBox = v.findViewById(R.id.textView4);
        submit = v.findViewById(R.id.submitButton);
        populateQuestion();
        try {
            this.user = (User)InternalStorage.readObject(this.getContext(),"User");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        populateScreen(currentQuestion);
        RadioButton button;
        for (int key : options.keySet()) {
            button = new RadioButton(this.getContext());
            button.setText(String.valueOf(options.get(key)) );
            radioGroup.addView(button);
        }
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(selectedId);
                checkAnswer(String.valueOf(radioButton.getText()));
                if ( currentQuestion <= questions.size()){
                    populateScreen(currentQuestion);
                }
                else {
                    calculateSkillMatrix();
                }

            }
        });
        return v;
    }

    private void calculateSkillMatrix() {
        if ( 0.7 * questions.size() <= currentMatrix){
            user.setMatrixScore(user.getMatrixScore() + 1);
        }
        Toast.makeText(this.getActivity(), "Current Skill Matrix Score : "  + user.getMatrixScore() , Toast.LENGTH_LONG).show();
        try {
            InternalStorage.writeObject(this.getContext(),"User",user);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void checkAnswer(String s) {
        if (answers.get(currentQuestion).equals(s)){
            currentMatrix = currentMatrix + 1;
        }
        currentQuestion++;
    }

    private void populateScreen(Integer questionNumber) {
        questionBox.setText(questions.get(questionNumber));
        radioGroup.removeAllViews();
        RadioButton button;
        for (String key : options.get(questionNumber)) {
            button = new RadioButton(this.getContext());
            button.setText(key) ;
            radioGroup.addView(button);
        }

    }

    private void populateQuestion() {
        questions.put(1," A process that involves recognizing and focusing on the important\n" +
                "characteristics of a situation or object is known as:" ) ;
        ArrayList<String> optionsArray = new ArrayList<>();
        optionsArray.add("Encapsulation");
        optionsArray.add("Polymorphism");
        optionsArray.add("Abstraction");
        optionsArray.add("Inheritance");
        options.put(1,optionsArray);
        answers.put(1,"Abstraction");

        questions.put(2,"Which statement is true regarding an object?" ) ;
        optionsArray = new ArrayList<>();
        optionsArray.add("An object is what classes instantiated are from");
        optionsArray.add("An object is an instance of a class");
        optionsArray.add("An object is a variable");
        optionsArray.add("An object is a reference to an attribute");
        options.put(2,optionsArray);
        answers.put(2,"An object is an instance of a class");

        questions.put(3,"In object-oriented programming, composition relates to ?" ) ;
        optionsArray = new ArrayList<>();
        optionsArray.add("The use of consistent coding conventions");
        optionsArray.add("The organization of components interacting to achieve a coherent, common behavior");
        optionsArray.add("The use of inheritance to achieve polymorphic behavior");
        optionsArray.add("The organization of components interacting not to achieve a coherent common behavior");
        options.put(3,optionsArray);
        answers.put(3,"The organization of components interacting to achieve a coherent, common behavior");

        questions.put(4,"In object-oriented programming, new classes can be defined by"+"\n"+ "extending existing classes. This is an example of:" ) ;
        optionsArray = new ArrayList<>();
        optionsArray.add("Encapsulation");
        optionsArray.add("Interface");
        optionsArray.add("Composition");
        optionsArray.add("Inheritance");
        options.put(4,optionsArray);
        answers.put(4,"Inheritance");

        questions.put(5,"Which of the following does not belong: If a class inherits from some other class, it should" ) ;
        optionsArray = new ArrayList<>();
        optionsArray.add("Make use of the parent class’s capabilities");
        optionsArray.add("Over-ride or add the minimum to accomplish the derived class’ purpose");
        optionsArray.add("Over-ride all the methods of its parent class");
        optionsArray.add("Make sure the result “IS-A-KIND-OF” its base class");
        options.put(5,optionsArray);
        answers.put(5,"Over-ride all the methods of its parent class");

        questions.put(6,"Object-oriented inheritance models the" ) ;
        optionsArray = new ArrayList<>();
        optionsArray.add("is a kind of relationship");
        optionsArray.add("has a relationship");
        optionsArray.add("want to be relationship");
        optionsArray.add("inheritance does not describe any kind of relationship between classes");
        options.put(6,optionsArray);
        answers.put(6,"is a kind of relationship");

        questions.put(7,"The wrapping up of data and functions into a single unit is called" ) ;
        optionsArray = new ArrayList<>();
        optionsArray.add("Encapsulation");
        optionsArray.add("Abstraction");
        optionsArray.add("Data Hiding");
        optionsArray.add("Polymorphism");
        options.put(7,optionsArray);
        answers.put(7,"Encapsulation");

        questions.put(8,"Polymorphism" ) ;
        optionsArray = new ArrayList<>();
        optionsArray.add("Is not supported by Java");
        optionsArray.add("Refers to the ability of two or more objects belonging to different classes"+"\n"+" to respond to exactly the same message in different class-specific ways");
        optionsArray.add("Simplifies code maintenance");
        optionsArray.add("Refers to the ability of two or more objects belonging to different classes"+"\n"+ "to respond to exactly the same message in different class-specific ways");
        options.put(8,optionsArray);
        answers.put(8,"Refers to the ability of two or more objects belonging to different classes"+"\n"+ "to respond to exactly the same message in different class-specific ways");

        questions.put(9,"In object-oriented programming, new classes can be defined by extending existing classes. This is an example of:" ) ;
        optionsArray = new ArrayList<>();
        optionsArray.add("Encapsulation");
        optionsArray.add("Interface");
        optionsArray.add("Composition");
        optionsArray.add("Inheritance");
        options.put(9,optionsArray);
        answers.put(9,"Inheritance");

        questions.put(10,"Given a class named student, which of the following is a valid constructor declaration for the class?" ) ;
        optionsArray = new ArrayList<>();
        optionsArray.add("Student (student s) { }");
        optionsArray.add("Student student ( ) { }");
        optionsArray.add("Private final student ( ) { }");
        optionsArray.add("Void student ( ) { }");
        options.put(10,optionsArray);
        answers.put(10,"Student (student s) { }");

    }

    @Override
    public void onStart() {
        // full screen
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void setSelection(User user) {



    }
}
