package com.example.alunocurso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DBAluno extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION   = 1;
    private static final String DATABASE_NAME   = "aluno2.db";
    private static final String TABLE_NAME      = "aluno";
    private static final String COLUM_AlunoId   = "alunoId";
    private static final String COLUM_NomeAluno = "nomeAluno";
    private static final String COLUM_CursoId   = "cursoId";
    private static final String COLUM_Cpf        = "cpf";
    private static final String COLUM_Email     = "email";
    private static final String COLUM_Telefone  = "telefone";
    SQLiteDatabase db;


    private static final String TABLE_CREATE = "create table aluno " +
            "(alunoId integer primary key autoincrement," +
            " nomeAluno text not null," +
            "cursoId, " +
            "cpf text not null," +
            "email text not null, " +
            "telefone text not null," +
            "FOREIGN KEY(cursoId) REFERENCES curso(id));";


    public DBAluno(Context context) {
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
        onCreate(db);
    }

    public long insereAluno(Aluno a) {
        //verificar se ja existe aluno (pensar em uma solução)
        //String verifica = "select * from aluno where " + a.getNomeAluno().toString() + " not in (select nome from aluno)";
        //db.execSQL(verifica);
        if(a.getCursoId().toString() == null || a.getNomeAluno().toString() == null){
            //System.out.print("invalido");
            return -1;
        }

        else {
            long retorno;
            db = this.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(COLUM_AlunoId, a.getAlunoId());
            valores.put(COLUM_NomeAluno, a.getNomeAluno());
            valores.put(COLUM_CursoId, a.getCursoId());
            valores.put(COLUM_Cpf, a.getCpf());
            valores.put(COLUM_Email, a.getEmail());
            valores.put(COLUM_Telefone, a.getTelefone());
            retorno = db.insert(TABLE_NAME, null, valores);

            String res = Long.toString(retorno);
            Log.i("DBAluno", res);
            db.close();
            return retorno;
        }
    }

    public ArrayList<Aluno> selectAllAluno() {
        String[] coluns = {COLUM_AlunoId, COLUM_NomeAluno,
                COLUM_CursoId, COLUM_Cpf,
                COLUM_Email, COLUM_Telefone};

        Cursor cur_aluno = getWritableDatabase().query(TABLE_NAME, coluns, null, null,
                null, null, "upper(nomeAluno)", null);

        ArrayList<Aluno> listAluno = new ArrayList<Aluno>();

        while (cur_aluno.moveToNext()) {
            Aluno a = new Aluno();
            a.setAlunoId(cur_aluno.getString(0));
            a.setNomeAluno(cur_aluno.getString(1));
            a.setCursoId(cur_aluno.getString(2));
            a.setCpf(cur_aluno.getString(3));
            a.setEmail(cur_aluno.getString(4));
            a.setTelefone(cur_aluno.getString(5));
            listAluno.add(a);
        }

        return listAluno;
    }

    public long updateAluno(Aluno a) {
        long retorno;
        db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(COLUM_CursoId, a.getCursoId());
        valores.put(COLUM_NomeAluno, a.getNomeAluno());
        valores.put(COLUM_Cpf, a.getCpf());
        valores.put(COLUM_Email, a.getEmail());
        valores.put(COLUM_AlunoId, a.getAlunoId());
        valores.put(COLUM_Telefone, a.getTelefone());

        String[] args = {String.valueOf(a.getAlunoId())};
        retorno = db.update(TABLE_NAME, valores, "id = ?", args);
        db.close();
        return retorno;
    }

    public long deleteAluno(Aluno a) {
        long retorno;
        db = this.getWritableDatabase();
        String[] args = {String.valueOf(a.getAlunoId())};

        retorno = db.delete(TABLE_NAME, COLUM_AlunoId + " = ?", args);
        return retorno;


    }

}

