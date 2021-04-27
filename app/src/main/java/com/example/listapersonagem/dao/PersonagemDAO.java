package com.example.listapersonagem.dao;

import com.example.activity.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorDeId = 1;

    public void salva(Personagem personagemSalvo) {
        //metodo salvar personagens

        personagemSalvo.setId(contadorDeId);
        //atribuindo id para personagem salvo
        personagens.add(personagemSalvo);
        //adicionando personagem na lista
        contadorDeId++;
        //aumenta numero de id para proximo personagem

    }

    public void edita(Personagem personagem){
        //metodo fazer alterações nos personagens

        Personagem personagemEncontrado = buscaPersonagemId(personagem);
        //identificar id do personagem que o usuario deseja modificar
        if(personagemEncontrado != null){
            int posicaoDoPersonagem = personagens.indexOf(personagemEncontrado);
            personagens.set(posicaoDoPersonagem, personagem);
        }

    }

    private Personagem buscaPersonagemId(Personagem personagem) {
        // busca id do personagem
        for (Personagem p: personagens){
            if(p.getId() == personagem.getId()){
                return p;
            }
        }
        return null;
    }

    public List<Personagem> todos() { return new ArrayList<>(personagens); }
    //busca informações salvas

    public void remove(Personagem personagem) {
        //Remove personagens da lista
        Personagem personagemDevolvido = buscaPersonagemId(personagem);
        //busca id do personagem que quer remover
        if(personagemDevolvido != null) {
            //confere se personagem é diferente de nulo
            personagens.remove(personagemDevolvido);
        }
    }
}
