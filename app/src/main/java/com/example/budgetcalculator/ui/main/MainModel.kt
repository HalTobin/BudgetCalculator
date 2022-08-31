package com.example.budgetcalculator.ui.main

import com.example.budgetcalculator.domain.model.Operation
import com.example.budgetcalculator.domain.use_case.operation.OperationUseCases
import javax.inject.Inject

class MainModel @Inject constructor(
    private val operationUseCases: OperationUseCases
) : MainContract.Model {

    override suspend fun insertOperation(operation: Operation) = operationUseCases.insertOperationUseCase(operation)

    override fun getOperations() = operationUseCases.getOperationsUseCase()

    override suspend fun deleteOperation(operation: Operation) = operationUseCases.deleteOperationUseCase(operation)

}