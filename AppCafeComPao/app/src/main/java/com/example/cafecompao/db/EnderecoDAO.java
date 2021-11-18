package com.example.cafecompao.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.cafecompao.model.Endereco;
import com.example.cafecompao.model.Usuario;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("all")

public class EnderecoDAO {
    private final String TABLE_ENDERECOS = "Enderecos";
    private DbGateway gw;

    public EnderecoDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public long salvar(Endereco endereco){
        ContentValues cv = new ContentValues();
        cv.put("Cidade", endereco.getCidade());
        cv.put("UF", endereco.getUf());
        cv.put("Bairro", endereco.getBairro());
        cv.put("Rua", endereco.getRua());
        cv.put("Numero", endereco.getNumero());
        cv.put("Complemento", endereco.getComplemento());

        if(endereco.getID() != null) {
            gw.getDatabase().update(TABLE_ENDERECOS, cv, "ID=?", new String[]{endereco.getID() + ""});
            return endereco.getID();
        } else {
            return gw.getDatabase().insert(TABLE_ENDERECOS, null, cv);
        }
    }
    public boolean excluir(Long id){
        return gw.getDatabase().delete(TABLE_ENDERECOS, "ID=?", new String[]{ id + "" }) > 0;
    }

    public boolean excluirTodos(){
        return gw.getDatabase().delete(TABLE_ENDERECOS, null, null) > 0;
    }

    public List<Endereco> retornarTodos(){
        List<Endereco> enderecos = new ArrayList<Endereco>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Enderecos", null);
        while(cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            String cidade = cursor.getString(cursor.getColumnIndex("Cidade"));
            String uf = cursor.getString(cursor.getColumnIndex("UF"));
            String bairro = cursor.getString(cursor.getColumnIndex("Bairro"));
            String rua = cursor.getString(cursor.getColumnIndex("Rua"));
            String numero = cursor.getString(cursor.getColumnIndex("Numero"));
            String complemento = cursor.getString(cursor.getColumnIndex("Complemento"));

            enderecos.add(new Endereco(id, cidade, uf, bairro, rua, numero, complemento));
        }
        cursor.close();
        return enderecos;
    }

    public Endereco retornarPorID(Long ID){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Enderecos where ID = ?", new String[]{String.valueOf(ID)});
        if(cursor.moveToFirst()){
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            String cidade = cursor.getString(cursor.getColumnIndex("Cidade"));
            String uf = cursor.getString(cursor.getColumnIndex("UF"));
            String bairro = cursor.getString(cursor.getColumnIndex("Bairro"));
            String rua = cursor.getString(cursor.getColumnIndex("Rua"));
            String numero = cursor.getString(cursor.getColumnIndex("Numero"));
            String complemento = cursor.getString(cursor.getColumnIndex("Complemento"));

            cursor.close();
            return new Endereco(id, cidade, uf, bairro, rua, numero, complemento);
        }

        return null;
    }

    public Endereco retornarUltimo(){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Enderecos ORDER BY ID DESC", null);
        if(cursor.moveToFirst()){
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            String cidade = cursor.getString(cursor.getColumnIndex("Cidade"));
            String uf = cursor.getString(cursor.getColumnIndex("UF"));
            String bairro = cursor.getString(cursor.getColumnIndex("Bairro"));
            String rua = cursor.getString(cursor.getColumnIndex("Rua"));
            String numero = cursor.getString(cursor.getColumnIndex("Numero"));
            String complemento = cursor.getString(cursor.getColumnIndex("Complemento"));

            cursor.close();
            return new Endereco(id, cidade, uf, bairro, rua, numero, complemento);
        }

        return null;
    }
}
