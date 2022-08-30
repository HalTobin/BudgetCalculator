package com.example.budgetcalculator.ui.main

import android.os.Handler
import com.example.budgetcalculator.domain.model.Operation

class MainModel : MainContract.Model {

    private val operationsList = listOf<Operation>()

    override fun getOperations(onFinishedListener: MainContract.Model.OnFinishedListener?) {
        Handler().postDelayed( { onFinishedListener!!.onFinished(operationsList) }, 1200 )
    }

}