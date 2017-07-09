package com.maomishen.memory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firstLoginLogic();
        addSlidePage();

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        setSeparatorColor(ContextCompat.getColor(this, R.color.colorPrimaryLight));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);
    }

    private void addSlidePage() {
        int backgroundColor = ContextCompat.getColor(this, R.color.colorPrimary);
        addSlide(AppIntroFragment.newInstance("title", "description", R.mipmap.ic_launcher, backgroundColor));
        addSlide(AppIntroFragment.newInstance("title2", "description1", R.mipmap.ic_launcher, backgroundColor));
        addSlide(AppIntroFragment.newInstance("title3", "description2", R.mipmap.ic_launcher, backgroundColor));
        addSlide(AppIntroFragment.newInstance("title4", "description3", R.mipmap.ic_launcher, backgroundColor));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        Log.e("onSkipPressed", "onSkipPressed");
        jumpToMainActivity();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        Log.e("done", "done");
        jumpToMainActivity();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
        Log.e("onSlideChanged", "onSlideChanged");
    }

    private void jumpToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void firstLoginLogic() {
        SharedPreferences shared = getSharedPreferences("loginSP", MODE_PRIVATE);
        if(shared.getBoolean("isFirst", true)){
            SharedPreferences.Editor editor=shared.edit();
            editor.putBoolean("isFirst", false);
            UpdateFirstLogin updateFirstLogin = new UpdateFirstLogin();
            updateFirstLogin.doInBackground(editor);
        }else{
            jumpToMainActivity();
        }
    }

    private class UpdateFirstLogin extends AsyncTask<SharedPreferences.Editor, Integer, Void> {

        @Override
        protected Void doInBackground(SharedPreferences.Editor... editors) {
            editors[0].commit();
            return null;
        }
    }
}

