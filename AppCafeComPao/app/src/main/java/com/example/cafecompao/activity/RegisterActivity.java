package com.example.cafecompao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafecompao.R;
import com.example.cafecompao.db.EnderecoDAO;
import com.example.cafecompao.db.UsuarioDAO;
import com.example.cafecompao.model.Endereco;
import com.example.cafecompao.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    Spinner spnUf;
    EditText txtNameRegistration;
    EditText txtEmailRegistration;
    EditText txtPasswordRegistration;
    EditText txtPasswordConfirmation;
    EditText txtRua;
    EditText txtNumero;
    EditText txtComplemento;
    EditText txtBairro;
    EditText txtCidade;
    Button btnSalvar;
    EnderecoDAO enderecoDAO;
    UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spnUf = (Spinner) findViewById(R.id.spnUf);
        this.handleSpinnerAdapter(spnUf);
        txtNameRegistration = (EditText) findViewById(R.id.txtNameRegistration);
        txtEmailRegistration = (EditText) findViewById(R.id.txtEmailRegistration);
        txtPasswordRegistration = (EditText) findViewById(R.id.txtPasswordRegistration);
        txtPasswordConfirmation = (EditText) findViewById(R.id.txtPasswordConfirmation);
        txtRua = (EditText) findViewById(R.id.txtRua);
        txtNumero = (EditText) findViewById(R.id.txtNumero);
        txtComplemento = (EditText) findViewById(R.id.txtComplemento);
        txtBairro = (EditText) findViewById(R.id.txtBairro);
        txtCidade = (EditText) findViewById(R.id.txtCidade);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean success = save();
                if(success) {
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    protected Boolean save() {
        try {
            enderecoDAO = new EnderecoDAO(getBaseContext());
            usuarioDAO = new UsuarioDAO(getBaseContext());

            long id;
            boolean success;

            String name = txtNameRegistration.getText().toString();
            String email = txtEmailRegistration.getText().toString();
            String password = txtPasswordRegistration.getText().toString();
            String passwordConfirmation = txtPasswordConfirmation.getText().toString();
            String rua = txtRua.getText().toString();
            String numero = txtNumero.getText().toString();
            String complemento = txtComplemento.getText().toString();
            String bairro = txtBairro.getText().toString();
            String cidade = txtCidade.getText().toString();
            String uf = spnUf.getSelectedItem().toString();

            if(!password.equals(passwordConfirmation)) {
                Toast.makeText(getBaseContext(), "Senhas Não Conferem!", Toast.LENGTH_SHORT).show();
                return false;
            }

            if(usuarioDAO.retornarPorEmail(email) != null) {
                Toast.makeText(getBaseContext(), "E-mail já cadastrado!", Toast.LENGTH_SHORT).show();
                return false;
            }

            Endereco endereco = new Endereco(cidade, uf, bairro, rua, numero, complemento);
            id = enderecoDAO.salvar(endereco);
            success = id > 0;
            if(!success) {
                Toast.makeText(getBaseContext(), "Problemas ao salvar o Endereço!", Toast.LENGTH_SHORT).show();
                return false;
            }

            endereco.setID(id);

            Usuario user = new Usuario(name, email, password, endereco);
            success = usuarioDAO.salvar(user) > 0;
            if(!success) {
                Toast.makeText(getBaseContext(), "Problemas ao salvar o Usuário!", Toast.LENGTH_SHORT).show();
                return false;
            }

            Toast.makeText(getBaseContext(), "Usuário salvo!", Toast.LENGTH_SHORT).show();
            return true;

        }catch (Exception e){
            Toast.makeText(getBaseContext(), "erro: "+e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    protected void handleSpinnerAdapter(Spinner spnUf) {
        List<String> spinnerArray = this.PopulateSpinner();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnUf.setAdapter(adapter);
    }

    protected List<String> PopulateSpinner() {
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("RO");
        spinnerArray.add("AC");
        spinnerArray.add("AM");
        spinnerArray.add("RR");
        spinnerArray.add("PA");
        spinnerArray.add("AP");
        spinnerArray.add("TO");
        spinnerArray.add("MA");
        spinnerArray.add("PI");
        spinnerArray.add("CE");
        spinnerArray.add("RN");
        spinnerArray.add("PB");
        spinnerArray.add("PE");
        spinnerArray.add("AL");
        spinnerArray.add("SE");
        spinnerArray.add("BA");
        spinnerArray.add("MG");
        spinnerArray.add("ES");
        spinnerArray.add("RJ");
        spinnerArray.add("SP");
        spinnerArray.add("PR");
        spinnerArray.add("SC");
        spinnerArray.add("RS");
        spinnerArray.add("MS");
        spinnerArray.add("MT");
        spinnerArray.add("GO");
        spinnerArray.add("DF");

        return spinnerArray;
    }

}
