package com.example.budgetcalculator.domain.model

import androidx.room.PrimaryKey

/** Model for operations, can be an income as well as an outcome **/

data class Operation(
    @PrimaryKey val id: Int? = null,
    var type: Int = OperationType.TYPE_IN_UNDEFINED,
    var name: String = "",
    var income: Float = 0f,
    var isIncome: Boolean = true,
    var isAnnual: Boolean = true,
    var isOn: Boolean = true
) {

    fun invertOnOff() {
        isOn = !isOn
    }

}
