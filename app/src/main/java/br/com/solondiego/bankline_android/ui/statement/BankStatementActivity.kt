package br.com.solondiego.bankline_android.ui.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.solondiego.bankline_android.R
import br.com.solondiego.bankline_android.databinding.ActivityBankStatementBinding
import br.com.solondiego.bankline_android.domain.Correntista
import java.lang.IllegalArgumentException

class BankStatementActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ACCOUNT_HOLDER = "br.com.solondiego.bankline_android.ui.statement.EXTRA_ACCOUNT_HOLDER"
    }

    private val binding by lazy{
        ActivityBankStatementBinding.inflate(layoutInflater)
    }

    private val accountHolder by lazy {
        intent.getParcelableExtra<Correntista>(EXTRA_ACCOUNT_HOLDER) ?: throw IllegalArgumentException()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("TESTE", "Chegou o ID: ${accountHolder.id}")
    }
}