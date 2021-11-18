package com.example.cafecompao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.cafecompao.R;
import com.example.cafecompao.db.EnderecoDAO;
import com.example.cafecompao.db.InsumoDAO;
import com.example.cafecompao.db.PlanoDAO;
import com.example.cafecompao.db.ServicoDAO;
import com.example.cafecompao.db.UsuarioDAO;
import com.example.cafecompao.model.Insumo;
import com.example.cafecompao.model.InsumoType;
import com.example.cafecompao.model.Plano;
import com.example.cafecompao.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnRegister;
    EditText editTextEmail;
    EditText editTextPassword;
    UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuarioDAO = new UsuarioDAO(getBaseContext());
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                Usuario user = usuarioDAO.autenticar(email, password);

                if(user != null) {
                    Toast.makeText(getBaseContext(), "Bem vindo "+user.getNome()+"!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("userID", user.getID());
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(), "Email ou Senha incorretos!", Toast.LENGTH_SHORT).show();
                    editTextEmail.setText("");
                    editTextPassword.setText("");
                    editTextEmail.requestFocus();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
