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
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.SetOptions

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var saveButton: Button
    private lateinit var textViewData: TextView
    private lateinit var loadButton: Button
    private lateinit var updateTitleButton: Button
    private lateinit var deleteDescriptionButton: Button
    private lateinit var deleteNoteButton: Button
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val docRef: DocumentReference = db.collection("Collection").document("First Document")
    private lateinit var listener: ListenerRegistration

    private val KEY_TITLE = "title"
    private val KEY_DESCRIPTION = "description"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editTextTitle = findViewById(R.id.edit_text_title)
        editTextDescription = findViewById(R.id.edit_text_description)
        saveButton = findViewById(R.id.button_save)
        textViewData = findViewById(R.id.textViewData)
        loadButton = findViewById(R.id.loadData)
        updateTitleButton = findViewById(R.id.updateTitle)
        deleteDescriptionButton = findViewById(R.id.delete_description_button)
        deleteNoteButton = findViewById(R.id.delete_note_button)

        saveButton.setOnClickListener {
            save()
        }
        loadButton.setOnClickListener {
            loadData()
        }
        updateTitleButton.setOnClickListener {
            updateTitle()
        }
        deleteDescriptionButton.setOnClickListener {
            deleteDescription()
        }
        deleteNoteButton.setOnClickListener {
            deleteNote()
        }

    }

    // update automatically
    override fun onStart() {
        super.onStart()

        //can be used instead of this, listener =
        docRef.addSnapshotListener(this) { document, error ->
            error?.let {
                return@addSnapshotListener
            }
            document?.let {
                if(it.exists()) {
                    val note = it.toObject(Note::class.java)

                    textViewData.text = "Title: ${note?.title}\n" + "Description: ${note?.description}"
                } else {
                    textViewData.text = ""
                    Toast.makeText(this, "Failed to load data! ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        //can be used instead of this,listener.remove()
    }

    private fun deleteDescription() {
        val note = mutableMapOf<String, Any>()
        note[KEY_DESCRIPTION] = FieldValue.delete()
        docRef.update(note)
    }

    private fun deleteNote() {
        docRef.delete()
    }

    private fun updateTitle() {
        val title = editTextTitle.text.toString()
        val note = mutableMapOf<String, Any>()
        note[KEY_TITLE] = title

        docRef.set(note, SetOptions.merge())
    }

    //store the data in the firebase
    private fun save() {
        val title = editTextTitle.text.toString()
        val description = editTextDescription.text.toString()
//
//        val note = mutableMapOf<String, Any>()
//        note.put(KEY_TITLE, title)
//        note.put(KEY_DESCRIPTION, description)

        //using custom note class
        val note = Note(title, description)

        docRef.set(note).addOnSuccessListener {
            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Note Failed to save! ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadData() {
        docRef.get().addOnSuccessListener { document->
            if(document.exists()) {
                val title = document.getString(KEY_TITLE)
                val description = document.getString(KEY_DESCRIPTION)

                textViewData.text = "Title: ${title}\n" + "Description: ${description}"
            } else {
                Toast.makeText(this, "Failed to load data! ", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this, "Failed to load data! ", Toast.LENGTH_SHORT).show()
        }
    }
}