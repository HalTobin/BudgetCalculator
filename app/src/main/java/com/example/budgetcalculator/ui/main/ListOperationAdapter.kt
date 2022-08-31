package com.example.budgetcalculator.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetcalculator.R
import com.example.budgetcalculator.databinding.ItemOperationBinding
import com.example.budgetcalculator.domain.model.Operation

class ListOperationAdapter(
    context: Context,
    items : List<Operation>,
    listener : OnItemClick
) : RecyclerView.Adapter<ListOperationAdapter.ViewHolder>() {

    private val context: Context
    private var operations: List<Operation>
    private val mCallback: OnItemClick?

    init {
        operations = items
        this.context = context
        this.mCallback = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemOperationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myOperation = operations[position]

        with(myOperation) {
            holder.binding.operationTitle.text = title
            holder.binding.operationAmount.text = amount.toString()
            holder.binding.operationFreq.text = if (isAnnual) "/y" else "/m"
            holder.binding.operationAmount.setTextColor(
                if (isIncome) (ContextCompat.getColor(context, R.color.green))
                else (ContextCompat.getColor(context, R.color.red))
            )
        }

    }

    override fun getItemCount(): Int {
        return operations.size
    }

    fun updateList(operations: List<Operation>) {
        this.operations = operations
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemOperationBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    interface OnItemClick {
        fun onClick(estateId: Int)
    }

}