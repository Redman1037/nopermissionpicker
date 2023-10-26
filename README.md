# No Permission Picker
Image picker with out any CAMERA or STORAGE permissions required

# This library is for getting the pictures with out any special permission.
 Please do not add camera permission in Android manifest, If you declare the permission in manifest then it is required to take the permission for intent as well

# Adding to project

Add it in your root build.gradle at the end of repositories

``` gradle
dependencyResolutionManagement {

		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependency

``` gradle
	dependencies {
	        implementation 'com.github.Redman1037:nopermissionpicker:V0.1.2-alpha'
	}
```

 

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


# License
 
The MIT License (MIT)

Copyright (c) 2023 Manohar Reddy

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

