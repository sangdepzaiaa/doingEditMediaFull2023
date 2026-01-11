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
import kotlinx.coroutines.withContext

class DialogItemUpdate(
    context: Context,
    private val item: ImageEntity,
    private val onPickImageClick: () -> Unit
) : BaseDialog<DialogItemUpdateBinding>(context, inflater = DialogItemUpdateBinding::inflate) {

    private var selectedImageUri: Uri? = null

    override fun initView() {
        super.initView()



        binding.imgSelectImage.setOnClickListener {
            onPickImageClick.invoke()
        }

        binding.btnUpdateItem.setOnClickListener {
            // Lấy ID từ item cũ để đảm bảo đây là lệnh UPDATE
            val id = item.id
            val title = binding.edtFileName.text.toString()
            val desc = binding.edtSize.text.toString()

            // Logic: Nếu đã chọn ảnh mới (selectedImageUri != null) thì lấy ảnh mới,
            // ngược lại giữ nguyên link ảnh cũ (item.image_url)
            val imageUrl = selectedImageUri?.toString() ?: item.image_url

            val updatedEntity = item.copy(
                title = title,
                description = desc,
                image_url = imageUrl
            )

            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getDatabase(context).imageDao().insertOrUpdate(listOf(updatedEntity))
                withContext(Dispatchers.Main) {
                    dismiss()
                }
            }
        }
    }

    // 2. PHẢI gán uri vào biến selectedImageUri ở đây
    fun setImagePreview(uri: Uri) {
        this.selectedImageUri = uri // Lưu lại để tí nữa lấy ra toString()
        Glide.with(context).load(uri).into(binding.imgSelectImage)
    }
}