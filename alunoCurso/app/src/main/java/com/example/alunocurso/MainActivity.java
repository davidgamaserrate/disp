package com.example.alunocurso
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView ListAluno;
    private Button btnNovoAluno;
    Aluno aluno;
    DBAlunoHelper alunoHelper;
    ArrayList<Aluno> arrayList;
    ArrayAdapter<Aluno> arrayAdapter;



    protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listAluno = findViewById(R.id.listContatos);
        btnNovoAluno = findViewById(R.id.btnNovoAluno);
    }
}