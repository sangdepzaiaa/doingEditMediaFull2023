package com.example.myapplication.ui.dialog

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.myapplication.base.BaseDialog
import com.example.myapplication.data.local.AppDatabase
import com.example.myapplication.data.local.AppDatabase.Companion.getDatabase
import com.example.myapplication.data.model.ImageEntity
import com.example.myapplication.databinding.DialogItemUpdateBinding
import com.example.myapplication.ui.home.HomeActivity
import com.example.myapplication.utils.tap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DialogItemUpdate(
    context: Context,
    private val item: ImageEntity, // Nhận item cũ
    private val onPickImageClick: () -> Unit
) : BaseDialog<DialogItemUpdateBinding>(context, inflater = DialogItemUpdateBinding::inflate) {
    private var selectedImageUri: Uri? = null
    override fun initView() {
        super.initView()


        binding.imgSelectImage.setOnClickListener {
            onPickImageClick.invoke()
        }

        binding.btnUpdateItem.setOnClickListener {
            val id = binding.edtFileName.text.toString()
            val title = binding.edtFileName.text.toString()
            val desc = binding.edtSize.text.toString()
            val status = binding.edtStatus.text.toString()
            val imageUrl = selectedImageUri?.toString() ?: ""
            val updatedEntity =
                ImageEntity(
                    id = id,
                    title = title,
                    description = desc,
                    image_url = imageUrl
                )
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getDatabase(context).imageDao().insertOrUpdate(listOf(updatedEntity))
            }
            dismiss()
        }
    }

    fun setImagePreview(uri: Uri) {
        Glide.with(context).load(uri).into(binding.imgSelectImage)
    }

}