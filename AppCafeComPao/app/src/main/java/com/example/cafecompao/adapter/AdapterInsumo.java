package com.example.cafecompao.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cafecompao.model.Insumo;
import com.example.cafecompao.R;
import com.example.cafecompao.validator.CheckBoxValidator;

import java.util.ArrayList;
import java.util.List;

public class AdapterInsumo extends RecyclerView.Adapter<AdapterInsumo.MyViewHolder> {
    private List<Insumo> listaInsumos;
    public List<Insumo> listaInsumosChecked;

    private CheckBoxValidator validator;

    private Callback callback;

    public AdapterInsumo(List<Insumo> lista) {
        listaInsumos = lista;
        listaInsumosChecked = new ArrayList<Insumo>();
        validator = CheckBoxValidator.getInstance();
    }

    public interface Callback {
        void onSetList(List<Insumo> lista);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        public TextView descricao;

        public MyViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBoxInsumo);
            descricao = itemView.findViewById(R.id.textDescricao);
        }
        void bind(Insumo insumo) {
            checkBox.setText(insumo.getNome());
            if(insumo.getDescricao() != null)
                descricao.setText("("+insumo.getDescricao()+")");
            else
                descricao.setVisibility(View.GONE);

            // Listen to changes (i.e. when the user checks or unchecks the box)
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(validator.isFull() && isChecked){
                        Toast.makeText(itemView.getContext(), "Limite de itens atingido!", Toast.LENGTH_SHORT).show();
                        checkBox.setChecked(false);
                    }else {
                        validator.validate(isChecked);
                        if(isChecked) {
                            listaInsumosChecked.add(insumo);
                            Toast.makeText(itemView.getContext(), insumo.getNome() + " Adicionado", Toast.LENGTH_SHORT).show();
                        }else {
                            listaInsumosChecked.remove(insumo);
                            Toast.makeText(itemView.getContext(), insumo.getNome() + " Removido", Toast.LENGTH_SHORT).show();
                        }
                        callback.onSetList(listaInsumosChecked);
                    }
                };

            });
        }
    }

    // Cria a visualizacao dos itens na tela
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista_insumo,parent,false);

        return new MyViewHolder(itemLista);
    }

    // Popula os objetos view com os dados
    @Override
    public void onBindViewHolder(AdapterInsumo.MyViewHolder holder, int position) {
        if (listaInsumos != null)
            holder.bind(listaInsumos.get(position));
    }

    // Quantidade de exibicoes do ViewHolder (dados) na tela
    @Override
    public int getItemCount() {
        return listaInsumos.size();
    }
}
