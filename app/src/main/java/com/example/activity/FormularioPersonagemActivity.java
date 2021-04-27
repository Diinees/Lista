package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import static com.example.activity.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagem";
    //constantes dos titulos
    private static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private Personagem personagem;
    private final PersonagemDAO dao = new PersonagemDAO();
    //instancia a classe para Formulario


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_personagem_menu_salvar)
            //salvar formulario
        {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        InicializacaoCampos();
        configuraBotaoSalvar();
        carregaPersonagem();
    }



    private void carregaPersonagem() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            //muda titulo do Formulario
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencheCampos();
        }
        else{
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }



    private void preencheCampos() {
        campoNome.setText(personagem.getNome());
        //pega informações no dao
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }



    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.button_salvar);
        //obtem button a partir da id encontrada no xml
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            //Cria ações para o botão
            @Override
            public void onClick(View view) {
                finalizaFormulario();
            }
        });
    }



    private void finalizaFormulario() {
        preencheePersonagem();
        if(personagem.idValido()){
            dao.edita(personagem);
        }
        else {
            dao.salva(personagem);
            //metodo para armazenar as informações no Personagem
        }
        finish();
    }



    private void InicializacaoCampos() {
        //metodo alterar formulario
        campoNome = findViewById(R.id.editText_nome);
        campoAltura = findViewById(R.id.editText_altura);
        //econtra edittext a partir da id encontrada no xml
        campoNascimento = findViewById(R.id.editText_nascimento);

        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN");
        MaskTextWatcher mtwAltura = new MaskTextWatcher(campoAltura, smfAltura);
        campoAltura.addTextChangedListener(mtwAltura);
        //aplica regra no texto digitado no formulario

        SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwNascimento = new MaskTextWatcher(campoNascimento, smfNascimento);
        campoNascimento.addTextChangedListener(mtwNascimento);
    }



     private void preencheePersonagem() {

        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        //converte os edittext em string
        String nascimento = campoNascimento.getText().toString();

        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
}}