# Capstone Project / what is...
Sixth and final project at udacity android developer nanodegree program. In this project, I've decided to develop dictionary app using Oxford' API. I've built an app of my own design in two stages. In Stage 1, I've designed and planed the app, using a template that @Udacity provide in the "Instructions" node. In Stage 2, I've built it. 

About the app, it has 2 flavors: premium and free version. In free version, users are able to use only Spanish language. In premium version, users are able to use all features of Oxford' API. App keeps users' searches in history section. Therefore users may access to their old searches easyly. In addition, this feature is available via widget aswell. Whenever users touch their old search on widget, app will open and make that search again.

## Project Overview
In the Capstone project, you will build an app of your own design in two stages. In Stage 1, you will design and plan the app, using a template that we provide in the "Instructions" node. In Stage 2, it's time to build it!

Creating and building your own app idea can be both exciting and daunting; ultimately, we want the experience to be rewarding. You'll apply a wealth of different concepts and components that you've learned across the Nanodegree to bring you own app idea to life.

To keep the process from becoming overwhelming (or simply chaotic), you will design and plan your app, and receive feedback, before you start building. This will help prevent and mitigate pain points you might run into along the way, and also replicates the process of professional Android Developers.

## Why this Project?
To become a proficient Android Developer, you need to design apps and make plans for how to implement them. This will involve choices such as how you will store data, how you will display data to the user, and what functionality to include in the app.

## What Will I Learn?
Through this project, you'll demonstrate the ability to communicate an app idea formally, using:

* An app description.
* UI flow mocks, similar to what you've seen in other Nanodegree projects, like the Popular Movies overview.
* A list of required tasks that you will complete to build the app, in order.

The Capstone project will give you the experience you need to own the full development of an app. This first stage replicates the design and planning experience that proficient Android Developers are expected to demonstrate.

## How Will I Complete this Project?

### **Supporting Courses**

You will use the skills you learned in all of the previous core curriculum to complete this two-stage Capstone Project.

### **App Ideas**
If you don't have an app idea of your own, feel free to choose one of these:

Teleprompter app (we're always on the look-out for a good teleprompter app!)
* Fitness app
* Podcast app
* Travel app
* Reddit app

### **Required Tasks**
1. Review the requirements for the app in this rubric
2. Make a copy of this template
3. Rename the copy: "Capstone_Stage1"
4. Fill out each section:
   - App Description
   - UI Mocks
   - Key Considerations
   - Next Steps: Required Tasks
5. Download the completed document as a PDF, and save it as: "Capstone_Stage1.pdf"
6. Submit the PDF document in a zip file or from a GitHub repo through the project submission portal.

# Rubric

### Common Project Requirements

* App conforms to common standards found in the Android Nanodegree General Project Guidelines
* App is written solely in the Java Programming Language.
* App utilizes stable release versions of all libraries, Gradle, and Android Studio.

### Design and Plan Review

* Proposal contains an overview description.
* Proposal contains a description of the intended user.
* Proposal contains user interface mocks.(*including a UI mock for the app's widget)
* Proposal declares the app’s primary features.
* Proposal outlines any key constraints such as data persistence, UX corner cases, and libraries used. App clearly outlines how a database will be implemented.
* Proposal describes a plan to implement the main features of the app via a set of well structured technical tasks.
* UI mocks depict interaction stories that adhere to Core App quality guidelines.
* App design specification demonstrates implementing all features required for Project 7: Capstone, Stage 2 - Build.

### Core Platform Development

* App integrates a third-party library.
* App validates all input from servers and users. If data does not exist or is in the wrong format, the app logs this fact and does not crash.
* App includes support for accessibility. That includes content descriptions, navigation using a D-pad, and, if applicable, non-audio versions of audio cues.
* App keeps all strings in a strings.xml file and enables RTL layout switching on all layouts.
* App provides a widget to provide relevant information to the user on the home screen.


### Google Play Services

* App integrates two or more Google services. Google service integrations can be a part of Google Play Services or Firebase.
* Each service imported in the build.gradle is used in the app.
* If Location is used, the app customizes the user’s experience by using the device's location.
* If Admob is used, the app displays test ads. If Admob was not used, student meets specifications.
* If Analytics is used, the app creates only one analytics instance. If Analytics was not used, student meets specifications.
* If Maps is used, the map provides relevant information to the user. If Maps was not used, student meets specifications.
* If Identity is used, the user’s identity influences some portion of the app. If Identity was not used, student meets specifications.

### Material Design

* App theme extends AppCompat.
* App uses an app bar and associated toolbars.
* App uses standard and simple transitions between activities.

### Building

* App builds from a clean repository checkout with no additional configuration.
* App builds and deploys using the installRelease Gradle task.
* App is equipped with a signing configuration, and the keystore and passwords are included in the repository. Keystore is referred to by a relative path.
* All app dependencies are managed by Gradle.

### Data Persistence

* App stores data locally either by implementing a ContentProvider OR using Firebase Realtime Database OR using Room. No third party frameworks nor Persistence Libraries may be used.
* Must implement at least one of the three:
If it regularly pulls or sends data to/from a web service or API, app updates data in its cache at regular intervals using a SyncAdapter or JobDispatcher.
**OR**
If it needs to pull or send data to/from a web service or API only once, or on a per request basis (such as a search application), app uses an IntentService to do so.
**OR**
It it performs short duration, on-demand requests(such as search), app uses an AsyncTask.
* If Content provider is used, the app uses a Loader to move its data to its views.
* If Room is used then LiveData and ViewModel are used when required and no unnecessary calls to the database are made.

# Screenshots from the Latest Version of The Application

### _Search Screen Premium Version_
![ScreenShot](/images/Search_Screen_Premium_Version.jpg)

### _Search Screen Free Version_
![ScreenShot](/images/Search_Screen_Free_Version.jpg)

### _History Screen Premium Version_
![ScreenShot](/images/History_Screen_Premium_Version.jpg)

### _More Screen Premium Version_
![ScreenShot](/images/More_Screen_Premium_Version.jpg)

### _More Screen Free Version_
![ScreenShot](/images/More_Screen_Free_Version.jpg)

### _Language Screen Premium Version_
![ScreenShot](/images/Language_Screen_Premium_Version.jpg)

### _Language Screen Free Version_
![ScreenShot](/images/Language_Screen_Free_Version.jpg)

### _Widget_
![ScreenShot](/images/Widget.jpg)
