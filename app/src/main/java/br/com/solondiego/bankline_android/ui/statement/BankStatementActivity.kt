package br.com.solondiego.bankline_android.ui.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.solondiego.bankline_android.R
import br.com.solondiego.bankline_android.data.State
import br.com.solondiego.bankline_android.databinding.ActivityBankStatementBinding
import br.com.solondiego.bankline_android.domain.Correntista
import br.com.solondiego.bankline_android.domain.Movimentacao
import br.com.solondiego.bankline_android.domain.TipoMovimentacao
import br.com.solondiego.bankline_android.ui.BankStatementViewModel
import com.google.android.material.snackbar.Snackbar
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

    private val viewModel by viewModels<BankStatementViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
       // Log.d("TESTE", "Chegou o ID: ${accountHolder.id}")

        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)

        findBankStatement()

        binding.srlBankStatement.setOnRefreshListener { findBankStatement() }
    }

    private fun findBankStatement() {
       viewModel.findBankStatement(accountHolder.id).observe(this){state ->
           when(state){
               is State.Success ->{
                   binding.rvBankStatement.adapter = state.data?.let { BankStatementAdapter(it) }
                   binding.srlBankStatement.isRefreshing = false
               }
               is State.Erro -> {
                   state.message?.let { Snackbar.make(binding.rvBankStatement, it, Snackbar.LENGTH_LONG).show() }
                   binding.srlBankStatement.isRefreshing = false
               }
               State.Wait -> binding.srlBankStatement.isRefreshing = true
           }
       }
    }
}