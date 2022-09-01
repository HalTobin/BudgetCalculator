package com.example.budgetcalculator.domain.model

data class Summary(
    var incomesRawAnnual: Float = 0f,
    var incomesRawMonthly: Float = 0f,
    var incomesAnnual: Float = 0f,
    var incomesMonthly: Float = 0f,
    var outcomesAnnual: Float = 0f,
    var outcomesMonthly: Float = 0f
) {

    fun setAnnualValues(incomes: Float, outcomes : Float) {
        incomesRawAnnual = incomes
        incomesRawMonthly = incomesRawAnnual
        incomesAnnual = incomes - outcomes
        incomesMonthly = incomesAnnual / 12f
        outcomesAnnual = outcomes
        outcomesMonthly = outcomesAnnual / 12f
    }

}