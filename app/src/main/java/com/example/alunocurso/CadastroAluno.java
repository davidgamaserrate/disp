package com.example.alunocurso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroAluno extends AppCompatActivity {
    private EditText edtNome, edtAlunoID ,
            edtCursoID, edtCPF,edtEmail, edtTelefone;
    Aluno   aluno, alteraAluno;
    private Button btnVariavel;
    long    retornoBD;

    DBAluno dbAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_alunos);
        Intent it   = getIntent();
        alteraAluno = (Aluno) it.getSerializableExtra("ch_aluno");
        aluno       = new Aluno();
        dbAluno     = new DBAluno(CadastroAluno.this);

        edtNome      = findViewById(R.id.edtNome);
        edtCursoID   = findViewById(R.id.edtCursoID);
        edtCPF       = findViewById(R.id.edtCPF);
        edtEmail     = findViewById(R.id.edtEmail);
        edtTelefone  = findViewById(R.id.edtTelefone);
        btnVariavel  = findViewById(R.id.btnVariavel);

        if(alteraAluno != null){
            btnVariavel.setText ("ALTERAR");
            edtNome.setText     (alteraAluno.getNomeAluno());
            edtAlunoID.setText     (alteraAluno.getAlunoId());
            edtCursoID.setText  (alteraAluno.getCursoId());
            edtCPF.setText      (alteraAluno.getCpf());
            edtEmail.setText    (alteraAluno.getEmail());
            edtTelefone.setText (alteraAluno.getTelefone());
            aluno.setAlunoId    (alteraAluno.getAlunoId());

        }else{
            btnVariavel.setText("SALVAR");
        }
        btnVariavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeAluno    = edtNome.getText().toString();
                String cursoId      = edtCursoID.getText().toString();
                String cpf          = edtCPF.getText().toString();
                String email        = edtEmail.getText().toString();
                String telefone     = edtTelefone.getText().toString();

                long retornoBD;

                aluno.setNomeAluno(nomeAluno);
                aluno.setCpf(cpf);                              aluno.setCursoId(cursoId);
                aluno.setEmail(email);                          aluno.setTelefone(telefone);

               if(btnVariavel.getText().toString().equals("SALVAR")){
                    retornoBD = dbAluno.insereAluno(aluno);
                    if(retornoBD == -1){
                        Toast.makeText(CadastroAluno.this, "Erro ao cadastrar",
                                        Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(CadastroAluno.this,
                                    "Cadastro realizado com sucesso!",
                                    Toast.LENGTH_LONG).show();
                    }
                } else{
                    dbAluno.updateAluno(aluno);
                   dbAluno.close();
                }
                finish();
            }
        });



    }
}
