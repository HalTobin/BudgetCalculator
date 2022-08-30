package com.example.budgetcalculator.ui.main

import com.example.budgetcalculator.domain.model.Operation

interface MainContract {

    interface View {
        fun displayOperation(operations: List<Operation>)

        fun displayValues(values: List<Int>)

        fun insertOperation(type: Int, title: String, amount: Float, isIncome: Boolean, isAnnual: Boolean)
    }

    interface Model {
        // nested interface to be
        interface OnFinishedListener {
            // function to be called
            // once the Handler of Model class
            // completes its execution
            fun onFinished(list: List<Operation>)
        }

        fun getOperations(onFinishedListener: OnFinishedListener?)
    }

    interface Presenter {
        // method to be called when
        // the button is clicked
        fun onAddOperationButtonClick()

        // method to destroy
        // lifecycle of MainActivity
        fun onOnOffOperationClick(operation: Operation)
    }

}