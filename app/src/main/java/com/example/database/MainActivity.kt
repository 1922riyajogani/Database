package com.example.database

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.database.Adapter.StudentAdapter
import com.example.database.DBHelper.DbHelper
import com.example.database.Modelclass.StudentModel
import com.example.database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DbHelper
    lateinit var adapter : StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DbHelper(this)

        var list = dbHelper.getStudents()

        adapter = StudentAdapter {id->

            var dialog = AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are you Sure to Delete?")
                .setPositiveButton("Yes",object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        dbHelper.deleteStudent(id)
                        adapter.update(dbHelper.getStudents())
                    }

                })
                .setNegativeButton("No",object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }

                })
                .create()
            dialog.show()


        }


        adapter.setStudents(list)
        binding.rcvStudentList.layoutManager = LinearLayoutManager(this)
        binding.rcvStudentList.adapter = adapter


        binding.btnAdd.setOnClickListener {
            var name = binding.edtName.text.toString()
            var surname = binding.edtSurname.text.toString()
            var data = StudentModel(0, name, surname)
            dbHelper.addStudents(data)

            adapter.update(dbHelper.getStudents())

        }




    }
}