package imobile.androidsample;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<AdItem> {
	Context context;
	LayoutInflater inflater;
	String strAdItemName;
	SharedPreferences shPref;

	public ListAdapter(Context context, int textViewResourceId, List<AdItem> objects) {
		super(context, textViewResourceId, objects);
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {

		if (null == convertView) {
			convertView = inflater.inflate(R.layout.activity_list, null);
		}

		// 行データ(position)の取得
		AdItem item = (AdItem)getItem(position);

        // 広告種類名
        TextView txtAdName = (TextView)convertView.findViewById(R.id.txtAdItemName);
        txtAdName.setText(item.getAdItemName());

        // 広告IDのテキストセット
        final TextView txtAdIds = (TextView)convertView.findViewById(R.id.txtAdID);
        txtAdIds.setText("PID:" + item.getTextAdItemPID() + " MID:" + item.getTextAdItemMID() +  " SID:" + item.getTextAdItemSID());

        // 設定ボタン
		Button btnSettings = (Button)convertView.findViewById(R.id.btnSettings);
		btnSettings.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				final AdItem item = (AdItem)getItem(position);
				strAdItemName = item.getAdItemName();
				//　設定ダイアログ
				// カスタムビューを設定
		        final View layout = inflater.inflate(R.layout.activity_dialog, (ViewGroup)v.findViewById(R.id.layoutDialog));
				// ダイアログ を生成
		        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
		        builder.setTitle(strAdItemName);
		        builder.setView(layout);
		        builder.setPositiveButton("OK", new OnClickListener () {
		            public void onClick(DialogInterface dialog, int which) {
		                // OK
		                EditText edtPID = (EditText)layout.findViewById(R.id.edtPID);
		                EditText edtMID = (EditText)layout.findViewById(R.id.edtMID);
		                EditText edtSID = (EditText)layout.findViewById(R.id.edtSID);

		                // 設定値の保存
		                item.setAdItemPID(edtPID.getText().toString());
		                item.setAdItemMID(edtMID.getText().toString());
		                item.setAdItemSID(edtSID.getText().toString());
		                item.savePreferences(context);
		                
		                // 表示内容の更新
		                txtAdIds.setText("PID:" + item.getTextAdItemPID() + " MID:" + item.getTextAdItemMID() +  " SID:" + item.getTextAdItemSID());
		            }
		        });

		        builder.setNegativeButton("キャンセル", new OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		                // キャンセル
		            }
		        });

		        // 表示
		        builder.create().show();

                EditText edtPID = (EditText)layout.findViewById(R.id.edtPID);
                EditText edtMID = (EditText)layout.findViewById(R.id.edtMID);
                EditText edtSID = (EditText)layout.findViewById(R.id.edtSID);

                // ID値の表示
                edtPID.setText(item.getEditAdItemPID());
                edtMID.setText(item.getEditAdItemMID());
                edtSID.setText(item.getEditAdItemSID());
			}
        });

		return convertView;
	}
}