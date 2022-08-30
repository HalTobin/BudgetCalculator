package com.example.budgetcalculator.domain.model

import com.example.budgetcalculator.R

data class OperationType(
    val id: Int = TYPE_IN_UNDEFINED,
    val drawable: Int = R.drawable.type_undefined,
    val isIncome: Boolean = true
) {

    companion object {
        /** Types of incomes **/
        const val TYPE_IN_UNDEFINED = 0x100
        const val TYPE_SALARY = 0x101
        const val TYPE_ALLOWANCES_HELP = 0x102
        const val TYPE_POCKET_MONEY = 0x103
        const val TYPE_DIVIDENDS = 0x104

        /** Types of outcomes **/
        const val TYPE_OUT_UNDEFINED = 0x200
        const val TYPE_RENT = 0x201
        const val TYPE_CREDIT = 0x202
        const val TYPE_INTERNET_PHONE = 0x203
        const val TYPE_INVESTMENT = 0x204
        const val TYPE_SAVINGS = 0x205
        const val TYPE_DONATION = 0x206
        const val TYPE_SUBSCRIPTION = 0x207
        const val TYPE_TAXES = 0x208
        const val TYPE_HOUSE = 0x209
        const val TYPE_BANK = 0x20A
        const val TYPE_SHOPPING = 0x20B

        val types = listOf(
            OperationType(id = TYPE_IN_UNDEFINED, drawable = R.drawable.type_undefined, isIncome = true),
            OperationType(id = TYPE_SALARY, drawable = R.drawable.type_salary, isIncome = true),
            OperationType(id = TYPE_ALLOWANCES_HELP, drawable = R.drawable.type_allowances, isIncome = true),
            OperationType(id = TYPE_POCKET_MONEY, drawable = R.drawable.type_pocket_money, isIncome = true),
            OperationType(id = TYPE_DIVIDENDS, drawable = R.drawable.type_dividends, isIncome = true),

            OperationType(id = TYPE_OUT_UNDEFINED, drawable = R.drawable.type_undefined, isIncome = false),
            OperationType(id = TYPE_RENT, drawable = R.drawable.type_rent, isIncome = false),
            OperationType(id = TYPE_CREDIT, drawable = R.drawable.type_credit, isIncome = false),
            OperationType(id = TYPE_INTERNET_PHONE, drawable = R.drawable.type_internet_phone, isIncome = false),
            OperationType(id = TYPE_INVESTMENT, drawable = R.drawable.type_investment, isIncome = false),
            OperationType(id = TYPE_SAVINGS, drawable = R.drawable.type_savings, isIncome = false),
            OperationType(id = TYPE_DONATION, drawable = R.drawable.type_donation, isIncome = false),
            OperationType(id = TYPE_SUBSCRIPTION, drawable = R.drawable.type_subscription, isIncome = false),
            OperationType(id = TYPE_TAXES, drawable = R.drawable.type_taxes, isIncome = false),
            OperationType(id = TYPE_HOUSE, drawable = R.drawable.type_house, isIncome = false),
            OperationType(id = TYPE_BANK, drawable = R.drawable.type_bank, isIncome = false),
            OperationType(id = TYPE_SHOPPING, drawable = R.drawable.type_shopping, isIncome = false),
        )
    }

}