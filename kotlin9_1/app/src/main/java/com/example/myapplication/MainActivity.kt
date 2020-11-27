package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity()
{
    private var rabprogress = 0
    private var turprogress = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_start.setOnClickListener()
        {
            btn_start.isEnabled = false
            rabprogress = 0
            turprogress = 0
            seekBar.progress = 0
            seekBar2.progress = 0
            runThread()
            runCoroutine()
        }
    }

    private fun runThread()
    {
        object: Thread()
        {
            override fun run()
            {
                while (rabprogress <= 100 && turprogress < 100)
                {
                    try
                    {
                        Thread.sleep(100)
                    }catch(e: InterruptedException)
                    {
                        e.printStackTrace()
                    }
                        rabprogress += (Math.random() * 3).toInt()
                        val msg = Message()
                        msg.what = 1
                        mHandler.sendMessage(msg)
                }
            }
        }.start()
    }

    private val mHandler = Handler (Handler.Callback{ msg ->
        when (msg.what)
        {
            1 -> seekBar.progress = rabprogress
        }
        if (rabprogress >= 100 && turprogress < 100)
        {
            Toast.makeText(this,"兔子勝利",
                    Toast.LENGTH_SHORT).show()
            btn_start!!.isEnabled = true
        }
        false
    })

    private fun runCoroutine()
    {
        GlobalScope.launch()
        {
            while (turprogress <= 100 && rabprogress < 100)
            {
                try
                {
                    delay(100L)
                    val msg = Message()
                    msg.what = 1
                    turprogress += (Math.random() * 3).toInt()
                    mHandler2.sendMessage(msg)
                } catch (e: InterruptedException)
                {
                    e.printStackTrace()
                }
            }
        }
    }

    private val mHandler2 = Handler(Handler.Callback{ msg ->
        when (msg.what)
        {
            1 -> seekBar2!!.progress = turprogress
        }
        if (turprogress >= 100 && rabprogress < 100)
        {
            Toast.makeText(this, "烏龜勝利",
                    Toast.LENGTH_SHORT).show()
            btn_start!!.isEnabled = true
        }
        false
    })
}