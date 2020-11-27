package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cubee_list.view.*


data class Item(val photo: Int, val name: String)

class MyAdapter constructor(private val layout: Int,
        private val data: ArrayList<Item>): BaseAdapter()
{
    override fun getCount() = data.size
    override fun getItem(position: Int) =  data[position]
    override fun getItemId(position: Int) = 0L
    override fun getView(position: Int, convertView: View?,
                         parent: ViewGroup?): View
    {
        val view = View.inflate(parent?.context, layout,
                null)
        view.name.text = data[position].name
        view.imageView.setImageResource(data[position].photo)
        return view
    }
}

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transNameArray = arrayOf("腳踏車", "機車",
                "汽車", "巴士", "飛機", "船")
        val item1 = ArrayList<Item>()
        val array1 = resources.obtainTypedArray(R.array.tran)
        for(i in 0 until array1.length())
            item1.add(Item(array1.getResourceId(i,
                    0), "${transNameArray[i]}"))
        array1.recycle()
        val cubeeNameArray = arrayOf("哭哭", "發抖",
                "再見", "生氣", "昏倒", "竊笑", "很棒",
                "你好", "驚嚇", "大笑")
        val item2 = ArrayList<Item>()
        val array2 = resources.obtainTypedArray(R.array.cubee)
        for(i in 0 until array2.length())
            item2.add(Item(array2.getResourceId(i,
                    0), "${cubeeNameArray[i]}"))
        array2.recycle()
        spinner.adapter = MyAdapter(R.layout.trans_list, item1)
        gridView.numColumns = 3
        gridView.adapter = MyAdapter(R.layout.cubee_list, item2)
        listView.adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                arrayListOf("訊息1", "訊息2", "訊息3", "訊息4",
                        "訊息5", "訊息6"))
    }
}
