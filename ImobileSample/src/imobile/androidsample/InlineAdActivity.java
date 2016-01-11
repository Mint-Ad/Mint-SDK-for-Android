package imobile.androidsample;

import jp.co.imobile.sdkads.android.ImobileSdkAd;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

public class InlineAdActivity extends Activity {
	SharedPreferences shPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_detail);

		Intent intent = getIntent();
		if(intent != null){
			AdItem adItem = (AdItem)intent.getSerializableExtra("AdItem");
			
			// 広告種類名
			TextView txtAdTitle = (TextView) findViewById(R.id.txtAdTitle);
			txtAdTitle.setText(adItem.getAdItemName());
		
			// スポット情報を設定します
			ImobileSdkAd.registerSpotInline(this, adItem.getAdItemPID(), adItem.getAdItemMID(), adItem.getAdItemSID());
			// 広告の取得を開始します
			ImobileSdkAd.start(adItem.getAdItemSID());

			// 広告を表示するViewを作成します
	        FrameLayout imobileAdLayout = new FrameLayout(this);
	        FrameLayout.LayoutParams imobileAdLayoutParam = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        // 広告の表示位置を指定
	        imobileAdLayoutParam.gravity = (Gravity.CENTER | Gravity.CENTER);
	        //広告を表示するLayoutをActivityに追加します
	        addContentView(imobileAdLayout, imobileAdLayoutParam);
	        // 広告を表示します
			ImobileSdkAd.showAd(this, adItem.getAdItemSID(), imobileAdLayout);
		}
	}

	@Override
	protected void onPause() {
		//広告の取得を停止
		ImobileSdkAd.stopAll();
		super.onPause();
	}

	@Override
	protected void onResume() {
		//広告の取得を再開
		ImobileSdkAd.startAll();
		super.onResume();
	}
}
