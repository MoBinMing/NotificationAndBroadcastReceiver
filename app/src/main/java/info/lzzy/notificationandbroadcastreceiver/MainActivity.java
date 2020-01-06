package info.lzzy.notificationandbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //广播注册
        notification();
        initView();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
    }

    //region 广播注册
    private void notification() {
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("Receiver");
        broadcastManager.registerReceiver(updateAdapterReceiver, intentFilter);
    }
    private BroadcastReceiver updateAdapterReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //这里接收到广播和数据，进行处理就是了
            switch (Objects.requireNonNull(intent.getAction())) {
                default:
                    break;
                case "Receiver":
                   tv.setText(intent.getStringExtra("hallo")+System.currentTimeMillis());
                    break;
            }
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent().getAction()!=null){
            if (getIntent().getAction().equals("Main2Activity的Action")){
                Toast.makeText(this, "收到通知栏点击事件，获取到信息为："+getIntent().getAction(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
        bt = (Button) findViewById(R.id.bt);
    }


}
