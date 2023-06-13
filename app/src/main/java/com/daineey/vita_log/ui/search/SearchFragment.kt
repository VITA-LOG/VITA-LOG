package com.daineey.vita_log.ui.search

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.daineey.vita_log.R
import com.daineey.vita_log.databinding.FragmentSearchBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata
import com.googlecode.tesseract.android.TessBaseAPI
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    lateinit var tess: TessBaseAPI //Tesseract API 객체 생성
    var image: Bitmap? = null
    var datapath: String = "" //데이터 경로 변수 선언

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_search, container, false)

        val SearchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        fun includesForUploadFiles() {
            val storage = Firebase.storage

            // [START upload_create_reference]
            // Create a storage reference from our app
            val storageRef = storage.reference

            // Create a reference to "mountains.jpg"
            val mountainsRef = storageRef.child("sample_eng.png")

            // Create a reference to 'images/mountains.jpg'
            val mountainImagesRef = storageRef.child("images/sample_eng.png")

            // While the file names are the same, the references point to different files
            mountainsRef.name == mountainImagesRef.name // true
            mountainsRef.path == mountainImagesRef.path // false
            // [END upload_create_reference]

            val imageView = v.findViewById<ImageView>(R.id.sample_image)
            // [START upload_memory]
            // Get the data from an ImageView as bytes
            imageView.isDrawingCacheEnabled = true
            imageView.buildDrawingCache()
            val bitmap = (imageView.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            var uploadTask = mountainsRef.putBytes(data)
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
            }.addOnSuccessListener { taskSnapshot ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
            }
            // [END upload_memory]

            // [START upload_file]
            var file = Uri.fromFile(File("path/to/images/sample_eng.png"))
            val riversRef = storageRef.child("images/${file.lastPathSegment}")
            uploadTask = riversRef.putFile(file)

            // Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
            }.addOnSuccessListener { taskSnapshot ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
            }
            // [END upload_file]

            // [START upload_stream]
            val stream = FileInputStream(File("path/to/images/rivers.jpg"))

            uploadTask = mountainsRef.putStream(stream)
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
            }.addOnSuccessListener { taskSnapshot ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
            }
//            // [END upload_stream]
        }

        val counter_num = v.findViewById<TextView>(R.id.ocr_result_view)
        val btn_event = v.findViewById<Button>(R.id.ocr_start_button)

        btn_event.setOnClickListener {
            includesForUploadFiles()
        }

        return v
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}