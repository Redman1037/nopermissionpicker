package com.mrandroiddev.nopermissionpicker.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.mrandroiddev.nopermissionpicker.NoPermissionPickerConstants
import com.mrandroiddev.nopermissionpicker.NoPermissionPickerType
import com.mrandroiddev.nopermissionpicker.R
import com.mrandroiddev.nopermissionpicker.listeners.OnImagePickedListener
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by Manohar on 03-10-2023.
 */
class NoPermissionPickerActivity : ComponentActivity() {

    private var pickerType = NoPermissionPickerType.GALLERY.value

    private lateinit var galleryImage:ImageView
    private lateinit var cameraImage:ImageView
    private lateinit var closeImage:ImageView
    private var currentPhotoPath:String? = null
    private var currentPhotoName:String? = null

    companion object {
        var onImagePickedListener: OnImagePickedListener? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFinishOnTouchOutside(false)
        window.statusBarColor = 0
//        window.decorView.setBackgroundColor(Color.TRANSPARENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        pickerType = intent.getIntExtra(
            NoPermissionPickerConstants.PICKER_TYPE,
            NoPermissionPickerType.GALLERY.value
        )


        when (pickerType) {
            NoPermissionPickerType.CAMERA_AND_GALLERY.value -> {
                setUIAndClickActions()
            }
            NoPermissionPickerType.GALLERY.value -> {
                openPickerIntent()
            }
            NoPermissionPickerType.CAMERA.value -> {
                openCameraIntent()
            }
        }

    }

    private fun setUIAndClickActions(){
        setContentView(R.layout.activity_main)
        cameraImage = findViewById(R.id.iv_camera)
        galleryImage = findViewById(R.id.iv_gallery)
        closeImage = findViewById(R.id.iv_close)

        cameraImage.setOnClickListener {
            openCameraIntent()
        }

        galleryImage.setOnClickListener {
            openPickerIntent()
        }

        closeImage.setOnClickListener {
            onImagePickedListener?.onImagePicked(null)
            finish()
        }
    }

    val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                takePersistentStorageAccess(uri)
            }
            onImagePickedListener?.onImagePicked(uri)
            finish()
        }

    private fun openPickerIntent() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

    }



    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = Uri.fromFile(File(currentPhotoPath+currentPhotoName))
            onImagePickedListener?.onImagePicked(uri)
            finish()
        }else if (pickerType == NoPermissionPickerType.CAMERA.value){
            onImagePickedListener?.onImagePicked(null)
            finish()
        }
    }

    private fun openCameraIntent() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            // Error occurred while creating the File
            ex.printStackTrace()
            null
        }



        if (photoFile != null){
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                applicationContext.packageName+".provider",
                photoFile
            )
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            resultLauncher.launch(cameraIntent)
        }
    }

    private fun takePersistentStorageAccess(uri:Uri){
        val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
        contentResolver.takePersistableUriPermission(uri, flag)
    }


    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yy-MM-dd-HH-mm-ss-SS", Locale.getDefault()).format(System.currentTimeMillis())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "FNL_${timeStamp}_",
            ".jpg",
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
            currentPhotoName = name
        }
    }

    override fun finish() {
        onImagePickedListener = null
        super.finish()
    }

}