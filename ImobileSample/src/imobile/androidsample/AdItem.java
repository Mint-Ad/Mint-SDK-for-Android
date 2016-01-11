package imobile.androidsample;

import imobile.androidsample.SpotParams.AdType;

import java.io.Serializable;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class AdItem  implements Serializable {

	private static final long serialVersionUID = -6011129648054077526L;

	private String adItemName;
	private String adItemPID;
    private String adItemMID;
    private String adItemSID;
    private AdType adType;
 
    // コンストラクタ
    public AdItem (AdType adType, Context context) {
    	this.adType = adType;
    	//　プリファレンスから情報を読み込む
		SharedPreferences shPref =  PreferenceManager.getDefaultSharedPreferences(context);
		adItemPID = shPref.getString(adType.toString() + "_PID", SpotParams.PUBLISHER_ID);
		adItemMID = shPref.getString(adType.toString() + "_MID", SpotParams.MEDIA_ID);

		switch (adType) {
			case BANNER:
				adItemName = "バナー（320 x 50）";
				adItemSID = shPref.getString(adType.toString() + "_SID", SpotParams.BANNER_SPOT_ID);
				break;
			case BIG_BANNER:
				adItemName = "ビックバナー（320 x 100）";
				adItemSID = shPref.getString(adType.toString() + "_SID", SpotParams.BIG_BANNER_SPOT_ID);
				break;
			case ICON:
				adItemName = "アイコン";
				adItemSID = shPref.getString(adType.toString() + "_SID", SpotParams.ICON_SPOT_ID);
				break;
			case INTERSTITIAL:
				adItemName = "インタースティシャル";
				adItemSID = shPref.getString(adType.toString() + "_SID", SpotParams.INTERSTITIAL_SPOT_ID);
				break;
			case RECTANGLE_BANNER:
				adItemName = "Mレクタングル（300 x 250）";
				adItemSID = shPref.getString(adType.toString() + "_SID", SpotParams.RECTANGLE_BANNER_SPOT_ID);
				break;
			case TEXT_POPUP:
				adItemName = "テキストポップアップ";
				adItemSID = shPref.getString(adType.toString() + "_SID", SpotParams.TEXT_POPUP_SPOT_ID);
				break;
			case WALL:
				adItemName = "ウォール";
				adItemSID = shPref.getString(adType.toString() + "_SID", SpotParams.WALL_SPOT_ID);
				break;
			default:
				break;
		 }
    }
    
    // 設定情報をプリファレンスに書き込む
    public void savePreferences(Context context) {
		SharedPreferences shPref =  PreferenceManager.getDefaultSharedPreferences(context);
        Editor shPrefEditor = shPref.edit();
        shPrefEditor.putString(adType.toString() + "_PID", adItemPID);
        shPrefEditor.putString(adType.toString() + "_MID", adItemMID);
        shPrefEditor.putString(adType.toString() + "_SID", adItemSID);
        shPrefEditor.commit();
    }
    
    // PID
    public String getAdItemPID() {
		return adItemPID;
	}

    public String getTextAdItemPID() {
		return adItemPID.equals(SpotParams.PUBLISHER_ID) ? "未設定" : adItemPID;
	}

    public String getEditAdItemPID() {
		return adItemPID.equals(SpotParams.PUBLISHER_ID) ? "" : adItemPID;
	}

    public void setAdItemPID(String adItemPID) {
		if (adItemPID.equals("")) { 
	    	this.adItemPID = SpotParams.PUBLISHER_ID;
		} else {
	    	this.adItemPID = adItemPID;
		}
	}

    // MID
	public String getAdItemMID() {
		return adItemMID;
	}
	public String getTextAdItemMID() {
		return adItemMID.equals(SpotParams.MEDIA_ID) ? "未設定" : adItemMID;
	}
	public String getEditAdItemMID() {
		return adItemMID.equals(SpotParams.MEDIA_ID) ? "" : adItemMID;
	}

	public void setAdItemMID(String adItemMID) {
		if (adItemMID.equals("")) { 
	    	this.adItemMID = SpotParams.MEDIA_ID;
		} else {
	    	this.adItemMID = adItemMID;
		}
	}

    // SID
	public String getAdItemSID() {
		return adItemSID;
	}
	public String getTextAdItemSID() {
		return isSpotIdDefault() ? "未設定" : adItemSID;
	}
	public String getEditAdItemSID() {
		return isSpotIdDefault() ? "" : adItemSID;
	}
	private boolean isSpotIdDefault () {
		boolean result = false;
		switch (adType) {
		case BANNER:
			result = adItemSID.equals(SpotParams.BANNER_SPOT_ID);
			break;
		case BIG_BANNER:
			result = adItemSID.equals(SpotParams.BIG_BANNER_SPOT_ID);
			break;
		case ICON:
			result = adItemSID.equals(SpotParams.ICON_SPOT_ID);
			break;
		case INTERSTITIAL:
			result = adItemSID.equals(SpotParams.INTERSTITIAL_SPOT_ID);
			break;
		case RECTANGLE_BANNER:
			result = adItemSID.equals(SpotParams.RECTANGLE_BANNER_SPOT_ID);
			break;
		case TEXT_POPUP:
			result = adItemSID.equals(SpotParams.TEXT_POPUP_SPOT_ID);
			break;
		case WALL:
			result = adItemSID.equals(SpotParams.WALL_SPOT_ID);
			break;
		default:
			break;
		}
		return result;
	}
	
	public void setAdItemSID(String adItemSID) {
		if (adItemSID.equals("")) {
			switch (adType) {
			case BANNER:
				this.adItemSID = SpotParams.BANNER_SPOT_ID;
				break;
			case BIG_BANNER:
				this.adItemSID = SpotParams.BIG_BANNER_SPOT_ID;
				break;
			case ICON:
				this.adItemSID = SpotParams.ICON_SPOT_ID;
				break;
			case INTERSTITIAL:
				this.adItemSID = SpotParams.INTERSTITIAL_SPOT_ID;
				break;
			case RECTANGLE_BANNER:
				this.adItemSID = SpotParams.RECTANGLE_BANNER_SPOT_ID;
				break;
			case TEXT_POPUP:
				this.adItemSID = SpotParams.TEXT_POPUP_SPOT_ID;
				break;
			case WALL:
				this.adItemSID = SpotParams.WALL_SPOT_ID;
				break;
			default:
				break;
			}
		} else {
			this.adItemSID = adItemSID;
		}
	}

	
	// 広告表示名
	public String getAdItemName() {
		return adItemName;
	}

    
}