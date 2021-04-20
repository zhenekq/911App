package com.zhenekns.android.call911android

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private lateinit var callButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callButton = findViewById(R.id.my_button)
        callButton.setOnClickListener{
            if(isCallPermissionGranted()){
                callThePolice()
            }else{
                requestCallPermission()
            }
        }

    }

    private fun isCallPermissionGranted(): Boolean{
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
    }

    private fun callThePolice(){
        val callUri = Uri.parse("tel:912")
        val callIntent = Intent(Intent.ACTION_CALL, callUri)
        startActivity(callIntent)
    }

    private fun requestCallPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), REQUEST_CODE)
    }

    private companion object{
        private const val REQUEST_CODE = 100
    }
}