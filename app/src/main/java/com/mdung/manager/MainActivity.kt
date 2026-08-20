package com.mdung.manager

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import rikka.shizuku.Shizuku

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Tạo giao diện trực tiếp bằng code (không cần file XML layout cho nhanh)
        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(50, 50, 50, 50)
        }

        val titleText = TextView(this).apply {
            text = "MDung System Module Manager"
            textSize = 20f
            setTypeface(null, android.graphics.Typeface.BOLD)
        }

        val statusText = TextView(this).apply {
            text = "Trạng thái Shizuku: Đang kiểm tra..."
            textSize = 16f
            setPadding(0, 30, 0, 30)
        }

        val checkBtn = Button(this).apply {
            text = "Kiểm tra quyền Shizuku"
            setOnClickListener {
                try {
                    if (Shizuku.pingBinder()) {
                        val uid = Shizuku.getUid()
                        statusText.text = "Đã kết nối Shizuku! UID: $uid"
                        Toast.makeText(context, "Shizuku hoạt động tốt!", Toast.LENGTH_SHORT).show()
                    } else {
                        statusText.text = "Chưa kết nối được Shizuku (Hãy bật Shizuku lên)"
                    }
                } catch (e: Exception) {
                    statusText.text = "Lỗi: ${e.message}"
                }
            }
        }

        layout.addView(titleText)
        layout.addView(statusText)
        layout.addView(checkBtn)

        setContentView(layout)
    }
}
