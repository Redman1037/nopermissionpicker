# No Permission Picker
Image picker with out any CAMERA or STORAGE permissions required

# This library is for getting the pictures with out any special permission.
 Please do not add camera permission in Android manifest, If you declare the permission in manifest then it is required to take the permission for intent as well

# Usage

```kotlin
 NoPermissionPicker.pickImage(
            activity,
            noPermissionPickerType = NoPermissionPickerType.CAMERA_AND_GALLERY
        ) { uriResult ->
            if (uriResult != null) {
               //Do some thing
            }
            
        }
```

-> noPermissionPickerType can be of 3 Types 
```
 noPermissionPickerType = NoPermissionPickerType.CAMERA_AND_GALLERY   // Shows a dialog to pick either from camera or from gallery
 noPermissionPickerType = NoPermissionPickerType.CAMERA        // Launches the camera intent directly
 noPermissionPickerType = NoPermissionPickerType.GALLERY      // Launches the new photo picker API
```

# Future plans

1. Add support for multi image pick
2. Customization settings
3. Add Support for video , mp3 etc


