package com.unicornholdings.skillmanagement;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@SuppressLint("ValidFragment")
public class TabFragment2 extends AppCompatDialogFragment {
    private RecyclerView m1RecyclerView;
    private MyCourseRecycleViewAdapter m1Adapter;
    private RecyclerView.LayoutManager m1LayoutManager;
    private ArrayList<Courses> courses;
    private User user;


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }

    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState)
    {
        View v = inflater.
                inflate(R.layout.tab_fragment_1,
                        container,
                        false);                             // Varun: inflate the layout onCreate
        m1RecyclerView = v.findViewById(R.id.CourseAssignedRecyclerView);
        setUpArrayList();
        initRecyclerView();
        user = new User( "Test User",  0);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);



    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void setUpArrayList(){


        try {
          User  user = (User) InternalStorage.readObject(this.getContext(),"User");
          this.courses = user.getCourses();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && this.getContext() != null) {

            setUpArrayList();
            initRecyclerView();
        }
    }
    private void initRecyclerView (){

        m1LayoutManager = new LinearLayoutManager(this.getActivity());
        m1Adapter = new MyCourseRecycleViewAdapter(this.getActivity().getApplicationContext(), getActivity().getSupportFragmentManager() ,courses);

        m1RecyclerView.setLayoutManager(m1LayoutManager);
        m1RecyclerView.setHasFixedSize(true);
        m1RecyclerView.setAdapter(m1Adapter);
        m1Adapter.setOnItemClickListener(new MyCourseRecycleViewAdapter.OnAddButtonClickListener() {
            @Override
            public void onPreviewButtonClick(Courses team) {
                previewButtonClicked(team);
            }


        }) ;

    }

    private void previewButtonClicked(Courses team) {
        TakeCourseFragment playerInfoDialogFragment = new TakeCourseFragment();
        playerInfoDialogFragment.setSelection(team,user);
        playerInfoDialogFragment.show(getFragmentManager(),"info pop up");
    }
}
