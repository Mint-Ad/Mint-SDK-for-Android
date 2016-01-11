package imobile.androidsample;

import imobile.androidsample.SpotParams.AdType;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {
    ListAdapter inlineListAdapater;
	ListView inlineListView;

    ListAdapter fullscreenListAdapater;
    ListView fullscreenlistView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart(){
		super.onStart();

		// インライン広告リストデータ作成部分　
        List<AdItem> inlineList = new ArrayList<AdItem>();
        inlineList.add(new AdItem(AdType.BANNER, this));
        inlineList.add(new AdItem(AdType.BIG_BANNER, this));
        inlineList.add(new AdItem(AdType.RECTANGLE_BANNER, this));
        inlineList.add(new AdItem(AdType.ICON, this));

        inlineListAdapater = new ListAdapter(this, 0, inlineList);
        inlineListView = (ListView)findViewById(R.id.listViewInline);
        inlineListView.setAdapter(inlineListAdapater);
        
        //リスト項目がクリックされた時の処理
        inlineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 画面遷移
                Intent intent = new Intent(MainActivity.this, InlineAdActivity.class);
                ListView listView = (ListView) parent;
                intent.putExtra("AdItem", (AdItem)listView.getItemAtPosition(position));
                startActivity(intent);
            }
        });

        // 全画面広告広告リストデータ作成部分　
        List<AdItem> fullscreenList = new ArrayList<AdItem>();
        fullscreenList.add(new AdItem(AdType.INTERSTITIAL, this));
        fullscreenList.add(new AdItem(AdType.WALL, this));
        fullscreenList.add(new AdItem(AdType.TEXT_POPUP, this));

        fullscreenListAdapater = new ListAdapter(this, 0, fullscreenList);
        fullscreenlistView = (ListView)findViewById(R.id.listViewFullscreen);
        fullscreenlistView.setAdapter(fullscreenListAdapater);

        //リスト項目がクリックされた時の処理
        fullscreenlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 画面遷移
                Intent intent = new Intent(MainActivity.this, FullScreenAdActivity.class);
                ListView listView = (ListView) parent;
                intent.putExtra("AdItem", (AdItem)listView.getItemAtPosition(position));
                startActivity(intent);
            }
        });

	}

}
