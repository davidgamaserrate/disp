package com.example.alunocurso;
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
    private Button btnNovoCadastro;
    private Aluno aluno;
    private int id1,id2;
    DBAluno alunoHelper;
    ArrayList<Aluno> arrayListAluno;
    ArrayAdapter<Aluno> arrayAdapterAluno;



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListAluno = findViewById(R.id.ListAluno);
        registerForContextMenu(ListAluno);

        btnNovoCadastro = findViewById(R.id.btnNovoCadastro);
        preencherLista();
        btnNovoCadastro = findViewById(R.id.btnNovoCadastro);
        btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,CadastroAluno.class);
                startActivity(it);
            }
        });


        ListAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoEnviado = (Aluno)arrayAdapterAluno.getItem(position);
                Intent it = new Intent(MainActivity.this,CadastroAluno.class);
                it.putExtra("ch_aluno",alunoEnviado);
                startActivity(it);

            }
        });
        ListAluno.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                aluno = arrayAdapterAluno.getItem(position);
                return false;
            }
        });
    }

    public void preencherLista (){
        alunoHelper = new DBAluno(MainActivity.this);
        arrayListAluno = alunoHelper.selectAllAluno();
        alunoHelper.close();
        if(arrayListAluno != null){
            arrayAdapterAluno = new ArrayAdapter<Aluno> (MainActivity.this, android.R.layout.simple_list_item_1,arrayListAluno);
            ListAluno.setAdapter(arrayAdapterAluno);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        MenuItem mDelete = menu.add(Menu.NONE, id1, 1,"Excluir");
        MenuItem mSair = menu.add(Menu.NONE, id2, 2,"Cancelar");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                long retorno;
                alunoHelper = new DBAluno(MainActivity.this);
                retorno     = alunoHelper.deleteAluno(aluno);
                alunoHelper.close();
                if(retorno==-1){
                    alert("Não foi possivel excluir aluno");
                }
                else{
                    alert("Aluno excluído com sucesso!");
                }
                preencherLista();
                return false;        }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        preencherLista();
    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}