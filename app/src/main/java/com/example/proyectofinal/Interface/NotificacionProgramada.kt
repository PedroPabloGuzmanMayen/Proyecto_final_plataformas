package com.example.proyectofinal.Interface

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class NotificacionProgramada: BroadcastReceiver() {
    companion object{
        const val NOTIFICATION_ID = 5
        const val EXTRA_TASK_NAME = "extra_task_name"
    }
    override fun onReceive(p0: Context, p1: Intent?) {

        crearNotifiacion(p0)
    }
    private fun crearNotifiacion(context: Context) {
        val notificacion = NotificationCompat.Builder(context, "CanalAlarmas")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Recordatorio")
            .setContentText("Tienes una tarea pendiente")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Tienes una tarea pendiente")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE)
        as NotificationManager
        manager.notify(NOTIFICATION_ID, notificacion)

    }

}