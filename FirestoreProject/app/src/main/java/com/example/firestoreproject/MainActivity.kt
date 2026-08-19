package com.example.firestoreproject

import android.net.nsd.NsdManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firestoreproject.classes.Note
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var editTextPriority: EditText
    private lateinit var saveButton: Button
    private lateinit var textViewData: TextView
    private lateinit var loadButton: Button
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val docRef: DocumentReference = db.collection("Notebook").document()
    private val noteBookRef: CollectionReference = db.collection("Notebook")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTitle = findViewById(R.id.edit_text_title)
        editTextDescription = findViewById(R.id.edit_text_description)
        editTextPriority = findViewById(R.id.edit_text_priority)
        saveButton = findViewById(R.id.button_save)
        textViewData = findViewById(R.id.textViewData)
        loadButton = findViewById(R.id.loadData)

        saveButton.setOnClickListener {
            saveNote()
        }
        loadButton.setOnClickListener {
            loadNotes()
        }

    }

    // update automatically
    override fun onStart() {
        super.onStart()

        noteBookRef.whereGreaterThanOrEqualTo("priority",2)
            .orderBy("priority", Query.Direction.DESCENDING)
            .addSnapshotListener(this) { documentSnapshots, exception ->
            exception?.let {
                return@addSnapshotListener
            }

            documentSnapshots?.let {
                var data = ""

                for(documentSnapshot in it) {
                    val note = documentSnapshot.toObject(Note::class.java)
                    note.id = documentSnapshot.id
                    val title = note.title
                    val description = note.description
                    val priority = note.priority

                    data += "ID: " + note.id + "\nTitle: " + title + "\nDescription: " + description +  "\nPriority: " + priority + "\n\n"
                }
                textViewData.text = data
            }

        }
    }

    override fun onStop() {
        super.onStop()
        //can be used instead of this,listener.remove()
    }

    private fun saveNote() {
        val title = editTextTitle.text.toString()
        val description = editTextDescription.text.toString()

        if(editTextPriority.text.toString().isEmpty()) {
            editTextPriority.setText("0")
        }

        val priority = editTextPriority.text.toString().toInt()

        val note = Note(title, description, priority)

        noteBookRef.add(note)
            .addOnSuccessListener {
            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Note Failed to save! ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadNotes() {
        noteBookRef.get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                var data = ""

                for(documentSnapshot in queryDocumentSnapshots) {
                    val note = documentSnapshot.toObject(Note::class.java)

                    note.id = documentSnapshot.id
                    val title = note.title
                    val description = note.description
                    val priority = note.priority

                    data += "ID: " + note.id + "\nTitle: " + title + "\nDescription: " + description + "\nPriority: " + priority + "\n\n"
                }
                textViewData.text = data
            }
    }
}