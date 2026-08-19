package com.example.firestoreproject.classes

import com.google.firebase.firestore.Exclude

class Note(val title: String, val description: String, val priority: Int) {
    constructor(): this("", "", 0) //Firebase need this public constructor otherwise the app will crash

    @Exclude
    var id: String = ""
}