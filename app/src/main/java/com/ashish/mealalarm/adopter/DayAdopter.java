package com.ashish.mealalarm.adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.mealalarm.R;
import com.ashish.mealalarm.databinding.DayItemLayoutBinding;
import com.ashish.mealalarm.models.CustomModel;

import java.util.List;

/**
 * Created by Ashish Tiwari on 28,April,2021
 */
public class DayAdopter extends RecyclerView.Adapter<DayAdopter.ViewHolder> {
    private final Context context;
    private final List<CustomModel> weekDietData;

    public DayAdopter(Context context, List<CustomModel> customModelList) {
        this.context = context;
        this.weekDietData = customModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (weekDietData != null && weekDietData.get(position) != null) {
            holder.binding.setItem(weekDietData.get(position).getDay());
            TimeAdopter adaptor = new TimeAdopter(context, weekDietData.get(position).getTimeList());
            holder.binding.recyclerViewTime.setAdapter(adaptor);
        }
    }

    @Override
    public int getItemCount() {
        return weekDietData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public DayItemLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
