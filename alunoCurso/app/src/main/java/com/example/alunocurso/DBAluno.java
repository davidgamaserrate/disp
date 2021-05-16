package com.example.alunocurso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBAluno extends SQLiteOpenHelper {
    private static final int    DATABASE_VERSION = 1;
    private static final String DATABASE_NAME    = "aluno.db";
    private static final String TABLE_NAME       = "aluno";
    private static final String COLUMN_AlunoId   = "alunoId";
    private static final String COLUMN_NomeAluno = "nomeAluno";
    private static final String COLUMN_CursoId   = "cursoId";
    private static final String COLUMN_Cpf       = "cpf";
    private static final String COLUMN_Email     = "email";
    private static final String COLUMN_Telefone  = "telefone";
    SQLiteDatabase db;

    private static  final String TABLE_CREATE = "create table aluno " +
    "(alunoId integer primay key autoincrement," +
            " nomeAluno text not null, " +
            "cursoId text not null, " +
            "cpf text not null," +
            "email text not null, " +
            "telefone text not null);";

    public DBAluno( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
    db.execSQL(query);
    }

    public long insereAluno (Aluno a){
        long retorno;
        db= this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(COLUMN_AlunoId, a.getAlunoId());
        valores.put(COLUMN_NomeAluno, a.getNomeAluno());
        valores.put(COLUMN_CursoId, a.getCursoId());
        valores.put(COLUMN_Cpf, a.getCpf());
        valores.put(COLUMN_Email, a.getEmail());
        valores.put(COLUMN_Telefone, a.getTelefone());
        retorno = db.insert(TABLE_NAME, null, valores);

        String res = Long.toString(retorno);
        Log.i ("DBAlunoHelper",res);
        db.close();
        return retorno;

    }

    public ArrayList<Aluno> selecionaTodosAlunos(){
        String[] columns = {COLUMN_AlunoId,COLUMN_NomeAluno,
                COLUMN_CursoId, COLUMN_Cpf ,COLUMN_Email ,COLUMN_Telefone};

        Cursor cur_aluno = getReadableDatabase().query(TABLE_NAME, columns, null,null,
                null, null, "upper(nomeAluno)",null);

        ArrayList<Aluno> listAluno = new ArrayList<Aluno>();

        while (cur_aluno.moveToNext()){
            Aluno a = new Aluno();
            a.setAlunoId(cur_aluno.getInt       (0));
            a.setCpf(cur_aluno.getString        (1));
            a.setCursoId(cur_aluno.getInt       (2));
            a.setEmail(cur_aluno.getString      (3));
            a.setNomeAluno(cur_aluno.getString  (4));
            a.setTelefone(cur_aluno.getString   (5));
            listAluno.add(a);
        }

        return listAluno;
    }

}

