package com.example.cafecompao.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.cafecompao.model.Plano;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("all")
public class PlanoDAO {
    private final String TABLE_PLANOS = "Planos";
    private DbGateway gw;

    public PlanoDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public long salvar(Plano plano){
        ContentValues cv = new ContentValues();
        cv.put("Nome", plano.getNome());
        cv.put("Valor", plano.getValor());
        cv.put("QtdComida", plano.getQtdComida());
        cv.put("QtdBebida", plano.getQtdBebida());


        if(plano.getID() != null) {
                    gw.getDatabase().update(TABLE_PLANOS, cv, "ID=?", new String[]{plano.getID() + ""});
            return plano.getID();
        } else {
            return gw.getDatabase().insert(TABLE_PLANOS, null, cv);
        }
    }
    public boolean excluir(Long id){
        return gw.getDatabase().delete(TABLE_PLANOS, "ID=?", new String[]{ id + "" }) > 0;
    }

    public boolean excluirTodos(){
        return gw.getDatabase().delete(TABLE_PLANOS, null, null) > 0;
    }

    public List<Plano> retornarTodos(){
        List<Plano> planos = new ArrayList<Plano>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Planos", null);
        while(cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            Double valor = cursor.getDouble(cursor.getColumnIndex("Valor"));
            Integer qtdComida = cursor.getInt(cursor.getColumnIndex("QtdComida"));
            Integer qtdBebida = cursor.getInt(cursor.getColumnIndex("QtdBebida"));

            planos.add(new Plano(id, nome, valor, qtdComida, qtdBebida));
        }
        cursor.close();
        return planos;
    }

    public Plano retornarPorID(Long ID){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Planos where ID = ?", new String[]{String.valueOf(ID)});
        if(cursor.moveToFirst()){
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            Double valor = cursor.getDouble(cursor.getColumnIndex("Valor"));
            Integer qtdComida = cursor.getInt(cursor.getColumnIndex("QtdComida"));
            Integer qtdBebida = cursor.getInt(cursor.getColumnIndex("QtdBebida"));

            cursor.close();
            return new Plano(id, nome, valor, qtdComida, qtdBebida);
        }

        return null;
    }

    public Plano retornarUltimo(){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Planos ORDER BY ID DESC", null);
        if(cursor.moveToFirst()){
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            Double valor = cursor.getDouble(cursor.getColumnIndex("Valor"));
            Integer qtdComida = cursor.getInt(cursor.getColumnIndex("QtdComida"));
            Integer qtdBebida = cursor.getInt(cursor.getColumnIndex("QtdBebida"));

            cursor.close();
            return new Plano(id, nome, valor, qtdComida, qtdBebida);
        }

        return null;
    }
}
