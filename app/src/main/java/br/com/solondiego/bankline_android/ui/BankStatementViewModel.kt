package br.com.solondiego.bankline_android.ui

import androidx.lifecycle.ViewModel
import br.com.solondiego.bankline_android.data.BanklineRepository


class BankStatementViewModel : ViewModel (){

    fun findBankStatement(accountHolderId: Int) =
        BanklineRepository.findBankStatement(accountHolderId)
}