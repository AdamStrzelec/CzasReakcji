package com.example.user.czasreakcji;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
/**
 * Created by user on 26.03.2018.
 */
public class MusicActivity1 extends Service implements MediaPlayer.OnCompletionListener{
    private static final
    String TAG = "MyService";
    MediaPlayer player;
    @Override
    public
    IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        player = MediaPlayer.create(this, R.raw.energy);
        player.setLooping(true); // Set looping
        player.setOnCompletionListener(this);
    }
    public void onDestroy() {
        if (player.isPlaying()) {
            player.stop();
        }
        player.release();
    }
    @Override
    public int onStartCommand(Intent intent,
                   int flags,
                   int startId) {
        if (!player.isPlaying()) {
            player.start();
        }
        //player.start();
        return START_STICKY;
    }
    public void
    onCompletion(MediaPlayer _mediaPlayer) {
        stopSelf();
    }
}