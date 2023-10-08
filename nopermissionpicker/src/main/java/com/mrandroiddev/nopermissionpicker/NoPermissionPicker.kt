package com.mrandroiddev.nopermissionpicker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.mrandroiddev.nopermissionpicker.listeners.OnImagePickedListener
import com.mrandroiddev.nopermissionpicker.ui.NoPermissionPickerActivity
import com.mrandroiddev.nopermissionpicker.ui.NoPermissionPickerFragment

/**
 * Created by Manohar on 03-10-2023.
 */
object NoPermissionPicker {

    fun pickImage(activity: Activity, noPermissionPickerType: NoPermissionPickerType = NoPermissionPickerType.GALLERY, onImagePickedListener: OnImagePickedListener){

        NoPermissionPickerActivity.onImagePickedListener = onImagePickedListener
        val intent = Intent(activity,NoPermissionPickerActivity::class.java)
        intent.putExtra(NoPermissionPickerConstants.PICKER_TYPE,noPermissionPickerType.value)
        activity.startActivity(intent)


//        NoPermissionPickerFragment.onImagePickedListener = onImagePickedListener
//
//        val fragment = NoPermissionPickerFragment()
//        fragment.arguments = Bundle().apply {
//            putInt(NoPermissionPickerConstants.PICKER_TYPE,noPermissionPickerType.value)
//        }
//        fragment.show(fragmentManager,"")

    }

}