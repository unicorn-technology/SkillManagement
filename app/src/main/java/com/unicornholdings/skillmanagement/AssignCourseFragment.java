package com.unicornholdings.skillmanagement;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;


public class AssignCourseFragment extends AppCompatDialogFragment {

    private TextView playerTitle;
    private ImageView closeDialog;
    private TextView courseName;
    private TextView courseDuration;
    private TextView courseProvider;

    private Button assignButton;
    private static final String TAG = "Player Info";


    private ViewPager mViewPager;
    private Courses course;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return super.onCreateDialog(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout( 1000 ,1800);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.pop_up_course_info_sssign, container,false);

        Log.d(TAG, "onCreate: Starting.");




        closeDialog = v.findViewById(R.id.closeDialog);
        playerTitle = v.findViewById(R.id.dialogTitle);
        courseName  = v.findViewById(R.id.courseName);
        courseDuration = v.findViewById(R.id.courseDuration);
        courseProvider = v.findViewById(R.id.courseProvider);
        assignButton = v.findViewById(R.id.assignButton);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        assignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    User user = (User) InternalStorage.readObject(getContext(),"User");
                    user.addCourses( course );
                    InternalStorage.writeObject(getContext(),"User",user);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        courseName.setText(course.getCourseName());
        courseDuration.setText(course.getCourseDuration());
        courseDuration.setText("You Tube");

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) v.findViewById(R.id.container);




        return v;
    }

    @Override
    public void onStart() {
        // full screen
        super.onStart();
        /*
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
        */
    }

    public void setSelection( Courses course ){
        this.course = course;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}
