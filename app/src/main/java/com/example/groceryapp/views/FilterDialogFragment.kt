package com.example.groceryapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import com.example.groceryapp.databinding.FragmentFilterDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterDialogFragment : BottomSheetDialogFragment() {
  lateinit var binding: FragmentFilterDialogBinding
  var filterText:String?=null
    var onFilterItemClick : ((String,String) ->Unit)?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterDialogBinding.inflate(layoutInflater)
        initViews()
        return binding.root
    }
    private fun initViews(){
        binding.filterRG.setOnCheckedChangeListener { radioGroup, i ->
            val radioButton: RadioButton = binding.root.findViewById(i)
            filterText = radioButton.text.toString()

        }
        binding.filterBtn.setOnClickListener {
            if(filterText?.isEmpty() == true){
                Toast.makeText(context,"Please select the option",Toast.LENGTH_LONG).show()
            }else{
                onFilterItemClick?.invoke(filterText!!, binding.filterET.text.toString())
            }
        }
    }

}