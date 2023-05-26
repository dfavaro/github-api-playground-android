Github Api Playground app
==================

Playground App for trying Github APIs.
The app showcases this [API](https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-repositories-for-the-authenticated-user), displaying the repositories of an authenticated user through a Github [Personal access tokens (classic)](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token#creating-a-personal-access-token-classic). 

# Configuration

In order to run, GITHUB_API_KEY containing the [Personal access tokens (classic)](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token#creating-a-personal-access-token-classic) has to be configured as a environmental variable into your local machine or your cloud environment.

## Mac
Once you have the token, you can quickly and temporarily add it to your local variable environment this way:
```
export GITHUB_API_KEY="PERSONAL_TOKEN"
```
You can make Android Studio aware of this temp variable launching it right after from your command line:
```
/Applications/Android\ Studio\ BETA.app/Contents/MacOS/studio 
```

# Technical features

The app is full Kotlin and Jetpack Compose.

# Development Environment

App uses the Gradle build system and for so it can be imported directly into Android Studio (last tested version Android Studio Giraffe | 2022.3.1 Beta 3).

# Build

The app contains the usual `debug` and `release` build variants. So far the is not difference between the two built types.
No flavours have been implemented.

# Architecture

The app follows an `MVVM` architecture and `Version Catalog` system for handling and sharing dependencies and their versions across modules.

# Modularization

The app has been fully modularized and the main modules are:
- `app` which contains the application entry point, the MainActivity;
- `core` which contains all the libraries' modules for delegating reusable business logic, exposing and make the data layer reusable, defining the core navigation, defining the Jetpack Compose theme, defining some Jetpack Compose reusable components, etc...
- `feature` which contains the main UI entry points (screens and eventual viewModels).

# UI

The app follows Material Design 3 guidelines and does support light and dark mode theme as per default Android Studio New Project template. No huge adjustments have been made yet.

# Testing

Unit tests have been implemented in order to test the main business logic.
Still no UI tests.
