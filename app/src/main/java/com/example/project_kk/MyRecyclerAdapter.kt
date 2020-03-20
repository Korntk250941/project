package com.example.project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import androidx.fragment.app.FragmentManager
import org.json.JSONArray
import androidx.fragment.app.FragmentTransaction
import com.example.project_kk.R
import com.example.project_kk.detail
import org.json.JSONObject

class MyRecyclerAdapter(
    context: Context,
    fm: FragmentManager,
    val dataSource: JSONArray
) : RecyclerView.Adapter<MyRecyclerAdapter.Holder>() {

    private val thiscontext : Context = context
    private val fragment: FragmentManager = fm

    class Holder(view : View) : RecyclerView.ViewHolder(view) {

        private val View = view;
        lateinit var layout : LinearLayout
        lateinit var titleTextView: TextView
        lateinit var image: ImageView

        fun Holder(){
            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            titleTextView = View.findViewById<View>(R.id.title) as TextView
            image = View.findViewById<View>(R.id.image) as ImageView
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recylayout, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.Holder()

        holder.titleTextView.setText( dataSource.getJSONObject(position).getString("title").toString() )

        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.image)

        holder.layout.setOnClickListener(){
            Toast.makeText(thiscontext,holder.titleTextView.text.toString(),Toast.LENGTH_SHORT).show()

            val image:String = dataSource.getJSONObject(position).getString("image").toString()
            val title:String = dataSource.getJSONObject(position).getString("title").toString()
            val description:String = dataSource.getJSONObject(position).getString("description").toString()
            val age:String = dataSource.getJSONObject(position).getString("age").toString()

            val detail = detail().newInstance(image,title,description,age)
            val transaction : FragmentTransaction = fragment!!.beginTransaction()
            transaction.replace(com.example.project_kk.R.id.contentContainer,detail,"detail")
            transaction.addToBackStack("detail")
            transaction.commit()
        }
    }
}
