package com.example.bottomnavigation

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.bottomnavigation.ui.main.SectionsPagerAdapter

import com.example.bottomnavigation.ui.main.ProfileDetail
import kotlinx.android.synthetic.main.activity_my_profile_page.*

import kotlinx.android.synthetic.main.activity_profile_completion.*

class ProfileCompletion : AppCompatActivity() {


    private lateinit var btnSubmitForVerification: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_completion)

        setprofileimage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {

                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    requestPermissions(permission, ProfileCompletion.PERMISSION_CODE);
                }
                else {
                    pickImageFromGallery();
                }
            } else {
                pickImageFromGallery();
            }
        }
        val fragmentAdapter = ProfileDetail(supportFragmentManager)
        view_pager.adapter = fragmentAdapter

        tabs.setupWithViewPager(view_pager)
     /*   val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        btnSubmitForVerification= findViewById(R.id.btnSubmitForVerification)
        btnSubmitForVerification.setOnClickListener{
            val intent = Intent(this, ThankYou::class.java)
            startActivity(intent)
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type ="image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object{
        private val IMAGE_PICK_CODE = 1000;
        private  val PERMISSION_CODE =1001;

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_CODE -> {
                if(grantResults.size>0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }
                else{
                    Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            //           profile.setImageURI(data?.data)
        }
    }
}