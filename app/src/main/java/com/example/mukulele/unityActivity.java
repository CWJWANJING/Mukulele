package com.example.mukulele;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;

public class unityActivity extends UnityPlayerActivity {

    private LinearLayout mLlUnityContainer;
    TextView noteText;
    AudioDispatcher dispatcher;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_unity);
        initView();

        noteText = findViewById(R.id.noteText);

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.RECORD_AUDIO) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            Log.i("i","permission granted");
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            Log.i("q","shouldShowRequestPermissionRationale");
        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
//            requestPermissionLauncher.launch(
//                    Manifest.permission.RECORD_AUDIO);
        }

        // The following code is from
        // https://medium.com/@juniorbump/pitch-detection-in-android-using-tarsosdsp-a2dd4a3f04e9
        // TarsosDSP comes equipped with its microphone detection
        dispatcher =
                AudioDispatcherFactory.fromDefaultMicrophone(22050,1024,0);

        //TarsosDSP has a simple PitchDetection class
        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult res, AudioEvent e){
                float pitch1 = res.getPitch();
                float pitch2 = res.getPitch();
                float pitch3 = res.getPitch();
                float pitch4 = res.getPitch();
                float pitch5 = res.getPitch();

                final float pitchInHz = (pitch1+pitch2+pitch3+pitch4+pitch5) / 5;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("w","Inside run");
                        processPitch(pitchInHz);
                    }
                });
            }
        };
        AudioProcessor pitchProcessor = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
        dispatcher.addAudioProcessor(pitchProcessor);

        Thread audioThread = new Thread(dispatcher, "Audio Thread");
        audioThread.start();
    }

    private void initView() {
        mLlUnityContainer = (LinearLayout) findViewById(R.id.ll_unity_container);

        //将Unity的View添加到布局里
        View scene = mUnityPlayer.getView();
        mLlUnityContainer.addView(scene);
    }

    public void receiveScore(float scorePercentage){
        Log.i("s", String.valueOf(scorePercentage));
        if (scorePercentage > 60){

        }

    }

    // This function is also from
    // https://medium.com/@juniorbump/pitch-detection-in-android-using-tarsosdsp-a2dd4a3f04e9
    // This will display the pitch and convert to notes
    public void processPitch(float pitchInHz) {
//        pitchText.setText("" + pitchInHz);
        Log.i("p", String.valueOf(pitchInHz));

        if (pitchInHz >= 110 && pitchInHz < 123.47) {
            //A
            noteText.setText("A");
        } else if (pitchInHz >= 123.47 && pitchInHz < 130.81) {
            //B
            noteText.setText("B");
        } else if (pitchInHz >= 130.81 && pitchInHz < 146.83) {
            //C
            noteText.setText("C");
        } else if (pitchInHz >= 146.83 && pitchInHz < 164.81) {
            //D
            noteText.setText("D");
        } else if (pitchInHz >= 164.81 && pitchInHz <= 174.61) {
            //E
            noteText.setText("E");
        } else if (pitchInHz >= 174.61 && pitchInHz < 185) {
            //F
            noteText.setText("F");
        } else if (pitchInHz >= 185 && pitchInHz < 196) {
            //G
            noteText.setText("G");
        } else if (pitchInHz >= 415.3 && pitchInHz < 440) {
            // ukulele A4 note
            noteText.setText("A4");
            UnityPlayer.UnitySendMessage("success1", "correctPitch", "");
        }else  if (pitchInHz >= 466.16 && pitchInHz <= 493.88) {
            // ukulele B4 note
            noteText.setText("B4");
            UnityPlayer.UnitySendMessage("success1", "correctPitch", "");
        } else if (pitchInHz >= 261.63 && pitchInHz < 277.18) {
            // ukulele C4 note
            noteText.setText("C4");
        } else if (pitchInHz >= 369.99 && pitchInHz < 392.00) {
            // ukulele G4 note
            noteText.setText("G4");
            UnityPlayer.UnitySendMessage("success2", "correctPitch", "");
        }else if (pitchInHz >=349.23 && pitchInHz <= 369.99){
            // ukulele F4
            noteText.setText("F4");
            UnityPlayer.UnitySendMessage("success2", "correctPitch", "");
        } else if (pitchInHz >= 311.13 && pitchInHz < 329.63) {
            // ukulele E4 note
            noteText.setText("E4");
            UnityPlayer.UnitySendMessage("success2", "correctPitch", "");
        } else if (pitchInHz >= 830.61 && pitchInHz < 880.00) {
            // ukulele A5 note
            noteText.setText("A5");
        } else if (pitchInHz >= 523.25 && pitchInHz < 554.37) {
            // ukulele C5 note
            noteText.setText("C5");
            UnityPlayer.UnitySendMessage("success1", "correctPitch", "");
        }
        else if (pitchInHz >= 739.99 && pitchInHz < 783.99) {
            // ukulele G5 note
            noteText.setText("G5");
        }
        else if (pitchInHz >= 622.25 && pitchInHz < 659.25) {
            // ukulele E5 note
            noteText.setText("E5");
        }
        else if (pitchInHz >= 1661.22 && pitchInHz < 1760.00) {
            // ukulele A6 note
            noteText.setText("A6");
        }
        else if (pitchInHz >= 1046.50 && pitchInHz < 1108.73) {
            // ukulele C6 note
            noteText.setText("C6");
        }
        else if (pitchInHz >= 1479.98 && pitchInHz < 1567.98) {
            // ukulele G6 note
            noteText.setText("G6");
        }
        else if (pitchInHz >= 1244.51 && pitchInHz < 1318.51) {
            // ukulele E6 note
            noteText.setText("E6");
        }
    }

    public void BackToMain(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}