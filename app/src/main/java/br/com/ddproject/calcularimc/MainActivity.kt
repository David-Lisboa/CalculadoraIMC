package br.com.ddproject.calcularimc

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Recuperando o email passado por meio da Intent
        val nome = intent.getStringExtra("INTENT_NOME")

        //Acessando o arquivo Shared Preferences
        val sharedPrefs = getSharedPreferences("imc_$nome", Context.MODE_PRIVATE)

        //Recuperando os dados no arquivo Shared Preferences
        val altura = sharedPrefs.getString("ALTURA", "Chave não encontrada")
        val peso = sharedPrefs.getString("PESO", "Chave não encontrada")
        val imc = sharedPrefs.getString("IMC", "Chave não encontrada")
        val faixa = sharedPrefs.getString("FAIXA", "Chave não encontrada")
        val genero = sharedPrefs.getString("GENERO", "Chave não encontrada")

        //vdvdv
        when (faixa) {
            "Muito abaixo do peso" -> {
                btnMainWeb.text = "Ajuda"
            }
            "Peso normal" -> {
                btnMainWeb.text = "Restaurante"
            }
            "Acima do peso" -> {
                btnMainWeb.text = "Academia"
            }
            "Obesidade I" -> {
                btnMainWeb.text = "Nutricionista"
            }
            "Obesidade II (severa)" -> {
                btnMainWeb.text = "Ajuda"
            }
            "Obesidade III (mórbida)" -> {
                btnMainWeb.text = "Ajuda"
            }
            else -> {
                btnMainWeb.text = "Web"
            }
        }



        //Exibindo os dados recuperados na tela
        tevMainNome.text = nome
        tevMainAltura.text = "$altura Cm"
        tevMainPeso.text = "$peso Kg"
        tevMainGenero.text = genero
        tevMainImc.text = "$imc ICM"
        tevMainFaixa.text = faixa

        btnMainSair.setOnClickListener {
            //Criando um alerta (caixa de diálogo)
            AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja mesmo sair?")
                .setPositiveButton("Sair"){_, _ ->
                    //Abrindo a tela de Login
                    val mIntent = Intent(this, IMCActivity::class.java)
                    startActivity(mIntent)
                    finishAffinity()
                }
                .setNeutralButton("Cancelar"){_,_ ->}
                .setCancelable(false)
                .create()
                .show()
        }

        btnMainWeb.setOnClickListener {


            //Criando um alerta (caixa de diálogo)
            AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Você sera redirecionado para pagina Web?")
                .setPositiveButton("Ok"){_, _ ->
                    //Abrindo a tela de Login
                    val mIntent = Intent(this, WebActivity::class.java)
                    mIntent.putExtra("INTENT_NOME", nome)
                    startActivity(mIntent)
//                    finishAffinity()
                }
                .setNeutralButton("Cancelar"){_,_ ->}
                .setCancelable(false)
                .create()
                .show()
        }
    }

}