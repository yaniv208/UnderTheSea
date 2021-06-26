package com.hit.underthesea.fragments;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import androidx.annotation.Nullable;

import com.hit.underthesea.R;

public class MusicService extends Service {
    MediaPlayer mediaPlayer;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100,100);
        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.release(); // equivalent to free()
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }




}


