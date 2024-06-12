package com.example.takeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterHabit extends RecyclerView.Adapter<RecycleViewHolder> {

    Context context;
    List<HabitHolderRecycle> habits;

    public AdapterHabit(Context context, List<HabitHolderRecycle> habits) {
        this.context = context;
        this.habits = habits;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecycleViewHolder(LayoutInflater.from(context).inflate(R.layout.recycle_container,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        holder.habitView.setText(habits.get(position).getHabit());
        holder.habitButton.setImageResource(habits.get(position).getImageHabit());
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }
}
    class RecycleViewHolder extends RecyclerView.ViewHolder {

    ImageButton habitButton;
    TextView habitView;
    public RecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        habitButton = itemView.findViewById(R.id.habit_icon);
        habitView = itemView.findViewById(R.id.habit_text);
    }
}
