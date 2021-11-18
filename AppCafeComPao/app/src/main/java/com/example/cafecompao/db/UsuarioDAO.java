package com.example.cafecompao.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.cafecompao.model.Endereco;
import com.example.cafecompao.model.Plano;
import com.example.cafecompao.model.Usuario;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("all")
public class UsuarioDAO {
    private final String TABLE_USUARIOS = "Usuarios";
    private DbGateway gw;
    private EnderecoDAO enderecoDAO;

    public UsuarioDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
        enderecoDAO = new EnderecoDAO(ctx);
    }

    public Usuario autenticar(String email, String password) {
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Usuarios where Email = ?", new String[]{String.valueOf(email)});
        Usuario auth = null;
        while(cursor.moveToNext()){
            String senha = cursor.getString(cursor.getColumnIndex("Senha"));
            if(password.equals(senha)){
                Long id = cursor.getLong(cursor.getColumnIndex("ID"));
                String nome = cursor.getString(cursor.getColumnIndex("Nome"));

                auth = new Usuario(id, nome, email, senha, null);
            }
        }
        cursor.close();
        return auth;
    }

    public long salvar(Usuario usuario){
        ContentValues cv = new ContentValues();
        cv.put("Nome", usuario.getNome());
        cv.put("Email", usuario.getEmail());
        cv.put("Senha", usuario.getSenha());
        cv.put("EnderecoID", usuario.getEndereco().getID());

        if(usuario.getID() != null) {
            gw.getDatabase().update(TABLE_USUARIOS, cv, "ID=?", new String[]{usuario.getID() + ""});
            return usuario.getID();
        } else {
            return gw.getDatabase().insert(TABLE_USUARIOS, null, cv);
        }
    }
    public boolean excluir(Long id){
        return gw.getDatabase().delete(TABLE_USUARIOS, "ID=?", new String[]{ id + "" }) > 0;
    }

    public boolean excluirTodos(){
        return gw.getDatabase().delete(TABLE_USUARIOS, null, null) > 0;
    }

    public List<Usuario> retornarTodos(){
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Usuarios", null);
        while(cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String email = cursor.getString(cursor.getColumnIndex("Email"));
            String senha = cursor.getString(cursor.getColumnIndex("Senha"));

            usuarios.add(new Usuario(nome, email, senha, null));
        }
        cursor.close();
        return usuarios;
    }

    public Usuario retornarPorID(Long ID){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Usuarios where ID = ?", new String[]{String.valueOf(ID)});
        if(cursor.moveToFirst()){
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String email = cursor.getString(cursor.getColumnIndex("Email"));
            String senha = cursor.getString(cursor.getColumnIndex("Senha"));
            Endereco endereco = enderecoDAO.retornarPorID(cursor.getLong(cursor.getColumnIndex("EnderecoID")));
            cursor.close();
            return new Usuario(id, nome, email, senha, endereco);
        }

        return null;
    }

    public Usuario retornarPorEmail(String param){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Usuarios where Email = ?", new String[]{String.valueOf(param)});
        if(cursor.moveToFirst()){
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String email = cursor.getString(cursor.getColumnIndex("Email"));
            String senha = cursor.getString(cursor.getColumnIndex("Senha"));
            Endereco endereco = enderecoDAO.retornarPorID(cursor.getLong(cursor.getColumnIndex("EnderecoID")));
            cursor.close();
            return new Usuario(nome, email, senha, endereco);
        }

        return null;
    }

    public Usuario retornarUltimo(){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Usuarios ORDER BY ID DESC", null);
        if(cursor.moveToFirst()){
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String email = cursor.getString(cursor.getColumnIndex("Email"));
            String senha = cursor.getString(cursor.getColumnIndex("Senha"));
            Endereco endereco = enderecoDAO.retornarPorID(cursor.getLong(cursor.getColumnIndex("EnderecoID")));
            cursor.close();
            return new Usuario(nome, email, senha, endereco);
        }

        return null;
    }

}
