package com.example.groceryapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.groceryapp.databinding.FragmentSortDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SortDialogFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentSortDialogBinding
    var onSortItemClick: ((String) -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSortDialogBinding.inflate(inflater)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.sortRG.setOnCheckedChangeListener { radioGroup, i ->
            val radioButton: RadioButton = binding.root.findViewById(i)
            onSortItemClick?.invoke(radioButton.text.toString())

        }
    }


}