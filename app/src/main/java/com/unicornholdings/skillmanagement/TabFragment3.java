package com.unicornholdings.skillmanagement;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class TabFragment3 extends AppCompatDialogFragment {
    private RecyclerView m1RecyclerView;
    private SkillMatrixRecyclerViewAdapter m1Adapter;
    private RecyclerView.LayoutManager m1LayoutManager;
    private ArrayList<Courses> courses;
    private User user;
    private ArrayList<User> userList;




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
                inflate(R.layout.tab_fragment_3,
                        container,
                        false);                             // Varun: inflate the layout onCreate
        m1RecyclerView = v.findViewById(R.id.SkillMatrixRecyclerView);
        setUpArrayList();
        initRecyclerView();


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
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && this.getContext() != null) {

            setUpArrayList();
            initRecyclerView();
        }
    }

    private void setUpArrayList(){

        try {
             user  = (User) InternalStorage.readObject(this.getContext(),"User");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        userList = new ArrayList<>();
        userList.add(user);
    }

    private void initRecyclerView (){

        m1LayoutManager = new LinearLayoutManager(this.getActivity());
        m1Adapter = new SkillMatrixRecyclerViewAdapter(this.getActivity().getApplicationContext(), getActivity().getSupportFragmentManager() ,userList);

        m1RecyclerView.setLayoutManager(m1LayoutManager);
        m1RecyclerView.setHasFixedSize(true);
        m1RecyclerView.setAdapter(m1Adapter);
        m1Adapter.setOnItemClickListener(new SkillMatrixRecyclerViewAdapter.OnAddButtonClickListener() {

            @Override
            public void onPreviewButtonClick(Courses team) {

            }

            @Override
            public void onPreviewButtonClick(User user) {
                previewButtonClicked(user);

            }


        }) ;

    }

    private void previewButtonClicked(User team) {

    }
}


