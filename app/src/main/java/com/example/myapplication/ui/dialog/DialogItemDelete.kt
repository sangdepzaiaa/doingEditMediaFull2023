package com.example.myapplication.ui.dialog

import android.content.Context
import com.example.myapplication.base.BaseDialog
import com.example.myapplication.data.model.ImageEntity
import com.example.myapplication.databinding.DialogItemDeleteBinding
import com.example.myapplication.utils.tap

class DialogItemDelete(
    context: Context,
    private val item: ImageEntity,
    private val onConfirmDelete: (ImageEntity) -> Unit
) : BaseDialog<DialogItemDeleteBinding>(context, inflater = DialogItemDeleteBinding::inflate) {

    override fun initView() {
        binding.tvNo.tap {
            dismiss()
        }
        binding.tvYes.setOnClickListener {
            onConfirmDelete(item)
            dismiss()
        }
        binding.tvClose.tap {
            dismiss()
        }
    }
}