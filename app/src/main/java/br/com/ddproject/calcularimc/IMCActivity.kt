package br.com.ddproject.calcularimc

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_i_m_c.*

class IMCActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_i_m_c)

        // Criar uma lista de opções para o Spinner
        val listaGenero = arrayListOf("", "Feminino", "Masculino", "Não-binário")

        //Criando o adaptador para o Spinner
        val generoAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaGenero)

        // Plugar o adaptador no Spinner
        spnIMCGenero.adapter = generoAdapter

        btnIMCCalcular.setOnClickListener {
            val nome = (edtIMCNome.text).toString()
            val altura = (edtIMCAltura.text).toString()
            val peso = edtIMCPeso.text.toString()
            val genero = spnIMCGenero.selectedItem.toString()

            if (nome.isEmpty() || altura.isEmpty() || peso.isEmpty() || genero == listaGenero[0]) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                val h = altura.toFloat() / 100
                val kg = peso.toFloat()
                val imc = kg / (h * h)
                var faixa = ""

                if(imc < 17){
                    faixa = "Muito abaixo do peso"

                }else if(imc > 17 && imc < 18.49){
                    faixa = "Abaixo do peso"

                }else if(imc > 18.49 && imc < 24.99){
                    faixa = "Peso normal"

                }else if(imc > 24.99 && imc < 29.99){
                    faixa = "Acima do peso"

                }else if(imc > 29.49 && imc < 34.99){
                    faixa = "Obesidade I"

                }else if(imc > 34.99 && imc < 39.99){
                    faixa = "Obesidade II (severa)"

                }else if(imc > 39.99){
                    faixa = "Obesidade III (mórbida)"

                }

                "%.2f".format(imc).also { tevIMCResultado.text = it }
                tevIMCFaixa.text = faixa
                btnIMCSalvar.visibility = 1

            }
        }
        btnIMCSalvar.setOnClickListener {
            // Obter os dados digitados pelo usuário
            val nome = (edtIMCNome.text).toString()
            val altura = (edtIMCAltura.text).toString()
            val peso = edtIMCPeso.text.toString()
            val imc = tevIMCResultado.text.toString().trim().toLowerCase()
            val faixa = tevIMCFaixa.text.toString().trim()
            val genero = spnIMCGenero.selectedItem.toString()

            // Validação dos campos
            if (nome.isEmpty() || altura.isEmpty() || peso.isEmpty() || genero == listaGenero[0]) {
                btnIMCSalvar.visibility = -1
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
            else { // Todas os campos estão preenchidos

                // Criar ou acessar o arquivo de preferências compartilhadas
                val sharedPrefs = getSharedPreferences("imc_$nome", Context.MODE_PRIVATE)

                // Referência para o editor do arquivo
                val editPrefs = sharedPrefs.edit()

                // Colocar as edições no editor do Shared Preference
                editPrefs.putString("NOME", nome)
                editPrefs.putString("ALTURA", altura)
                editPrefs.putString("PESO", peso)
                editPrefs.putString("IMC", imc)
                editPrefs.putString("FAIXA", faixa)
                editPrefs.putString("GENERO", genero)

                // Salvar as alterações que queremos no arquivo de SharedPreference
                editPrefs.apply()

                // Abrir a tela MainActivity
                val mIntent = Intent(this, MainActivity::class.java)

                // Passando informações de uma activity para a outra pela Intent
                mIntent.putExtra("INTENT_NOME", nome)

                startActivity(mIntent)

                // Encerra todas as telas do empilhamento
                finishAffinity()
            }
        }
    }
}