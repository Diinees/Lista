package com.example.activity;

import androidx.annotation.NonNull;

import java.io.Serializable;

import javax.xml.namespace.QName;

public class Personagem implements Serializable {

    private String nome;
    private String altura;
    //variaveis
    private String nascimento;
    private int id = 0;

    public Personagem(String nome, String altura, String nascimento) {

        this.nome = nome;
        this.altura = altura;
        //valor das variaveis
        this.nascimento = nascimento;
    }

    public Personagem() {

    }

    @NonNull
    @Override
    public String toString() {
        //retorna nome salvo na aplicação

        return nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }
    //convertendo valores em strings

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }


    public void setId(int id) {
        this.id = id;
    }
    //ids para identificar qual nome estamos clicando

    public int getId() {
        return id;
    }

    public boolean idValido() {
        return id > 0;
    }
}
