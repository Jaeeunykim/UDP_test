package io.subak.viewmodeltest

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress


class MainActivity : AppCompatActivity() {

    var socket: DatagramSocket
    lateinit var udpViewModel: UDPViewModel
    init {
        socket = DatagramSocket()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        udpViewModel = ViewModelProviders.of(this)[UDPViewModel::class.java]
        Log.e("main","onCreate 들어옴")

        CoroutineScope(Dispatchers.Default).launch {
            try {
                var address = InetAddress.getByName("192.168.0.2")
                val buf = ByteArray(256)
                var packet = DatagramPacket(buf, buf.size, address, 14580)
                socket.send(packet)

                while (true){
                    packet = DatagramPacket(buf, buf.size);
                    socket.receive(packet)

                    var data = packet.length
                    udpViewModel.setBattery(data)

                    Log.e("MainActivity ","들어온 값있음 : $data")
                    delay(1000L)
                }


            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

}