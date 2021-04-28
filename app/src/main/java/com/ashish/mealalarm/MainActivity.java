package com.ashish.mealalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import com.ashish.mealalarm.adopter.DayAdopter;
import com.ashish.mealalarm.databinding.ActivityMainBinding;
import com.ashish.mealalarm.models.CustomModel;
import com.ashish.mealalarm.models.DietResponseModel;
import com.ashish.mealalarm.models.TimeModel;
import com.ashish.mealalarm.retrofit.RestServices;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ashish Tiwari on 28,April,2021
 */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(() -> runOnUiThread(this::getAPIResponse), 2000);
    }

    private void getAPIResponse() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final Call<DietResponseModel> call = RestServices.API_SERVICE.dummyApi();
        Response<DietResponseModel> response = null;
        try {
            response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                binding.progress.setVisibility(View.GONE);
                DietResponseModel diet = response.body();
                Log.e("response", response.isSuccessful() + " " + diet);
                setData(diet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setData(DietResponseModel diet) {
        if (diet != null) {
            Map<String, List<TimeModel>> gfg = diet.getDaySlot();
            List<CustomModel> customModelList = new ArrayList<>();
            for (Map.Entry<String, List<TimeModel>> entry : gfg.entrySet()) {
                CustomModel model = new CustomModel();
                model.setDay(entry.getKey());
                model.setTimeList(entry.getValue());
                customModelList.add(model);
            }
            DayAdopter adaptor = new DayAdopter(this, customModelList);
            binding.recyclerView.setAdapter(adaptor);
            methodAlarmSchedule(customModelList);
        }

    }

    private void methodAlarmSchedule(List<CustomModel> customModelList) {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            for (CustomModel model : customModelList) {
                for (TimeModel timeModel : model.getTimeList()) {
                    int day = calculateDay(model.getDay());
                    methodSetAlarm(day, timeModel);
                }
            }
        }, 1000);
    }

    private void methodSetAlarm(int day, TimeModel timeModel) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("HH:mm");
        DateTime jodatime = dtf.parseDateTime(timeModel.getMealTime());

        Intent intent = new Intent(this, SetAlarmService.class);
        Bundle bundle = new Bundle();
        bundle.putInt(constant.DAY, day);
        bundle.putInt(constant.HOUR, jodatime.hourOfDay().get());
        bundle.putInt(constant.MINUTE, jodatime.minuteOfHour().get());
        bundle.putString(constant.DESC, timeModel.getMealTime() + " " + timeModel.getFood());
        intent.putExtras(bundle);
        startService(intent);
    }

    private int calculateDay(String day) {
        if (day.equalsIgnoreCase(DayOfWeek.sunday.name())) {
            return Calendar.SUNDAY;
        } else if (day.equalsIgnoreCase(DayOfWeek.monday.name())) {
            return Calendar.MONDAY;
        } else if (day.equalsIgnoreCase(DayOfWeek.tuesday.name())) {
            return Calendar.TUESDAY;
        } else if (day.equalsIgnoreCase(DayOfWeek.wednesday.name())) {
            return Calendar.WEDNESDAY;
        } else if (day.equalsIgnoreCase(DayOfWeek.thursday.name())) {
            return Calendar.THURSDAY;
        } else if (day.equalsIgnoreCase(DayOfWeek.friday.name())) {
            return Calendar.FRIDAY;
        } else if (day.equalsIgnoreCase(DayOfWeek.saturday.name())) {
            return Calendar.SATURDAY;
        } else
            return 0;
    }
}