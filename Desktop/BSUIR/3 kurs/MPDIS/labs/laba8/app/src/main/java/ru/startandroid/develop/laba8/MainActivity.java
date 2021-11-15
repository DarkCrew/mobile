package ru.startandroid.develop.laba8;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spinner;
    private WebView webView;
    private ImageButton imageButton;
    private EditText editText_links;
    private Button back;
    private Button forward;

    @SuppressLint("SetJavaScriptEnabled")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_links = (EditText) findViewById(R.id.editText_links);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        back = (Button) findViewById(R.id.back);
        forward = (Button) findViewById(R.id.forward);

        imageButton.setOnClickListener(this);
        back.setOnClickListener(this);
        forward.setOnClickListener(this);


        // Устанавливаем черный фон и включаем поддержку JavaScript
        webView = (WebView) findViewById(R.id.webview);
        webView.setBackgroundColor(getColor(R.color.darkCrew));
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        //Определяем спиннер
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.links, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    private class MyWebViewClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton:{
                if(editText_links.getText().length() == 0){
                    webView.loadUrl("http://"+ spinner.getSelectedItem().toString());
                    Toast.makeText(this,spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                }
                else{
                    webView.loadUrl("http://"+ editText_links.getText());
                    Toast.makeText(this,editText_links.getText(),Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.back:{
                if(webView.canGoBack()){
                    webView.goBack();
                }
                break;
            }
            case R.id.forward:{
                if(webView.canGoForward()){
                    webView.goForward();
                }
                break;
            }
    }
}}