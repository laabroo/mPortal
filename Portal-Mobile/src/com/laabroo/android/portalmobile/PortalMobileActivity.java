package com.laabroo.android.portalmobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class PortalMobileActivity extends Activity {
	private WebView webView;
	private String url = "http://portal.uad.ac.id/mobile/index.php";
	private ProgressDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().requestFeature(Window.FEATURE_PROGRESS);

		setContentView(R.layout.main);
		dialog = new ProgressDialog(this);
		dialog.setMessage("Silahkan tunggu..");
		dialog.show();

		webView = (WebView) findViewById(R.id.webView);

		webView.getSettings().setJavaScriptEnabled(true);
		final Activity activity = this;

		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				activity.setProgress(progress * 1000);
				if (progress == 100 && dialog.isShowing())
					dialog.dismiss();
			}
		});
		webView.setWebViewClient(new WebViewClient() {
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Toast.makeText(activity, "Upss..! " + description,
						Toast.LENGTH_SHORT).show();
			}
		});

		webView.loadUrl(url);

	}
}