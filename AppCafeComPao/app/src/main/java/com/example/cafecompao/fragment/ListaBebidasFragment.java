package com.example.cafecompao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafecompao.R;
import com.example.cafecompao.model.Insumo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaBebidasFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Insumo> listaInsumos = new ArrayList<Insumo>();

    public ListaBebidasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bebidas, container, false);
    }

}
