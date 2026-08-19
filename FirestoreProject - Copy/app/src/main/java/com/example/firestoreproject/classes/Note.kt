package com.example.firestoreproject.classes

class Note(val title: String, val description: String) {
    constructor(): this("", "") //Firebase need this public constructor otherwise the app will crash
}