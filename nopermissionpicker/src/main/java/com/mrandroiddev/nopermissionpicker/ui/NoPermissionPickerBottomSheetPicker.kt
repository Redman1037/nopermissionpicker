package com.mrandroiddev.nopermissionpicker.ui

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mrandroiddev.nopermissionpicker.R


/**
 * Created by Manohar on 04-10-2023.
 */
class NoPermissionPickerBottomSheetPicker:BottomSheetDialogFragment() {
    fun newInstance(): NoPermissionPickerBottomSheetPicker {
        return NoPermissionPickerBottomSheetPicker()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.activity_main, container,
            false
        )
    }


}