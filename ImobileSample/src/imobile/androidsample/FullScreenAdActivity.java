package imobile.androidsample;

import jp.co.imobile.sdkads.android.ImobileSdkAd;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class FullScreenAdActivity extends Activity {
	SharedPreferences shPref;
	String strAdItemName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_detail2);

		Intent intent = getIntent();
		final AdItem adItem = (AdItem)intent.getSerializableExtra("AdItem");
		// 広告種類名
		TextView txtAdTitle = (TextView) findViewById(R.id.txtAdTitle);
		txtAdTitle.setText(adItem.getAdItemName());

		// スポット情報を設定します
		ImobileSdkAd.registerSpotFullScreen(this, adItem.getAdItemPID(), adItem.getAdItemMID(), adItem.getAdItemSID());
		// 広告の取得を開始します
		ImobileSdkAd.start(adItem.getAdItemSID());

		// ボタン押下時の全画面広告表示
        Button btnShowAd = (Button)findViewById(R.id.btnShowAd);
        btnShowAd.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
		        // 広告を表示します
				ImobileSdkAd.showAd(FullScreenAdActivity.this, adItem.getAdItemSID());
			}
        });
	}

	@Override
	protected void onDestroy(){
		// Activity廃棄時の後処理
		ImobileSdkAd.activityDestory();
		super.onDestroy();
	}
	@Override
	protected void onPause() {
		// 広告の取得を停止
		ImobileSdkAd.stopAll();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// 広告の取得を再開
		ImobileSdkAd.startAll();
		super.onResume();
	}

}
