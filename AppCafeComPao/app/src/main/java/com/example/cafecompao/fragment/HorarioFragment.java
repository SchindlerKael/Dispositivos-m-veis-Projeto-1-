package com.example.cafecompao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafecompao.R;
import com.example.cafecompao.model.Insumo;
import com.example.cafecompao.model.Plano;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HorarioFragment extends Fragment {

    private TimePicker picker;
    private Context context;
    OnHorarioPass dataPasser;

    public interface OnHorarioPass {
        public void onHorarioPass(int hour, int min);
    }

    public HorarioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
        dataPasser = (OnHorarioPass) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_horario, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.app_name) + " - " + getString(R.string.horario_fragment));

        picker = (TimePicker) view.findViewById(R.id.timePicker);
        picker.setIs24HourView(true);

        getTime();

        picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                getTime();
            }
        });

    }

    public void getTime(){
        int hour, min;
        hour = picker.getCurrentHour();
        min = picker.getCurrentMinute();
        passHorario(hour, min);
    }

    public void passHorario(int hour, int min) {
        dataPasser.onHorarioPass(hour, min);
    }

}
