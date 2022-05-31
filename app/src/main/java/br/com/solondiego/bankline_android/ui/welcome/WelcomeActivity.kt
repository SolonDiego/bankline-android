package br.com.solondiego.bankline_android.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.solondiego.bankline_android.R
import br.com.solondiego.bankline_android.databinding.ActivityWelcomeBinding
import br.com.solondiego.bankline_android.domain.Correntista
import br.com.solondiego.bankline_android.ui.statement.BankStatementActivity

class WelcomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btContinue.setOnClickListener{
            val accountHolderId = binding.etAccountHolderId.text.toString().toInt()
            val accountHolder = Correntista(accountHolderId)

            val intent = Intent(this, BankStatementActivity::class.java).apply {
                putExtra(BankStatementActivity.EXTRA_ACCOUNT_HOLDER, accountHolder)
            }

            startActivity(intent)
        }
    }
}