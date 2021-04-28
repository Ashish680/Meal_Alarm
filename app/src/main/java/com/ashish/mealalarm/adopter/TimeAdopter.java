package com.ashish.mealalarm.adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.mealalarm.R;
import com.ashish.mealalarm.databinding.TimeItemLayoutBinding;
import com.ashish.mealalarm.models.TimeModel;

import java.util.List;

/**
 * Created by Ashish Tiwari on 28,April,2021
 */
public class TimeAdopter extends RecyclerView.Adapter<TimeAdopter.ViewHolder> {
    private Context context;
    private final List<TimeModel> weekDietData;

    public TimeAdopter(Context context, List<TimeModel> customModelList) {
        this.context = context;
        this.weekDietData = customModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (weekDietData != null && weekDietData.get(position) != null) {
            holder.binding.setItem(weekDietData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return weekDietData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TimeItemLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
