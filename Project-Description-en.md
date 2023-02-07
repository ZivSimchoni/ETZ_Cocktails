# ETZ_Cocktails Description

ETZ Cocktails
First, what is cocktails and why did we choose that idea?

## What is a Cocktail?

A cocktail is an alcoholic mixed drink. Most commonly, cocktails are either a single spirit or combination of spirits, mixed with other ingredients such as juices, flavored syrups, tonic water, shrubs, and bitters.
In modern times, the term cocktail has also come to refer to any mixed drink that contains three or more ingredients, without a spirit.

## Why this idea?

We chose this idea because we love cocktails and we wanted to create an app that will help us and other cocktail lovers to find and save their favorite cocktails.

## What is the app about?

This is Android app that allows everyone search inside the CocktailsDB, either by cocktail name or an ingredient.
Our app allows the user to add custom recipes and save them locally.
The user also can mark as cocktails as favorite and view only the favorite cocktails (in a dedicated fragment).
Moreover, the user is also able to view the details of a cocktail (including the ingredients and the instructions) in a dedicated fragment.
Since cocktails vary in their alcohol content, we also added an option for the user to to create the recipes and mark the content as Alcoholic/Non-Alcoholic/Optional-Alcoholic.

## What is the app's target users?

Our target users are Any cocktail lover, mixologist or Bartender.

## App structure

The app utilizes 1 Activity and 6 fragments:
Search cocktail by name - App entry point (first shown screen)
Search cocktail by ingredient
Favorite cocktails
My cocktails
Add cocktail
Single cocktail display \* Please note that all screens have down navigation bar but 'add cocktail' and 'single cocktail display' fragments.

## App themes

The app has 2 themes
Light mode theme that uses blue and white color palette
Dark mode that utilizes purple and dark color palette

## What is the app's target devices?

The app is designed for Android mobile devices with Android 5.0 and above. (API 21+)
Minimum API: 21
Target API: 32

## Cocktail object structure

Name
Prepare Instructions
5 ingredients & 5 corresponding measurements
Alcohol content (Alcoholic/Non-Alcoholic/Optional-Alcoholic)
Image thumbnail

## Architecture

The app is built using the MVVM (Model, View, View-Model) architectural pattern and makes use of a couple of Android Jetpack components.

### Libraries and dependencies

Kotlin - Official Android programming language, based on Java.
Glide - Image management and caching library for Android.
Dagger & Hilt - dependency injection framework.
Retrofit - API management library.
Android Jetpack
ROOM - a persistence library provides an abstraction layer over SQLite.
LiveData - is an observable data holder, notify views when the underlying database changes.
Lifecycle - perform action when lifecycle state changes.
ViewModel - stores UI-related data that isn't destroyed on UI changes.
API - TheCocktailDB - Our API is free and doesn’t not require any token or fee, just a simple GET request that returns a JSON.

    # API (By names) response structure:
    Drinks
    |____ index
        |____ object

    # API (By Ingredient) response structure:
    Drinks
    |____ 0
        |____ list of cocktail names
