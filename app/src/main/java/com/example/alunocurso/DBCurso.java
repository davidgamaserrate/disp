package com.example.alunocurso;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBCurso  extends SQLiteOpenHelper{
    private static final int    DATABASE_VERSION = 1;
    private static final String DATABASE_NAME    = "aluno2.db";
    private static final String TABLE_NAME       = "curso";
    private static final String COLUM_CursoId    = "id";
    private static final String COLUM_NomeCurso  = "nomeCurso";
    private static final String COLUM_qtdeHoras    = "qtdHoras";

    SQLiteDatabase db;

    private static  final String TABLE_CREATE =   "create table curso " +
            "(id integer primary key autoincrement, " +
            "nomeCurso text not null, " +
            "integer qtdHoras);";


    public DBCurso( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insereCurso (Curso a){
        //verificar se ja existe aluno (pensar em uma solução)
        //String verifica = "select * from aluno where " + a.getNomeAluno().toString() + " not in (select nome from aluno)";
        //db.execSQL(verifica);

        long retorno;
        db= this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(COLUM_CursoId,     a.getCursoId());
        valores.put(COLUM_NomeCurso,   a.getNomeCurso());
        valores.put(COLUM_qtdeHoras,     a.getQtdeHoras());

        retorno = db.insert(TABLE_NAME, null, valores);

        String res = Long.toString(retorno);
        Log.i ("DBCurso",res);
        db.close();
        return retorno;

    }

    public long deleteCurso(Curso a){
        long retorno;
        db = this.getWritableDatabase();
        //String test = "select count(*) from aluno where cursoId not in (select id from curso);";
        //long r = db.execSQL(test);
        /*String test = "select count(*) from aluno where cursoId not in (select id from curso);";
        db.execSQL(test)) */
        String[] args = {String.valueOf(a.getCursoId())};

        /*if(test.toString() == null){*/
            retorno=db.delete(TABLE_NAME, COLUM_CursoId+"=?",args);
            return retorno;
       /*} else {
            return retorno = -1;
        }*/
    }
}
