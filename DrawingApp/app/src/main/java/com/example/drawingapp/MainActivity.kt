package com.example.drawingapp

import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import yuku.ambilwarna.AmbilWarnaDialog
import java.io.File
import java.io.FileOutputStream
import java.util.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var drawingView: DrawingView
    private lateinit var brushButton: ImageButton

    private lateinit var purpleButton: ImageButton
    private lateinit var blueButton: ImageButton
    private lateinit var redButton: ImageButton
    private lateinit var orangeButton: ImageButton
    private lateinit var greenButton: ImageButton

    private lateinit var undoButton: ImageButton
    private lateinit var coloPickerButton: ImageButton

    private lateinit var galleryButton: ImageButton
    private lateinit var saveButton: ImageButton

    val openGalleryLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK && result.data?.data != null) {
            findViewById<ImageView>(R.id.gallery_image).setImageURI(result.data?.data)
        }
    }

    val requestPermission: ActivityResultLauncher<Array<String>> = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permission ->
        permission.entries.forEach {
            val permissionName = it.key
            val isGranted = it.value

            if (isGranted && permissionName == android.Manifest.permission.READ_EXTERNAL_STORAGE) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()

                val pickIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                openGalleryLauncher.launch(pickIntent)
            } else if(isGranted && permissionName == android.Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                CoroutineScope(IO).launch{
                    saveImage(getBitmapFromView(findViewById(R.id.drawing_container)))
                }
            } else {
                if (permissionName == android.Manifest.permission.READ_EXTERNAL_STORAGE) {
                    Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        purpleButton = findViewById(R.id.purple_button)
        redButton = findViewById(R.id.red_button)
        blueButton = findViewById(R.id.blue_button)
        orangeButton = findViewById(R.id.orange_button)
        greenButton = findViewById(R.id.green_button)

        undoButton = findViewById(R.id.undo_button)
        coloPickerButton = findViewById(R.id.colorPicker_button)

        galleryButton = findViewById(R.id.gallery_button)
        saveButton = findViewById(R.id.save_button)

        drawingView = findViewById(R.id.drawingView)
        brushButton = findViewById(R.id.brush_button)

        brushButton.setOnClickListener {
            showBrushChooserDialog()
        }

        purpleButton.setOnClickListener(this)
        redButton.setOnClickListener(this)
        blueButton.setOnClickListener(this)
        orangeButton.setOnClickListener(this)
        greenButton.setOnClickListener(this)

        undoButton.setOnClickListener(this)
        coloPickerButton.setOnClickListener(this)

        galleryButton.setOnClickListener(this)
        saveButton.setOnClickListener(this)
    }

    private fun showBrushChooserDialog() {
        val brushDialog = Dialog(this@MainActivity)
        brushDialog.setContentView(R.layout.dialog_brush)
        val seekBarProgress = brushDialog.findViewById<SeekBar>(R.id.dialog_seek_bar)
        val seekBarProgressTv = brushDialog.findViewById<TextView>(R.id.text_view_dialog_seek_bar)

        seekBarProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, p1: Int, p2: Boolean) {
                seekBar?.progress?.let {
                    drawingView.changeBrushSize(seekBar.progress.toFloat())
                    seekBarProgressTv.text = seekBar.progress.toString()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        brushDialog.show()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.purple_button -> {
                drawingView.setColor("#AA65CC")
            }

            R.id.blue_button -> {
                drawingView.setColor("#33B4E4")
            }

            R.id.red_button -> {
                drawingView.setColor("#FA4343")
            }

            R.id.green_button -> {
                drawingView.setColor("#99CC01")
            }

            R.id.orange_button -> {
                drawingView.setColor("#FFBB34")
            }

            R.id.undo_button -> {
                drawingView.undoPath()
            }

            R.id.colorPicker_button -> {
                showColorPickerDialog()
            }

            R.id.gallery_button -> {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestStoragePermission()
                } else {
                    val pickIntent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    openGalleryLauncher.launch(pickIntent)
                }
            }

            R.id.save_button -> {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestStoragePermission()
                } else {
                    val layout = findViewById<FrameLayout>(R.id.drawing_container)
                    val bitmap = getBitmapFromView(layout)

                    CoroutineScope(Main).launch {
                        saveImage(bitmap)
                    }
                }
            }
        }
    }

    private fun showColorPickerDialog() {
        val dialog = AmbilWarnaDialog(
            this, Color.GREEN,
            object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {
                    TODO("Not yet implemented")
                }

                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    drawingView.setColor(color)
                }
            },
        )
        dialog.show()
    }

    private fun requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            showRationalDialog()
        } else {
            requestPermission.launch(
                arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
        }
    }

    private fun showRationalDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Storage Permission")
            .setMessage("We need this permission in order to access the internal Storage")
            .setPositiveButton(R.string.dialog_yes) { dialog, _ ->
                requestPermission.launch(
                    arrayOf(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                )
                dialog.dismiss()
            }
        builder.create().show()
    }

    private fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private suspend fun saveImage(bitmap: Bitmap) {
        val root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
        val myDir = File("$root/saved_images")
        myDir.mkdir()

        val generator = Random()
        var n = 1000
        n = generator.nextInt(n)

        val outputFile = File(myDir, "Images-$n.jpg")

        if (outputFile.exists()) {
            outputFile.delete()
        } else {
            try {
                val out = FileOutputStream(outputFile)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
                out.flush()
                out.close()
            } catch (e: Exception) {
                e.stackTrace
            }

            withContext(Main) {
                Toast.makeText(this@MainActivity, "${outputFile.absolutePath} saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
}