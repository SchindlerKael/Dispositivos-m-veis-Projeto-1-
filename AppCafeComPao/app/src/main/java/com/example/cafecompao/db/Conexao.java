package com.example.cafecompao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CoffeeWithBread.db";
    private static final int DATABASE_VERSION = 5;

    public Conexao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_INSUMO = "CREATE TABLE Insumos (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nome TEXT NOT NULL, " +
                "Descricao TEXT, " +
                "Tipo TEXT NOT NULL);";

        String CREATE_TABLE_ENDERECO = "CREATE TABLE Enderecos (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Cidade TEXT NOT NULL, " +
                "UF TEXT NOT NULL, " +
                "Bairro TEXT NOT NULL, " +
                "Rua TEXT NOT NULL, " +
                "Numero TEXT NOT NULL, " +
                "Complemento TEXT);";

        String CREATE_TABLE_USUARIO = "CREATE TABLE Usuarios (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nome TEXT NOT NULL, " +
                "Email TEXT NOT NULL, " +
                "Senha TEXT NOT NULL, " +
                "EnderecoID INTEGER NOT NULL, " +
                "FOREIGN KEY (EnderecoID) REFERENCES Enderecos (ID));";

        String CREATE_TABLE_PLANO = "CREATE TABLE Planos (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nome TEXT NOT NULL, " +
                "Valor FLOAT NOT NULL, " +
                "QtdComida INTEGER NOT NULL, " +
                "QtdBebida INTEGER NOT NULL);";

        String CREATE_TABLE_SERVICO = "CREATE TABLE Servicos (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Horario TEXT NOT NULL, " +
                "Observacao TEXT, " +
                "UsuarioID INTEGER NOT NULL, " +
                "PlanoID INTEGER NOT NULL, " +
                "FOREIGN KEY (UsuarioID) REFERENCES Usuarios (ID)," +
                "FOREIGN KEY (PlanoID) REFERENCES Planos (ID));";

        String CREATE_TABLE_SERVICO_INSUMOS = "CREATE TABLE ServicosInsumos (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ServicoID INTEGER NOT NULL, " +
                "InsumoID INTEGER NOT NULL, " +
                "FOREIGN KEY (ServicoID) REFERENCES Servicos (ID)," +
                "FOREIGN KEY (InsumoID) REFERENCES Insumos (ID));";

        db.execSQL(CREATE_TABLE_INSUMO);
        db.execSQL(CREATE_TABLE_ENDERECO);
        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(CREATE_TABLE_PLANO);
        db.execSQL(CREATE_TABLE_SERVICO);
        db.execSQL(CREATE_TABLE_SERVICO_INSUMOS);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
