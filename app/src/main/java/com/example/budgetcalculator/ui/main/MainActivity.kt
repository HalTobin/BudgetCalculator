package com.example.budgetcalculator.ui.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetcalculator.R
import com.example.budgetcalculator.base.BaseActivity
import com.example.budgetcalculator.databinding.ActivityMainBinding
import com.example.budgetcalculator.domain.model.Operation
import com.example.budgetcalculator.domain.model.OperationType
import com.example.budgetcalculator.domain.model.Summary
import com.google.android.material.switchmaterial.SwitchMaterial
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding>(),
    MainContract.View,
    ListOperationAdapter.OnItemClick,
    ListOperationTypeAdapter.OnItemClick {

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
        mAdapter = ListOperationAdapter(this, ArrayList(), this)
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
            this.showAddEditOperationDialog(null)
        }
    }

    private fun setUpObservers() {
        presenter.observeOperations().observe(this) { operations ->
            displayOperation(operations)
        }
    }

    @SuppressLint("InflateParams")
    fun showAddEditOperationDialog(operation: Operation?) {

        var operationId: Int? = null

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val dialogLayout = View.inflate(this, R.layout.dialog_add_edit_operation, null)

        val typeSpinner = dialogLayout.findViewById<AutoCompleteTextView>(R.id.main_operation_type)
        val titleText = dialogLayout.findViewById<EditText>(R.id.main_operation_title)
        val amountText = dialogLayout.findViewById<EditText>(R.id.main_operation_amount)
        val isIncomeOrOutcome = dialogLayout.findViewById<SwitchMaterial>(R.id.main_operation_income_or_outcome)
        val isAnnualOrMonthly = dialogLayout.findViewById<SwitchMaterial>(R.id.main_operation_annual_or_monthly)

        val spinnerAdapter = ListOperationTypeAdapter(
            this,
            presenter.onChangeIncomeOrOutcome(!isIncomeOrOutcome.isChecked),
            this
        )

        typeSpinner.setAdapter(spinnerAdapter)

        isIncomeOrOutcome.setOnClickListener {
            spinnerAdapter.updateList(
                presenter.onChangeIncomeOrOutcome(!isIncomeOrOutcome.isChecked)
            )
        }

        if (operation != null) {
            operationId = operation.id
            titleText.setText(operation.title)
            amountText.setText(operation.amount.toString())
            isIncomeOrOutcome.isChecked = !operation.isIncome
            isAnnualOrMonthly.isChecked = !operation.isAnnual
        }

        with(builder) {
            setView(dialogLayout)
            setTitle(R.string.new_operation)

            setPositiveButton(
                if (operation == null) R.string.add_low
                else R.string.edit_low
            ) { dialog, _ ->
                presenter.onAddOperationButtonClick(
                    id = operationId,
                    type = 0,
                    title = titleText.text.toString(),
                    amount = amountText.text.toString().toFloat(),
                    isIncome = !isIncomeOrOutcome.isChecked,
                    isAnnual = !isAnnualOrMonthly.isChecked
                )
                dialog.dismiss()
            }

            if (operation != null) {
                setNegativeButton(R.string.delete_low) { dialog, _ ->
                    presenter.onDeleteOperationButtonClick(operation)
                    dialog.dismiss()
                }
            }

            show()
        }

    }

    override fun displayOperation(operations: List<Operation>) {
        mAdapter!!.updateList(operations)
    }

    override fun displayValues(values: Summary) {
        val df = DecimalFormat("#.##")

        binding!!.mainBudgetIncomeRawYear.text = df.format(values.incomesRawAnnual)
        binding!!.mainBudgetIncomeRawMonth.text = df.format(values.incomesRawMonthly)

        binding!!.mainBudgetIncomeNetYear.text = df.format(values.incomesAnnual)
        binding!!.mainBudgetIncomeNetMonth.text = df.format(values.incomesMonthly)

        if (values.incomesAnnual < 0) setNetIncomesTextColor(R.color.red)
        else setNetIncomesTextColor(R.color.green)

        binding!!.mainBudgetOutcomeYear.text = df.format(values.outcomesAnnual)
        binding!!.mainBudgetOutcomeMonth.text = df.format(values.outcomesMonthly)
    }

    private fun setNetIncomesTextColor(colorId: Int) {
        binding!!.mainBudgetIncomeNetYear.setTextColor(
            ContextCompat.getColor(this, colorId)
        )
        binding!!.mainBudgetIncomeNetMonth.setTextColor(
            ContextCompat.getColor(this, colorId)
        )
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

    // Click on an Operation from the RecyclerView of the MainActivity
    override fun onClick(operation: Operation) {
        showAddEditOperationDialog(operation)
    }
    // Long click on an Operation from the RecyclerView of the MainActivity
    override fun onLongClick(operation: Operation) {
        presenter.onOnOffOperationClick(operation)
    }

    // Click on an OperationType from the Spinner of the AddEditOperationDialog
    override fun onClick(operationType: OperationType) {
        TODO("Not yet implemented")
    }



}