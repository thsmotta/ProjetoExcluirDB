package br.com.projeto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.projeto1.database.ProdutoDAO;
import br.com.projeto1.database.contract.ProdutoContract;
import br.com.projeto1.modelo.Produto;

public class CadastroProdutoActivity extends AppCompatActivity {

    private int id = 0;

    private EditText editTextNome;
    private  EditText editTextValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        setTitle("Cadatro de Produtos");

        editTextNome = findViewById(R.id.et_nome);
        editTextValor = findViewById(R.id.et_valor);

        carregarProduto();
    }

    public void carregarProduto() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("produtoEditado") != null) {
            Produto produto = (Produto) intent.getExtras().get("produtoEditado");
            editTextNome.setText(produto.getNome());
            editTextValor.setText(String.valueOf(produto.getValor()));
            id = produto.getId();
        }
    }

    public void onClickVoltar(View v ) {

        finish();
    }

    public void onClickSalvar(View v) {
        String nome = editTextNome.getText().toString();
        Float valor = Float.parseFloat(editTextValor.getText().toString());

        Produto produto = new Produto(id ,nome, valor);
            ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
            boolean salvou = produtoDAO.salvar(produto);
            if (salvou) {
                finish();
            } else {
                Toast.makeText(CadastroProdutoActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
            }

    }

}