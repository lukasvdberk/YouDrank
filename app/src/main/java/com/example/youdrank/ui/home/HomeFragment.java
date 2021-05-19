package com.example.youdrank.ui.home;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.youdrank.R;
import com.example.youdrank.controllers.WaterIntakeController;
import com.example.youdrank.databinding.FragmentHomeBinding;
import com.example.youdrank.models.WaterIntake;
import com.example.youdrank.models.WaterIntakeOfToday;

import java.text.ParseException;
import java.util.Date;

public class HomeFragment extends Fragment {
    WaterIntakeController waterIntakeController;
    protected View mView;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mView = root;

        waterIntakeController = new WaterIntakeController(getContext());
        setupEventListeners();
        try {
            updateTotalWaterInTakeOfToday();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setupEventListeners() {
        Button add250mlBtn = mView.findViewById(R.id.add250ml_button);
        Button add500lBtn = mView.findViewById(R.id.add500ml_button);
        Button add1LBtn = mView.findViewById(R.id.add1l_button);
        Button customWaterInput = mView.findViewById(R.id.customWaterIntakeMLInputBtn);

        EditText editText = mView.findViewById(R.id.customWaterIntakeMLInput);

        add250mlBtn.setOnClickListener(v ->
                saveWaterIntake(250));
        add500lBtn.setOnClickListener(v -> saveWaterIntake(500));
        add1LBtn.setOnClickListener(v -> saveWaterIntake(1000));

        customWaterInput.setOnClickListener(v -> {
            try {
                int waterIntake = Integer.parseInt(editText.getText().toString());
                saveWaterIntake(waterIntake);
            } catch (Exception ignored) {
                showToast("You did not enter a valid numnber for water intake!");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveWaterIntake(int waterIntakeInMl) {
        WaterIntake waterIntake = new WaterIntake(waterIntakeInMl, new Date());
        waterIntakeController.addWaterIntake(waterIntake);

        showToast(String.format("Added %s ml to your water intake!", waterIntakeInMl));
        try {
            updateTotalWaterInTakeOfToday();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateTotalWaterInTakeOfToday() throws ParseException {
        WaterIntakeOfToday waterIntakeOfToday = waterIntakeController.getAllIntakesOfToday();
        WaterIntake totalWaterIntakeOfToday = waterIntakeOfToday.getTotalWaterIntakeOfToday();

        ProgressBar progressBar = mView.findViewById(R.id.waterIntakeProgress);

        progressBar.setMax(2000);
        progressBar.setProgress(totalWaterIntakeOfToday.getInTakeInMilliliter(), true);

        TextView waterIntakeText = mView.findViewById(R.id.waterIntake);
        waterIntakeText.setText(String.format("%d ML", totalWaterIntakeOfToday.getInTakeInMilliliter()));
    }

    private void showToast(String text) {
        Context context = getContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}