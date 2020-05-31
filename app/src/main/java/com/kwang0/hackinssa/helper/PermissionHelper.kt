package com.kwang0.hackinssa.helper

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionHelper {
    const val READ_STORAGE_REQUEST_CODE = 4000

    // Read External Storage Permission 이 체크 되어있는지 확인
    fun readStoragePermissionCheck(activity: Activity): Boolean {
        if (ContextCompat.checkSelfPermission(activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                return false
            }
        }
        return true
    }

    // Read EXternal Storage 에 대한 리퀘스트 요청
    fun showReadStorageRequest(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityCompat.requestPermissions(activity,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_STORAGE_REQUEST_CODE)
        }
    }
}