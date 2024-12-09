package com.capstone.setara.fragments

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.capstone.setara.MainActivity
import com.capstone.setara.R
import com.capstone.setara.view.AboutUsActivity
import com.capstone.setara.view.SignUpInActivity
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Ambil informasi pengguna saat ini
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val accountInfo = view.findViewById<TextView>(R.id.accountInfo)

        // Perbarui nama/email di TextView
        if (currentUser != null) {
            accountInfo.text = currentUser.email ?: "Pengguna tidak memiliki email"
        } else {
            accountInfo.text = "Pengguna tidak terdaftar"
        }
        // Buat Notification Channel
        createNotificationChannel()

        // Fitur Notifikasi
        val notificationLayout = view.findViewById<LinearLayout>(R.id.notificationLayout)
        notificationLayout.setOnClickListener {
            showNotification()
        }

        // Fitur About Us
        val aboutUsLayout = view.findViewById<LinearLayout>(R.id.aboutUsLayout)
        aboutUsLayout.setOnClickListener {
            val intent = Intent(requireContext(), AboutUsActivity::class.java)
            startActivity(intent)
        }
        // Fitur Logout
        val logoutLayout = view.findViewById<LinearLayout>(R.id.logoutLayout)
        logoutLayout.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            auth.signOut()

            val intent = Intent(requireContext(), SignUpInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            Toast.makeText(context, "Anda telah logout", Toast.LENGTH_SHORT).show()
        }


        return view
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "notification_channel_id"
            val channelName = "Default Channel"
            val channelDescription = "Channel untuk notifikasi"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }

            val notificationManager =
                requireContext().getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification() {
        val channelId = "notification_channel_id"
        val notificationId = 1

        // Cek izin POST_NOTIFICATIONS untuk Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1001
            )
            return
        }

        // Intent untuk membuka aplikasi saat notifikasi diklik
        val intent = Intent(requireContext(), MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            requireContext(), 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Bangun notifikasi
        val notificationBuilder = NotificationCompat.Builder(requireContext(), channelId)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Notifikasi Baru")
            .setContentText("Ini adalah contoh notifikasi.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // Hapus notifikasi setelah diklik

        // Tampilkan notifikasi
        val notificationManager = NotificationManagerCompat.from(requireContext())
        notificationManager.notify(notificationId, notificationBuilder.build())

        Toast.makeText(context, "Notifikasi ditampilkan", Toast.LENGTH_SHORT).show()
    }
}
