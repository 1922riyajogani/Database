package com.example.database.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.database.Modelclass.StudentModel
import com.example.database.R

class StudentAdapter(click: (Int) -> Unit) : RecyclerView.Adapter<StudentAdapter.StudentHolder>() {

    var click = click
    lateinit var list : ArrayList<StudentModel>

    class StudentHolder(itemView: View) : ViewHolder(itemView) {
        var txtId = itemView.findViewById<TextView>(R.id.txtId)
        var txtName = itemView.findViewById<TextView>(R.id.txtName)
        var txtSurname = itemView.findViewById<TextView>(R.id.txtSurname)
        var imgDelete = itemView.findViewById<ImageView>(R.id.imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return StudentHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.txtId.text = list.get(position).id.toString()
        holder.txtName.text = list.get(position).name.toString()
        holder.txtSurname.text = list.get(position).surname.toString()

        holder.imgDelete.setOnClickListener {
            click.invoke(list.get(position).id)
        }

    }

    fun update(students: ArrayList<StudentModel>) {
        list = students
        notifyDataSetChanged()
    }

    fun setStudents(list: ArrayList<StudentModel>) {
        this.list = list
    }

}