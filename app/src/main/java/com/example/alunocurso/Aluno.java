package com.example.alunocurso;

import java.io.Serializable;

public class Aluno implements Serializable {

    private String alunoId;
    private String cursoId;
    private String nomeAluno;
    private String cpf;
    private String email;
    private String telefone;

    public  Aluno(){

    }

    public String getAlunoId() {
        return alunoId;
    }
    public String getCursoId() {
        return cursoId;
    }
    public String getNomeAluno() {
        return nomeAluno;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefone() {
        return telefone;
    }


    public void setAlunoId(String alunoId) {
        this.alunoId = alunoId;
    }
    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }
    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return nomeAluno+
                "- " + cursoId;
    }
}
