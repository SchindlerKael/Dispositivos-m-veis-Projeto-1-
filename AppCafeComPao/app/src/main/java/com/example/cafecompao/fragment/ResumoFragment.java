package com.example.cafecompao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.cafecompao.R;
import com.example.cafecompao.db.ServicoDAO;
import com.example.cafecompao.model.Servico;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResumoFragment extends Fragment {

    EditText txtObservacao;
    TextView textUserName;
    TextView textUserEndereco;
    TextView textPlano;
    TextView textInsumos;
    TextView textHorario;

    private Context context;
    OnObservacaoPass dataPasser;

    public interface OnObservacaoPass {
        public void onObservacaoPass(String data);
    }

    public ResumoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
        dataPasser = (OnObservacaoPass) context;
    }

    public static ListaInsumosFragment newInstance(String nome, String endereco, String plano, String insumos, String horario) {
        ListaInsumosFragment f = new ListaInsumosFragment();
        f.setArguments(arguments(nome, endereco, plano, insumos, horario));
        return f;
    }

    public static Bundle arguments(String nome, String endereco, String plano, String insumos, String horario) {
        return new Bundler()
                .putString("nome", nome)
                .putString("endereco", endereco)
                .putString("plano", plano)
                .putString("insumos", insumos)
                .putString("horario", horario)
                .get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resumo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.app_name) + " - " + getString(R.string.resumo_fragment));

        txtObservacao = view.findViewById(R.id.editTextTextMultiLine);
        textUserName = view.findViewById(R.id.textUserName);
        textUserEndereco = view.findViewById(R.id.textUserEndereco);
        textPlano = view.findViewById(R.id.textPlano);
        textInsumos = view.findViewById(R.id.textInsumos);
        textHorario = view.findViewById(R.id.textHorario);

        textUserName.setText(getArguments().getString("nome"));
        textUserEndereco.setText(getArguments().getString("endereco"));
        textPlano.setText(getArguments().getString("plano"));
        textInsumos.setText(getArguments().getString("insumos"));
        textHorario.setText(getArguments().getString("horario"));

        txtObservacao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    passObservacao(txtObservacao.getText().toString());
            }
        });

    }

    public void passObservacao(String data) {
        dataPasser.onObservacaoPass(data);
    }


}
