package com.example.budgetcalculator.ui.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import androidx.core.widget.doAfterTextChanged
import com.example.budgetcalculator.R
import com.example.budgetcalculator.base.BaseActivity
import com.example.budgetcalculator.databinding.ActivityMainBinding
import com.example.budgetcalculator.domain.model.Operation


class MainActivity : BaseActivity<ActivityMainBinding>(), MainContract.View {

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding!!.root)

        presenter = MainPresenter(this, MainModel())

        println("INIT APP")

        setUpListeners()
    }

    private fun setUpListeners() {
        println("CLICK")
        binding!!.mainBudgetAddOperation.setOnClickListener {
            println("CLICK")
            this.showAddEditOperationDialog()
        }
    }

    @SuppressLint("InflateParams")
    fun showAddEditOperationDialog() {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val dialogLayout = View.inflate(this, R.layout.dialog_add_edit_operation, null)

        val titleText = dialogLayout.findViewById<EditText>(R.id.main_operation_title)
        val amountText = dialogLayout.findViewById<EditText>(R.id.main_operation_amount)
        val isIncomeOrOutcome = dialogLayout.findViewById<Switch>(R.id.main_operation_income_or_outcome)
        val isAnnualOrMonthly = dialogLayout.findViewById<Switch>(R.id.main_operation_annual_or_monthly)

        with(builder) {
            setView(dialogLayout)
            setTitle(R.string.new_operation)

            setPositiveButton(R.string.add_low) { dialog, _ ->
                presenter.onAddOperationButtonClick(
                    type = 0,
                    title = titleText.text.toString(),
                    amount = amountText.text.toString().toFloat(),
                    isIncome = !isIncomeOrOutcome.isActivated,
                    isAnnual = !isAnnualOrMonthly.isActivated
                )
                dialog.dismiss()
            }

            setNegativeButton(R.string.cancel_low) { dialog, _ ->
                dialog.dismiss()
            }

            show()
        }

        /*val dialog = Dialog(this)
        val dialogBinding: DialogAddEditOperationBinding = DialogAddEditOperationBinding.inflate(LayoutInflater.from(this))
        dialog.setContentView(dialogBinding.root)
        dialog.show()

        dialogBinding.mainOperationAdd.setOnClickListener {
            dialog.dismiss()
        }*/

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