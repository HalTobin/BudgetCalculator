package com.example.budgetcalculator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.budgetcalculator.domain.model.Operation
import com.example.budgetcalculator.domain.model.OperationType
import com.example.budgetcalculator.domain.model.Summary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainPresenter @Inject constructor(
    private var view : MainContract.View,
    private val model: MainContract.Model
) : MainContract.Presenter, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun observeOperations(): LiveData<List<Operation>> {

        launch {
            model.getOperations().collect { operations ->
                val values = Summary()
                var incomes = 0f
                var outcomes = 0f

                operations.forEach { operation ->
                    if (operation.isOn) {
                        if (operation.isIncome) {
                            incomes +=
                                if (operation.isAnnual) operation.amount
                                else (operation.amount * 12f)
                        }
                        else {
                            outcomes +=
                                if (operation.isAnnual) operation.amount
                                else (operation.amount * 12f)
                        }
                    }
                }

                values.setAnnualValues(incomes = incomes, outcomes = outcomes)

                view.displayValues(values)
            }
        }

        return model.getOperations().asLiveData()
    }

    override fun onChangeIncomeOrOutcome(isIncome: Boolean): List<OperationType> {
        return OperationType.getListByIsIncome(isIncome)
    }

    override fun onAddOperationButtonClick(
        id: Int?,
        type: Int,
        title: String,
        amount: Float,
        isIncome: Boolean,
        isAnnual: Boolean
    ) {
        launch {
            model.insertOperation(
                Operation(
                    id = id,
                    type = type,
                    title = title,
                    amount = amount,
                    isIncome = isIncome,
                    isAnnual = isAnnual
                )
            )
        }
    }

    override fun onDeleteOperationButtonClick(operation: Operation) {
        launch {
            model.deleteOperation(operation)
        }
    }

    override fun onOnOffOperationClick(operation: Operation) {
        operation.invertOnOff()
        launch {
            model.insertOperation(operation)
        }
    }

}