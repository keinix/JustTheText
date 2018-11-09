# Just the Text
A simple offline OCR app that uses Google's [ML Kit] to extract the text from a photo. Currently Just the Text only supports latin text. 

#### Screen Shots:

Before                     |  After                  
:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/26476452/48248701-c0951800-e43b-11e8-866a-1c077b4422d0.png" height="350">  |  <img src="https://user-images.githubusercontent.com/26476452/48248563-3a78d180-e43b-11e8-9f69-309ec65ee655.png" height="350"> |

## Core Features

* Extract text from a picture
* copy text to the clipboard or share it with other apps 
* bulk copy/share mutiple pictures at one 

## Contributing
If you are interested in contributing to this project below are some good starting points. Additionally, feel free to work on any aspect of the app even if it’s not mentioned in the list below.  

### Features for Future Releases
* better supports for document conversion by adding a selector for [dense text model]
* add data persistence (with [room]) for ConvertedText objects
* add supports for character based languages by utilizing the cloud based API
* add cropping options for pictures
* add support for uploading files from local storage 
* add support for uploading files from cloud storage



## License

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[ML Kit]: https://developers.google.com/ml-kit/
[dense text model]: densehttps://firebase.google.com/docs/reference/android/com/google/firebase/ml/vision/text/FirebaseVisionCloudTextRecognizerOptions.Builder#setModelType(int)
[room]: https://developer.android.com/topic/libraries/architecture/room

