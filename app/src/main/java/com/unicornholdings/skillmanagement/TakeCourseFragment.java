package com.unicornholdings.skillmanagement;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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



public class TakeCourseFragment extends AppCompatDialogFragment {

    private TextView playerTitle;
    private ImageView closeDialog;
    private TextView courseName;
    private TextView courseDuration;
    private TextView courseProvider;
    private Button takeTestButton;

    private Button takeCourseButton;
    private static final String TAG = "Player Info";


    private ViewPager mViewPager;
    private Courses course;
    private User user;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return super.onCreateDialog(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(1400 ,1800);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.pop_up_course_action, container,false);

        Log.d(TAG, "onCreate: Starting.");




        closeDialog = v.findViewById(R.id.closeDialog);
        playerTitle = v.findViewById(R.id.dialogTitle);
        courseName  = v.findViewById(R.id.courseName);
        courseDuration = v.findViewById(R.id.courseDuration);
        courseProvider = v.findViewById(R.id.courseProvider);
        takeCourseButton = v.findViewById(R.id.takeCourseButton);
        takeTestButton = v.findViewById(R.id.assignButton);

        takeCourseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(course.getURL()));
                startActivity(browserIntent);
            }
        });
        takeTestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TakeTestFragment playerInfoDialogFragment = new TakeTestFragment();
                playerInfoDialogFragment.setSelection( user);
                playerInfoDialogFragment.show(getFragmentManager(),"info pop up");
            }
        });
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        courseProvider.setText(course.getCourseName());
        courseDuration.setText(course.getCourseDuration());

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

    public void setSelection(Courses course, User user){
        this.course = course;
        this.user   = user;
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
