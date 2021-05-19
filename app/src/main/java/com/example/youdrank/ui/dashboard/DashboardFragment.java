package com.example.youdrank.ui.dashboard;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.youdrank.R;
import com.example.youdrank.controllers.WaterIntakeController;
import com.example.youdrank.databinding.FragmentDashboardBinding;
import com.example.youdrank.models.WaterIntake;

import java.text.ParseException;
import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    private WaterIntakeController waterIntakeController;

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        waterIntakeController = new WaterIntakeController(getContext());
        try {
            getAllWaterIntakes();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return root;
    }

    public ArrayList<WaterIntake> getAllWaterIntakes() throws ParseException {
        return waterIntakeController.getAllWaterIntake();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}