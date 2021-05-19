package com.example.youdrank.ui.settings;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.youdrank.R;
import com.example.youdrank.controllers.WaterIntakeController;
import com.example.youdrank.databinding.FragmentHomeBinding;
import com.example.youdrank.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {
    WaterIntakeController waterIntakeController;
    protected View mView;
    private SettingsViewModel settingsViewModel;
    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mView = root;

        waterIntakeController = new WaterIntakeController(getContext());

        return root;
    }

    public void setupListener() {
        Button waterIntakeBtn = mView.findViewById(R.id.save_settings);
        waterIntakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText dailyWaterIntakeSetting = mView.findViewById(R.id.water_intake_per_day);
                    int waterIntake = Integer.parseInt(dailyWaterIntakeSetting.getText().toString());
                } catch (Exception ignored) {
                    showToast("You did not enter a valid number for water intake!");
                }
            }
        });
    }

    private void saveSettings(int waterIntakePerDay) {

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