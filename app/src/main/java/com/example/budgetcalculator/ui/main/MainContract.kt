package com.example.budgetcalculator.ui.main

import androidx.lifecycle.LiveData
import com.example.budgetcalculator.domain.model.Operation
import kotlinx.coroutines.flow.Flow

interface MainContract {

    interface View {
        fun displayOperation(operations: List<Operation>)

        fun displayValues(values: List<Int>)

        fun insertOperation(type: Int, title: String, amount: Float, isIncome: Boolean, isAnnual: Boolean)
    }

    interface Model {
        suspend fun insertOperation(operation: Operation)

        fun getOperations() : Flow<List<Operation>>

        suspend fun deleteOperation(operation: Operation)
    }

    interface Presenter {
        fun observeOperations() : LiveData<List<Operation>>

        fun onAddOperationButtonClick(
            id: Int?,
            type: Int,
            title: String,
            amount: Float,
            isIncome: Boolean,
            isAnnual: Boolean
        )

        fun onDeleteOperationButtonClick(operation: Operation)

        fun onOnOffOperationClick(operation: Operation)
    }

}