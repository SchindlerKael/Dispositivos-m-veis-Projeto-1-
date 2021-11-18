package com.example.cafecompao.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.cafecompao.db.EnderecoDAO;
import com.example.cafecompao.db.ServicoDAO;
import com.example.cafecompao.db.UsuarioDAO;
import com.example.cafecompao.fragment.HorarioFragment;
import com.example.cafecompao.fragment.InsumoFragment;
import com.example.cafecompao.fragment.ListaInsumosFragment;
import com.example.cafecompao.fragment.ListaPlanosFragment;
import com.example.cafecompao.fragment.ResumoFragment;

import com.example.cafecompao.model.Endereco;
import com.example.cafecompao.model.Insumo;
import com.example.cafecompao.model.Plano;
import com.example.cafecompao.R;
import com.example.cafecompao.model.Servico;
import com.example.cafecompao.model.Usuario;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements
        ListaInsumosFragment.OnDataPass,
        ListaPlanosFragment.OnPlanoPass,
        HorarioFragment.OnHorarioPass,
        ResumoFragment.OnObservacaoPass {

    private UsuarioDAO usuarioDAO;
    private ServicoDAO servicoDAO;
    private Usuario usuario;
    private LocalTime horario;
    private String observacao;
    private List<Insumo> comidas = new ArrayList<Insumo>();
    private List<Insumo> bebidas = new ArrayList<Insumo>();
    private Plano plano;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioDAO = new UsuarioDAO(getBaseContext());
        servicoDAO = new ServicoDAO(getBaseContext());

        btn = findViewById(R.id.btnTeste);

        Servico servico = servicoDAO.retornarPorUsuario(getIntent().getLongExtra("userID", -1));

        if(servico != null){
            comidas = servico.getInsumos();
            usuario = servico.getUsuario();
            plano = servico.getPlano();
            horario = servico.getHorario();
            observacao = servico.getObservacao();

            btn.setVisibility(View.INVISIBLE);

            getFragment(ResumoFragment.class, false, ResumoFragment.arguments(
                    usuario.getNome(),
                    usuario.getEndereco().toString(),
                    plano.getNome(),
                    insumosToString(),
                    horario.format(DateTimeFormatter.ofPattern("HH:mm"))
            ));

        }else {
            usuario = usuarioDAO.retornarPorID(getIntent().getLongExtra("userID", -1));
            getFragment(ListaPlanosFragment.class, false, null);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String currentFragment = ((String) getSupportActionBar().getTitle()).split(" - ")[1];

                if(currentFragment.equals(getString(R.string.plano_fragment))){
                    getFragment(InsumoFragment.class, true, null);
                }else if(currentFragment.equals(getString(R.string.insumo_fragment))){
                    getFragment(HorarioFragment.class, true, null);
                }else if(currentFragment.equals(getString(R.string.horario_fragment))){
                    getFragment(ResumoFragment.class, true, ResumoFragment.arguments(
                            usuario.getNome(),
                            usuario.getEndereco().toString(),
                            plano.getNome(),
                            insumosToString(),
                            horario.format(DateTimeFormatter.ofPattern("HH:mm"))
                    ));
                }else if(currentFragment.equals(getString(R.string.resumo_fragment))){
                    salvar();
                }
            }
        });
    }

    private void getFragment( Class<? extends Fragment> f, Boolean isFirstInStack, Bundle args ) {
        if(isFirstInStack){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_teste, f, args)
                    .addToBackStack(null)
                    .commit();
        }else{
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_teste, f, args)
                    .commit();
        }
    }

    public Boolean salvar() {
        try {
            long id;
            boolean success;

            List<Insumo> insumos = new ArrayList<Insumo>();
            insumos.addAll(comidas);
            insumos.addAll(bebidas);

            Servico servico = new Servico(horario, observacao, usuario, plano, insumos);
            success = servicoDAO.salvar(servico) > 0;
            if(!success) {
                Toast.makeText(getBaseContext(), "Problemas ao salvar o Serviço!", Toast.LENGTH_SHORT).show();
                return false;
            }

            Toast.makeText(getBaseContext(), "Serviço salvo!", Toast.LENGTH_SHORT).show();
            return true;

        }catch (Exception e){
            Toast.makeText(getBaseContext(), "erro: "+e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public String insumosToString() {
        List<String> nomes = new ArrayList<String>();

        for(Insumo i : comidas){
            nomes.add(i.getNome());
        }
        for(Insumo i : bebidas){
            nomes.add(i.getNome());
        }

        return String.join(", ", nomes);
    }

    @Override
    public void onDataPass(List<Insumo> data, String insumoType) {
        switch (insumoType){
            case "C":
                comidas = data;
                break;
            case "B":
                bebidas = data;
                break;
        }
    }

    @Override
    public void onPlanoPass(Plano data) {
        plano = data;
    }

    @Override
    public void onHorarioPass(int hour, int min) {
        horario = LocalTime.of(hour, min);
    }

    @Override
    public void onObservacaoPass(String data) {
        Log.d("onObservacaoPass", data);
        observacao = data;
    }
}
