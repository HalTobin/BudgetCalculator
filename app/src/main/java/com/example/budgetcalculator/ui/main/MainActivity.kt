package com.example.budgetcalculator.ui.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetcalculator.R
import com.example.budgetcalculator.base.BaseActivity
import com.example.budgetcalculator.databinding.ActivityMainBinding
import com.example.budgetcalculator.domain.model.Operation
import com.google.android.material.switchmaterial.SwitchMaterial
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), MainContract.View, ListOperationAdapter.OnItemClick {

    private var mAdapter: ListOperationAdapter? = null

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding!!.root)

        setUpAdapter()
        setUpListeners()
        setUpObservers()
    }

    private fun setUpAdapter() {
        mAdapter = ListOperationAdapter(this, ArrayList<Operation>(), this)
        binding!!.mainBudgetListOperation.layoutManager = LinearLayoutManager(this)
        binding!!.mainBudgetListOperation.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        binding!!.mainBudgetListOperation.adapter = mAdapter
    }

    private fun setUpListeners() {
        binding!!.mainBudgetAddOperation.setOnClickListener {
            this.showAddEditOperationDialog()
        }
    }

    private fun setUpObservers() {
        presenter.observeOperations().observe(this) { operations ->
            displayOperation(operations)
        }
    }

    @SuppressLint("InflateParams")
    fun showAddEditOperationDialog() {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val dialogLayout = View.inflate(this, R.layout.dialog_add_edit_operation, null)

        val titleText = dialogLayout.findViewById<EditText>(R.id.main_operation_title)
        val amountText = dialogLayout.findViewById<EditText>(R.id.main_operation_amount)
        val isIncomeOrOutcome = dialogLayout.findViewById<SwitchMaterial>(R.id.main_operation_income_or_outcome)
        val isAnnualOrMonthly = dialogLayout.findViewById<SwitchMaterial>(R.id.main_operation_annual_or_monthly)

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

    }

    override fun displayOperation(operations: List<Operation>) {
        mAdapter!!.updateList(operations)
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

    override fun onClick(estateId: Int) {
        TODO("Not yet implemented")
    }

}