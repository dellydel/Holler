package com.appsbydel.holler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class WootListAdapter extends ArrayAdapter {
    private List<WootDetails> wootDeals;
    private Context context;

    public WootListAdapter(Context context, List<WootDetails> wootDeals) {
        super(context, R.layout.woot_row, wootDeals);
        this.wootDeals = wootDeals;
        this.context = context;
    }

    @Override
    public int getCount() {
        return wootDeals.size();
    }

    @Override
    public Object getItem(int position) {
        return wootDeals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(getContext());
        10:22:24.150: git commit --only -F C:\Users\Delvin\AppData\Local\Temp\git-commit-msg-2882841596228000076.txt -- app/src/main/java/com/appsbydel/holler/WootListAdapter.java .idea/gradle.xml app/src/main/res/drawable-hdpi/holler_icon.png .idea/modules.xml app/src/main/res/drawable-hdpi/ic_launcher.png build.gradle .idea/compiler.xml app/src/main/res/values/styles.xml gradlew app/app.iml .idea/.name app/src/main/res/drawable-hdpi/wootlogo.png app/src/main/res/values-sw600dp/refs.xml gradle/wrapper/gradle-wrapper.jar .idea/encodings.xml .idea/dictionaries/Delvin.xml Holler.iml settings.gradle app/build.gradle app/src/main/res/layout/activity_main.xml app/src/main/res/values-w820dp/dimens.xml .idea/copyright/profiles_settings.xml app/src/main/res/values/strings.xml .idea/inspectionProfiles/profiles_settings.xml app/src/main/java/com/appsbydel/holler/dummy/DummyContent.java app/src/main/AndroidManifest.xml app/src/main/java/com/appsbydel/holler/Item.java app/src/main/res/drawable-xxhdpi/ic_launcher.png gradle.properties app/src/main/res/drawable-mdpi/ic_launcher.png app/.gitignore .idea/scopes/scope_settings.xml .idea/vcs.xml app/src/androidTest/java/com/appsbydel/holler/ApplicationTest.java app/src/main/res/values/dimens.xml app/src/main/res/drawable-xhdpi/ic_launcher.png app/src/main/res/layout/woot_row.xml .idea/inspectionProfiles/Project_Default.xml app/src/main/res/menu/menu_main.xml app/proguard-rules.pro .gitignore app/src/main/java/com/appsbydel/holler/Photo.java app/src/main/java/com/appsbydel/holler/Offer.java app/src/main/res/values-large/refs.xml gradlew.bat gradle/wrapper/gradle-wrapper.properties app/src/main/java/com/appsbydel/holler/JSONHandler.java .idea/misc.xml app/src/main/java/com/appsbydel/holler/WootDetails.java app/src/main/java/com/appsbydel/holler/MainActivity.java
                [master (root-commit) a5e35dc] initial commit
        50 files changed, 1207 insertions(+)
        create mode 100644 .gitignore
        create mode 100644 .idea/.name
        create mode 100644 .idea/compiler.xml
        create mode 100644 .idea/copyright/profiles_settings.xml
        create mode 100644 .idea/dictionaries/Delvin.xml
        View wootView = inflator.inflate(R.layout.woot_row, parent, false);

        WootDetails woot = (WootDetails) getItem(position);
        TextView txtTitle = (TextView)wootView.findViewById(R.id.txtTitle);
        ImageView wootImage = (ImageView)wootView.findViewById(R.id.imgWoot);

        txtTitle.setText(woot.getTitle());
        wootImage.setImageBitmap(woot.getOffer().getPhotos().get(0).getBitmap());
        return wootView;
    }

}
