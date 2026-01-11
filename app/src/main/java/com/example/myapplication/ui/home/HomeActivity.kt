package com.example.myapplication.ui.home

import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.ui.dialog.DialogInsert
import com.example.myapplication.ui.dialog.DialogItemDelete
import com.example.myapplication.ui.dialog.DialogItemUpdate
import com.example.myapplication.utils.tap

class HomeActivity : BaseActivity<ActivityHomeBinding>(
    inflater = ActivityHomeBinding::inflate
) {
    private lateinit var adapter: HomeAdapter
    private val viewModel: HomeViewModel by viewModels()
    private var dialogItemUpdate: DialogItemUpdate? = null
    val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            // Truyền ngược Uri vào Dialog để hiển thị lên ImageView của Dialog
            dialogItemUpdate?.setImagePreview(it)
        }
    }

    override fun initView() {
        super.initView()
            setupRecyclerView()
            setupObservers()
            setupFloat()

        }

    private fun setupFloat(){
        binding.fabCenterBottom.tap {
            val dialog = DialogInsert()
            dialog.show(supportFragmentManager,"dialog")
        }
    }

    private fun setupRecyclerView() {
        adapter = HomeAdapter().apply {
            onItemClick = { view, item ->
                val popup = PopupMenu(this@HomeActivity, view)
                popup.menuInflater.inflate(R.menu.menu_item_options, popup.menu)

                popup.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.menu_update -> {
                            // 3. Khởi tạo dialog tại đây và truyền item hiện tại vào
                            dialogItemUpdate = DialogItemUpdate(this@HomeActivity, item) {
                                pickImageLauncher.launch("image/*")
                            }
                            dialogItemUpdate?.show()
                            true
                        }
                        R.id.menu_delete -> {
                            DialogItemDelete(this@HomeActivity, item) { itemToDelete ->
                                viewModel.deleteImage(itemToDelete) // Gọi ViewModel
                            }.show()
                            true
                        }
                        else -> false
                    }
                }

                // (Tùy chọn) Hiện icon nếu bạn dùng style cũ hoặc máy đời cao
                // popup.setForceShowIcon(true)

                popup.show()
            }
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservers() {
        // QUAN TRỌNG: Lắng nghe Room thông qua ViewModel
        viewModel.allImages.observe(this) { list ->
            adapter.submitList(list) // Cứ Room có data là RecyclerView tự nhảy
        }

        // Lắng nghe các thông báo lỗi hoặc thành công
        viewModel.statusMessage.observe(this) { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        // Tải dữ liệu lần đầu
        viewModel.syncDataFromApi()
    }
}