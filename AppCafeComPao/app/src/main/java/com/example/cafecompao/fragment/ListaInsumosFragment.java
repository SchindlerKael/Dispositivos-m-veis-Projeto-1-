package com.example.cafecompao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafecompao.R;
import com.example.cafecompao.adapter.AdapterInsumo;
import com.example.cafecompao.model.Insumo;
import com.example.cafecompao.model.InsumoType;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaInsumosFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Insumo> listaInsumos = new ArrayList<Insumo>();

    public ListaInsumosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insumos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Context applicationContext = getActivity().getApplicationContext();

        recyclerView = view.findViewById(R.id.recyclerView);
        // Listagem de filmes (pode vir de arquivos, banco de dados etc.

        this.criarFilmes();
        // Configurar o AdapterFilme
        AdapterInsumo InsumoAdapterInsumo = new AdapterInsumo(this.listaInsumos);

        InsumoAdapterInsumo.setCallback(new AdapterInsumo.Callback() {
            @Override
            public Boolean onCheckedChanged(String item, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(applicationContext, item + " adicionado", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    Toast.makeText(applicationContext, item + " Removido", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }

            @Override
            public void sendAlert(){
                Toast.makeText(applicationContext, "Limite de itens atingido!", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar RecyclerView layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(applicationContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(applicationContext, LinearLayout.VERTICAL));
        recyclerView.setAdapter(InsumoAdapterInsumo);
    }

        public void criarFilmes() {
        Insumo novoInsumo = new Insumo("Pão Francês com Manteiga", null,  InsumoType.COMIDA);
        listaInsumos.add(novoInsumo);

        novoInsumo = new Insumo("Pão de Queijo", null, InsumoType.COMIDA);
        listaInsumos.add(novoInsumo);

        novoInsumo = new Insumo("Bolo","Limão, Cenoura, Laranja", InsumoType.COMIDA);
        listaInsumos.add(novoInsumo);

        novoInsumo = new Insumo("Fruta","Banana, Mamão, Maçã, Carambola", InsumoType.COMIDA);
        listaInsumos.add(novoInsumo);

        novoInsumo = new Insumo("Café", "Xicara 300ml", InsumoType.BEBIDA);
        listaInsumos.add(novoInsumo);

        novoInsumo = new Insumo("Leite","Copo 300ml", InsumoType.BEBIDA);
        listaInsumos.add(novoInsumo);

        novoInsumo = new Insumo("Suco de Frutas","Copo 300ml", InsumoType.BEBIDA);
        listaInsumos.add(novoInsumo);

    }

}
