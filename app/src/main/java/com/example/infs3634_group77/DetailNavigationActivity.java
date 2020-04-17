package com.example.infs3634_group77;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailNavigationActivity extends AppCompatActivity {

    //NOTE: only UI elements on this are navigation, fragment 'holder, and help button

    //Navigation bottom bar tutorial on youtube: https://www.youtube.com/watch?v=tPV8xA7m-iw
    // with navigation drawer (like in gmail app) tutorial on youtube: https://www.youtube.com/watch?v=fGcMLu1GJEc
            // part 2: https://www.youtube.com/watch?v=zYVEMCiDcmY
            // part 3 switching between fragments depending on navigation button click: https://www.youtube.com/watch?v=bjYstsO1PgI

    //navigation bar at the bottom with 3 sections
        //  home (categories fragment with horizontal recycler view> learning fragment > fightscreenactivity> scorescreen fragment, back to home)
        //  search (dictionary bascially. have a fragment with text field, then search,
                     //  then query api, then return defition with image if available) LEAVE TILL LAST. delete if no time

        //  settings (profile name dummy data w/e, language drop down box, disclaimer)

    //consider a horizontal recycler view: https://www.youtube.com/watch?v=UsXv6VRqZKs


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_navigation);
    }
}
