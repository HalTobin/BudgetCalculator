package com.example.budgetcalculator.ui.main

import android.os.Handler
import com.example.budgetcalculator.domain.model.Operation
import com.example.budgetcalculator.domain.use_case.operation.OperationUseCases
import javax.inject.Inject

class MainModel @Inject constructor(
    private val operationUseCase: OperationUseCases
) : MainContract.Model {

    private val operationsList = listOf<Operation>()

    override suspend fun insertOperation(operation: Operation) {
        operationUseCase.insertOperationUseCase(operation)
    }

    override fun getOperations(onFinishedListener: MainContract.Model.OnFinishedListener?) {
        Handler().postDelayed( { onFinishedListener!!.onFinished(operationsList) }, 1200 )
    }

}