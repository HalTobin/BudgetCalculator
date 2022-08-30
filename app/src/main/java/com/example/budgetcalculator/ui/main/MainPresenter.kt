package com.example.budgetcalculator.ui.main

import com.example.budgetcalculator.domain.model.Operation

class MainPresenter(
    private var view : MainContract.View?,
    private val model: MainContract.Model
) : MainContract.Presenter {



    override fun onAddOperationButtonClick() {
        TODO("Not yet implemented")
    }

    override fun onOnOffOperationClick(operation: Operation) {
        operation.invertOnOff()
    }

}