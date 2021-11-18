package com.example.cafecompao.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafecompao.R;
import com.example.cafecompao.adapter.AdapterPlano;
import com.example.cafecompao.db.PlanoDAO;
import com.example.cafecompao.model.Plano;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaPlanosFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Plano> listaPlanos;
    private PlanoDAO dao;
    private Context context;
    OnPlanoPass dataPasser;

    public interface OnPlanoPass {
        public void onPlanoPass(Plano data);
    }

    public ListaPlanosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
        dataPasser = (OnPlanoPass) context;
    }

    public static ListaPlanosFragment newInstance() {
        ListaPlanosFragment f = new ListaPlanosFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.app_name) + " - " + getString(R.string.plano_fragment));

        recyclerView = view.findViewById(R.id.recyclerView);

        dao = new PlanoDAO(context);

        listaPlanos = dao.retornarTodos();

        // Configurar o AdapterInsumo
        AdapterPlano planoAdapter = new AdapterPlano(this.listaPlanos);

        planoAdapter.setCallback(new AdapterPlano.Callback() {
            @Override
            public void onSetSelected(Plano plano) {
                passPlano(plano);
            }
        });

        // Configurar RecyclerView layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayout.VERTICAL));
        recyclerView.setAdapter(planoAdapter);

    }

    public void passPlano(Plano data) {
        dataPasser.onPlanoPass(data);
    }

}
