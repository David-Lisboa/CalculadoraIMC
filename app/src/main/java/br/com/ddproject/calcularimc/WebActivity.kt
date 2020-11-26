package br.com.ddproject.calcularimc

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val nome = intent.getStringExtra("INTENT_NOME")

        //Acessando o arquivo Shared Preferences
        val sharedPrefs = getSharedPreferences("imc_$nome", Context.MODE_PRIVATE)

        //Recuperando os dados no arquivo Shared Preferences
        val faixa = sharedPrefs.getString("FAIXA", "Chave não encontrada")


        //Habilitando a execução de códigos javascript
        wbvWeb.settings.javaScriptEnabled = true

        var url = "https://www.google.com/"
        when (faixa) {
            "Muito abaixo do peso" -> {
                url = "https://hospitalsantamonica.com.br/saude-mental/transtorno-alimentar"
            }
            "Peso normal" -> {
                url = "https://www.ifood.com.br/"
            }
            "Acima do peso" -> {
                url = "https://www.google.com/search?biw=1536&bih=763&tbm=lcl&sxsrf=ALeKk02E5N3Mn4UTQ4yGajeHl3oSytb84A%3A1606357693343&ei=vRK_X7bKFIjG5OUP7KCwgAU&q=academia&oq=academia&gs_l=psy-ab.3..0i433i67k1j0l4j0i433i131k1j0i433i67k1j0l3.90683.92134.0.93009.8.6.0.2.2.0.219.798.0j2j2.4.0....0...1c.1.64.psy-ab..2.6.803...46j0i433k1j46i433k1j0i67k1.0.O5oXjCTPyz4#rlfi=hd:;si:;mv:[[-3.0541723999999997,-59.92286489999999],[-3.1159812,-59.988836199999994]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!2m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:2"
            }
            "Obesidade I" -> {
                url = "https://www.doctoralia.com.br/nutricionista"
            }
            "Obesidade II (severa)" -> {
                url = "https://centrodeobesidadeediabetes.org.br/tudo-sobre-obesidade/tratamento-da-obesidade/"
            }
            "Obesidade III (mórbida)" -> {
                url = "https://centrodeobesidadeediabetes.org.br/tudo-sobre-obesidade/tratamento-da-obesidade/"
            }
            else -> {
                url = "https://www.google.com/"
            }
        }

        //Carregando um endereço de Web
        wbvWeb.loadUrl(url)

        //Definindo a WebView como cliente Web padrão
        wbvWeb.webViewClient = WebViewClient()

    }
}