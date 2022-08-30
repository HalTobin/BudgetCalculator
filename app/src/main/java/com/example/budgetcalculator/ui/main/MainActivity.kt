package com.example.budgetcalculator.ui.main

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import com.example.budgetcalculator.base.BaseActivity
import com.example.budgetcalculator.databinding.ActivityMainBinding
import com.example.budgetcalculator.databinding.DialogAddEditOperationBinding
import com.example.budgetcalculator.domain.model.Operation


class MainActivity: BaseActivity<ActivityMainBinding>(), MainContract.View {

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding!!.root)

        println("INIT APP")

        presenter = MainPresenter(this, MainModel())

        setUpListeners()
    }

    private fun setUpListeners() {
        binding!!.mainBudgetAddOperation.setOnClickListener {
            println("CLICK")
            this.showAddEditOperationDialog()
        }
    }

    @SuppressLint("InflateParams")
    fun showAddEditOperationDialog() {

        val dialog = Dialog(this)
        val dialogBinding: DialogAddEditOperationBinding = DialogAddEditOperationBinding.inflate(LayoutInflater.from(this))
        dialog.setContentView(binding!!.root)
        dialog.show()

        dialogBinding.mainOperationAdd.setOnClickListener {
            dialog.dismiss()
        }

    }

    override fun displayOperation(operations: List<Operation>) {
        TODO("Not yet implemented")
    }

    override fun displayValues(values: List<Int>) {
        TODO("Not yet implemented")
    }

    override fun insertOperation(
        type: Int,
        title: String,
        amount: Float,
        isIncome: Boolean,
        isAnnual: Boolean
    ) {
        TODO("Not yet implemented")
    }

}