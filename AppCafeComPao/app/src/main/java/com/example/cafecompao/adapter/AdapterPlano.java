package com.example.cafecompao.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cafecompao.R;
import com.example.cafecompao.model.Insumo;
import com.example.cafecompao.model.Plano;
import com.example.cafecompao.validator.CheckBoxValidator;

import java.util.ArrayList;
import java.util.List;

public class AdapterPlano extends RecyclerView.Adapter<AdapterPlano.MyViewHolder> {
    private List<Plano> listaPlanos;
    public int checkedPosition;

    private Callback callback;

    public AdapterPlano(List<Plano> lista) {
        listaPlanos = lista;
        checkedPosition = -1;
    }

    public interface Callback {
        void onSetSelected(Plano plano);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textPlanoName;
        public TextView textPlanoValor;
        public TextView textPlanoQtdBebidas;
        public TextView textPlanoQtdComidas;
        public RadioButton myRadioButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            textPlanoName = itemView.findViewById(R.id.textPlanoName);
            textPlanoValor = itemView.findViewById(R.id.textPlanoValor);
            textPlanoQtdBebidas = itemView.findViewById(R.id.textPlanoQtdBebidas);
            textPlanoQtdComidas = itemView.findViewById(R.id.textPlanoQtdComidas);
            myRadioButton = itemView.findViewById(R.id.radioButton);
        }

        void bind(Plano plano, int position) {
            textPlanoName.setText(plano.getNome());
            textPlanoValor.setText("R$ "+plano.getValor().toString());
            textPlanoQtdBebidas.setText(plano.getQtdBebida().toString()+" Opções de bebida(s)");
            textPlanoQtdComidas.setText(plano.getQtdComida().toString()+" Opções de comida(s)");
            myRadioButton.setChecked(position == checkedPosition);

            myRadioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(position == checkedPosition) {
                        myRadioButton.setChecked(false);
                        checkedPosition = -1;
                    } else {
                        checkedPosition = position;
                        notifyDataSetChanged();
                        callback.onSetSelected(plano);
                    }
                }
            });
        }
    }

    // Cria a visualizacao dos itens na tela
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista_plano,parent,false);

        return new MyViewHolder(itemLista);
    }

    // Popula os objetos view com os dados
    @Override
    public void onBindViewHolder(AdapterPlano.MyViewHolder holder, int position) {
        if (listaPlanos != null)
            holder.bind(listaPlanos.get(position), position);
    }

    // Quantidade de exibicoes do ViewHolder (dados) na tela
    @Override
    public int getItemCount() {
        return listaPlanos.size();
    }
}
