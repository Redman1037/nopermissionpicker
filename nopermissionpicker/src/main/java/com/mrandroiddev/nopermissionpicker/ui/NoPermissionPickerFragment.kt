package com.mrandroiddev.nopermissionpicker.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mrandroiddev.nopermissionpicker.NoPermissionPickerConstants
import com.mrandroiddev.nopermissionpicker.NoPermissionPickerType
import com.mrandroiddev.nopermissionpicker.R
import com.mrandroiddev.nopermissionpicker.listeners.OnImagePickedListener

class NoPermissionPickerFragment:BottomSheetDialogFragment() {


    private var pickerType = NoPermissionPickerType.GALLERY.value

    companion object {
        var onImagePickedListener: OnImagePickedListener? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        activity?.window?.statusBarColor = 0
//        window.decorView.setBackgroundColor(Color.TRANSPARENT)
        activity?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        pickerType = arguments?.getInt(
            NoPermissionPickerConstants.PICKER_TYPE,
            NoPermissionPickerType.GALLERY.value
        )?: NoPermissionPickerType.GALLERY.value

        if (pickerType == NoPermissionPickerType.GALLERY.value) {
            openPickerIntent()
        } else if (pickerType == NoPermissionPickerType.CAMERA.value) {

        }
    }

    private fun openPickerIntent() {
        val pickMedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                NoPermissionPickerActivity.onImagePickedListener?.onImagePicked(uri)
                dismiss()
            }

        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

    }

    override fun dismiss() {
        onImagePickedListener = null
        super.dismiss()
    }

}