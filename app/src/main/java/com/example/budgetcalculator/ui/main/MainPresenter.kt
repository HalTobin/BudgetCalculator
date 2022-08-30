package com.example.budgetcalculator.ui.main

import com.example.budgetcalculator.domain.model.Operation

class MainPresenter(
    private var view : MainContract.View?,
    private val model: MainContract.Model
) : MainContract.Presenter {

    override fun onAddOperationButtonClick(
        type: Int,
        title: String,
        amount: Float,
        isIncome: Boolean,
        isAnnual: Boolean
    ) {
        model.insertOperation(
            Operation(
                type = type,
                title = title,
                amount = amount,
                isIncome = isIncome,
                isAnnual = isAnnual
            )
        )
    }

    override fun onOnOffOperationClick(operation: Operation) {
        operation.invertOnOff()
    }

}