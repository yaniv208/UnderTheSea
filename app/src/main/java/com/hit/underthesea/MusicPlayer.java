package com.hit.underthesea;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class MusicPlayer {

    private static MusicPlayer instance;
    private MediaPlayer mediaPlayer;
    private boolean pausedByUser = false;

            public static MusicPlayer getInstance() {
                if (instance == null) {
                        instance = new MusicPlayer();
                    }
                return instance;
            }

            public void initialize(Context context) {
                mediaPlayer = MediaPlayer.create(context, R.raw.background_music);
                mediaPlayer.setLooping(true);
                mediaPlayer.setVolume(100,100);
                play(false);
            }

            public void play(boolean byUser) {
                Log.d("byuser play",byUser+"");
                if(byUser) {
                    mediaPlayer.start();
                    pausedByUser=false;
                }else if(!pausedByUser){
                    mediaPlayer.start();
                }
            }

            public void pause(boolean byUser) {
                Log.d("byuser paused",byUser+"");
                pausedByUser = byUser;
                this.mediaPlayer.pause();
           }

           public void pausedByUser (){
                pausedByUser = true;
           }

        }