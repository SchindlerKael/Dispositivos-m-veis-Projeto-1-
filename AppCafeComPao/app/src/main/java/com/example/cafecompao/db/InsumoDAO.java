package com.example.cafecompao.db;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.example.cafecompao.model.Insumo;
import com.example.cafecompao.model.InsumoType;

import java.util.ArrayList;
import java.util.List;
@SuppressLint("all")
public class InsumoDAO {

    private final String TABLE_INSUMOS = "Insumos";
    private DbGateway gw;

    public InsumoDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public boolean salvar(Insumo insumo){
        ContentValues cv = new ContentValues();
        cv.put("Nome", insumo.getNome());
        cv.put("Descricao", insumo.getDescricao());
        cv.put("Tipo", insumo.getTipo().getDescricao());
        if(insumo.getID() != null) {
            return gw.getDatabase().update(TABLE_INSUMOS, cv, "ID=?", new String[]{insumo.getID() + ""}) > 0;
        } else {
            return gw.getDatabase().insert(TABLE_INSUMOS, null, cv) > 0;
        }
    }
    public boolean excluir(Long id){
        return gw.getDatabase().delete(TABLE_INSUMOS, "ID=?", new String[]{ id + "" }) > 0;
    }

    public List<Insumo> retornarTodos(){
        List<Insumo> insumos = new ArrayList<Insumo>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Insumos", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String descricao = cursor.getString(cursor.getColumnIndex("Descricao"));
            String tipo = cursor.getString(cursor.getColumnIndex("Tipo"));
            insumos.add(new Insumo(nome, descricao, tipo.equals('C') ? InsumoType.COMIDA : InsumoType.BEBIDA));
        }
        cursor.close();
        return insumos;
    }

    public List<Insumo> retornarPorTipo(String param){
        List<Insumo> insumos = new ArrayList<Insumo>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Insumos where Tipo = ?",new String[]{param});
        while(cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String descricao = cursor.getString(cursor.getColumnIndex("Descricao"));
            String tipo = cursor.getString(cursor.getColumnIndex("Tipo"));
            insumos.add(new Insumo(id, nome, descricao, tipo.equals('C') ? InsumoType.COMIDA : InsumoType.BEBIDA));
        }
        cursor.close();
        return insumos;
    }

    public List<Insumo> retornarPorServico(Long servicoID){
        List<Insumo> insumos = new ArrayList<Insumo>();
        Cursor cursor = gw.getDatabase().rawQuery(
                "SELECT Insumos.* FROM Insumos LEFT JOIN ServicosInsumos ON Insumos.ID = ServicosInsumos.InsumoID WHERE ServicosInsumos.ServicoID = ?",
                new String[]{servicoID.toString()});
        while(cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String descricao = cursor.getString(cursor.getColumnIndex("Descricao"));
            String tipo = cursor.getString(cursor.getColumnIndex("Tipo"));
            insumos.add(new Insumo(id, nome, descricao, tipo.equals('C') ? InsumoType.COMIDA : InsumoType.BEBIDA));
        }
        cursor.close();
        return insumos;
    }

    public Insumo retornarUltimo(){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Insumos ORDER BY ID DESC", null);
        if(cursor.moveToFirst()){
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String descricao = cursor.getString(cursor.getColumnIndex("Descricao"));
            String tipo = cursor.getString(cursor.getColumnIndex("Tipo"));
            cursor.close();
            return new Insumo(nome, descricao, tipo.equals('C') ? InsumoType.COMIDA : InsumoType.BEBIDA);
        }

        return null;
    }

}
