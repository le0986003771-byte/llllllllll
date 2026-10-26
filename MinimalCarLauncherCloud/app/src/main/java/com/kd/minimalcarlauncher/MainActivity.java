package com.kd.minimalcarlauncher;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.content.Intent;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {
    private final int bg = Color.rgb(16,18,20);
    private final int tile = Color.rgb(31,35,39);
    private final int white = Color.rgb(245,246,247);
    private final int muted = Color.rgb(157,165,172);

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        buildUi();
    }

    private void buildUi() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(28), dp(20), dp(28), dp(20));
        root.setBackgroundColor(bg);

        LinearLayout top = new LinearLayout(this);
        top.setGravity(Gravity.CENTER_VERTICAL);
        top.setOrientation(LinearLayout.HORIZONTAL);
        TextView brand = text("DRIVE  /  HOME", 13, muted, true);
        top.addView(brand, new LinearLayout.LayoutParams(0, dp(32), 1));
        TextView date = text(new SimpleDateFormat("EEE, MMM d", Locale.getDefault()).format(new Date()), 15, muted, false);
        top.addView(date);
        root.addView(top);

        LinearLayout clockBlock = new LinearLayout(this);
        clockBlock.setOrientation(LinearLayout.VERTICAL);
        clockBlock.setPadding(0, dp(16), 0, dp(22));
        TextView clock = text(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()), 52, white, true);
        clock.setLetterSpacing(0.02f);
        clockBlock.addView(clock);
        TextView subtitle = text("KEEP IT SIMPLE.", 12, muted, true);
        subtitle.setLetterSpacing(0.18f);
        clockBlock.addView(subtitle);
        root.addView(clockBlock);

        LinearLayout row1 = new LinearLayout(this);
        row1.setOrientation(LinearLayout.HORIZONTAL);
        row1.addView(card("♫", "MUSIC", "Play your audio", v -> launchMusic()), weightParams());
        row1.addView(card("⌖", "NAVIGATION", "Find your way", v -> launchMaps()), weightParamsWithMargin());
        root.addView(row1);

        LinearLayout row2 = new LinearLayout(this);
        row2.setOrientation(LinearLayout.HORIZONTAL);
        row2.addView(card("ᛒ", "BLUETOOTH", "Connect a device", v -> openBluetooth()), weightParams());
        row2.addView(card("⚙", "SETTINGS", "System settings", v -> openSettings()), weightParamsWithMargin());
        root.addView(row2);

        TextView footer = text("MINIMAL CAR LAUNCHER  •  ANDROID", 10, Color.rgb(100,108,114), true);
        footer.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams fp = new LinearLayout.LayoutParams(-1, dp(30));
        fp.topMargin = dp(10);
        root.addView(footer, fp);
        setContentView(root);
    }

    private LinearLayout card(String symbol, String title, String desc, View.OnClickListener listener) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setGravity(Gravity.CENTER_VERTICAL);
        box.setPadding(dp(22), dp(15), dp(18), dp(14));
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(tile); shape.setCornerRadius(dp(16));
        box.setBackground(shape);
        TextView icon = text(symbol, 27, white, false);
        box.addView(icon);
        TextView name = text(title, 16, white, true);
        LinearLayout.LayoutParams np = new LinearLayout.LayoutParams(-1, -2); np.topMargin = dp(7);
        box.addView(name, np);
        TextView description = text(desc, 12, muted, false);
        LinearLayout.LayoutParams dpms = new LinearLayout.LayoutParams(-1, -2); dpms.topMargin = dp(3);
        box.addView(description, dpms);
        box.setOnClickListener(listener);
        box.setFocusable(true);
        return box;
    }

    private LinearLayout.LayoutParams weightParams() {
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(0, dp(118), 1f);
        p.bottomMargin = dp(12); return p;
    }
    private LinearLayout.LayoutParams weightParamsWithMargin() {
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(0, dp(118), 1f);
        p.leftMargin = dp(12); p.bottomMargin = dp(12); return p;
    }
    private TextView text(String s, int size, int color, boolean bold) {
        TextView t = new TextView(this); t.setText(s); t.setTextSize(size); t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }
    private int dp(int n) { return (int)(n * getResources().getDisplayMetrics().density + 0.5f); }
    private void tryIntent(Intent i, String message) {
        try { if (i.resolveActivity(getPackageManager()) != null) startActivity(i); else Toast.makeText(this, message, Toast.LENGTH_SHORT).show(); }
        catch (Exception e) { Toast.makeText(this, message, Toast.LENGTH_SHORT).show(); }
    }
    private void launchMusic() {
        Intent i = new Intent(Intent.ACTION_MAIN); i.addCategory("android.intent.category.APP_MUSIC");
        tryIntent(i, "找不到音樂播放器，請從車機 App 清單開啟");
    }
    private void launchMaps() {
        Intent i = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps");
        if (i != null) tryIntent(i, ""); else Toast.makeText(this, "尚未安裝 Google 地圖", Toast.LENGTH_SHORT).show();
    }
    private void openBluetooth() { tryIntent(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS), "此車機不支援直接開啟藍牙設定"); }
    private void openSettings() { tryIntent(new Intent(Settings.ACTION_SETTINGS), "無法開啟系統設定"); }
}
