package com.mrandroiddev.nopermissionpicker.listeners

import android.net.Uri

/**
 * Created by Manohar on 03-10-2023.
 */
fun interface OnImagePickedListener {
    fun onImagePicked(uri: Uri?)
}