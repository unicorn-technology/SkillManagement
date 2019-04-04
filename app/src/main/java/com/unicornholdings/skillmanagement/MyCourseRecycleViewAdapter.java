package com.unicornholdings.skillmanagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class MyCourseRecycleViewAdapter extends RecyclerView.Adapter<MyCourseRecycleViewAdapter.ContestViewHolder> {



    // to the Player Card Class which i have just created for temporary purposes.
    private Context mContext;
    private OnAddButtonClickListener mListener;


    public interface OnAddButtonClickListener {
        void onPreviewButtonClick(Courses team);


    }
    private ArrayList<Courses> teams;

    public void setOnItemClickListener (OnAddButtonClickListener listener){
        mListener = listener;
    }


    public class ContestViewHolder extends RecyclerView.ViewHolder
    {

        public TextView courseName, duration;
        public Button detailsButton;

        public ContestViewHolder(@NonNull View itemView, final OnAddButtonClickListener listener) {
            super(itemView);
            Log.d("debug", "object of PlayerViewHolder class created");
            courseName     = itemView.findViewById(R.id.courseName);
            duration      = itemView.findViewById(R.id.duration);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onPreviewButtonClick(teams.get(position));
                        }
                    }
                }
            });

            //playerPosition   = itemView.findViewById(R.id.playerPositionText);




        }
    }



    public MyCourseRecycleViewAdapter(Context context, FragmentManager supportFragmentManager, ArrayList<Courses> teamNames){
        mContext = context;
        this.teams = teamNames;
    }

    /*
        Varun: OnCreateViewHolder is the method which is responsible for inflating the view.

     */
    @NonNull
    @Override
    public ContestViewHolder onCreateViewHolder
    (@NonNull ViewGroup parent, int i) {
        Log.d("debug", "onCreateViewHolder: called");
        View view = LayoutInflater.
                from(parent.getContext()).
                inflate(R.
                                layout.
                                card_in_course_assigned,
                        parent,
                        false);
        ContestViewHolder holder = new ContestViewHolder(view, mListener);                           // Creating an object of the PlayerViewHolder class
        return holder;                                                                  // That class is returned when the onCreateView is called.
    }

    @Override
    public void onBindViewHolder(@NonNull ContestViewHolder holder, int position) {
        Log.d("debug", "onBindViewHolder: called");
        Log.d("number of Players", String.valueOf(position));
        if (position < teams.size()) {
            setVisible(holder);
            Courses course = teams.get(position);
            holder.courseName.setText(course.getCourseName());
            holder.duration.setText(String.valueOf(course.getCourseDuration()));//currentPlayer.getTeam().getTeamName());

        }
    }




    private void setVisible(ContestViewHolder holder) {
        holder.courseName.setAlpha(1);
        holder.duration.setAlpha(1);

    }





    @Override
    public int getItemCount() {

        /*
            Varun: this tells the adapter how many list items are in your adapter.
            If we left this as return 0, nothing would happen.

         */
        return teams.size();
    }
}