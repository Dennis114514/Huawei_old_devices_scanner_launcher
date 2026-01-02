package com.launcher.huaweiscanner

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            openHuaweiScanner()
        }, 300)//延迟300ms防止堵塞
    }

    private fun openHuaweiScanner() {
        try {
            val intent = Intent()
            intent.component = android.content.ComponentName(
                "com.huawei.scanner",
                "com.huawei.scanner.view.ScannerActivity"
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            // 如果指定的Activity不存在，显示提示
            Toast.makeText(this, "扫码功能不可用，因为找不到对应的Activity：com.huawei.scanner.view.ScannerActivity", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            //如果应用不存在，显示提示
            e.printStackTrace()
            Toast.makeText(this, "无法启动扫码功能，因为应用未安装: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}