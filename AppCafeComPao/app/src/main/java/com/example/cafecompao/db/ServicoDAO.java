package com.example.cafecompao.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.cafecompao.model.Insumo;
import com.example.cafecompao.model.InsumoType;
import com.example.cafecompao.model.Plano;
import com.example.cafecompao.model.Servico;
import com.example.cafecompao.model.Usuario;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ServicoDAO {
    private final String TABLE_SERVICOS = "Servicos";
    private DbGateway gw;
    private UsuarioDAO usuarioDAO;
    private PlanoDAO planoDAO;
    private InsumoDAO insumoDAO;

    public ServicoDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
        usuarioDAO = new UsuarioDAO(ctx);
        planoDAO = new PlanoDAO(ctx);
        insumoDAO = new InsumoDAO(ctx);

    }

    public long salvar(Servico servico){
        ContentValues cv = new ContentValues();
        cv.put("Horario", servico.getHorario().format(DateTimeFormatter.ofPattern("HH:mm")));
        cv.put("Observacao", servico.getObservacao());
        cv.put("PlanoID", servico.getPlano().getID());
        cv.put("UsuarioID", servico.getUsuario().getID());

        if(servico.getID() != null) {
            gw.getDatabase().update(TABLE_SERVICOS, cv, "ID=?", new String[]{servico.getID() + ""});
            return servico.getID();
        } else {
            Long servicoID = gw.getDatabase().insert(TABLE_SERVICOS, null, cv);
            for(Insumo insumo : servico.getInsumos()) {
                cv = new ContentValues();
                cv.put("ServicoID", servicoID);
                cv.put("InsumoID", insumo.getID());
                gw.getDatabase().insert("ServicosInsumos", null, cv);
            }
            return servicoID;
        }
    }

    public Servico retornarPorUsuario(Long usuarioID){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Servicos where UsuarioID = ?", new String[]{String.valueOf(usuarioID)});
        if(cursor.moveToFirst()){
            Long id = cursor.getLong(cursor.getColumnIndex("ID"));
            LocalTime horario = LocalTime.parse(cursor.getString(cursor.getColumnIndex("Horario")));
            String observacao = cursor.getString(cursor.getColumnIndex("Observacao"));
            Usuario usuario = usuarioDAO.retornarPorID(cursor.getLong(cursor.getColumnIndex("UsuarioID")));
            Plano plano = planoDAO.retornarPorID(cursor.getLong(cursor.getColumnIndex("PlanoID")));
            List<Insumo> insumos = insumoDAO.retornarPorServico(id);
            cursor.close();
            return new Servico(horario, observacao, usuario, plano, insumos);
        }

        return null;
    }
}
