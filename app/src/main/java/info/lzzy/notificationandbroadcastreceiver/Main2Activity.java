package info.lzzy.notificationandbroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;

import info.lzzy.notificationandbroadcastreceiver.data.DataT;

public class Main2Activity extends AppCompatActivity {

    public static NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //region 发送广播
        Intent intent = new Intent("Receiver");
        intent.putExtra("hallo","我是从Main2Activity发送的消息");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        //endregion

        //region 发送通知栏广播
                //1.获取系统通知的管理者
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent1=new Intent(this, MainActivity.class);
        intent1.setAction("Main2Activity的Action");
        intent1.putExtra("i",DataT.i);
        PendingIntent pendingIntent = PendingIntent.getActivity(this , 0 , intent1 , 0);
        //添加自定义视图
        //RemoteViews mRemoteViews = new RemoteViews(getPackageName(),R.layout.tong);
        //mBuilder.setContent(mRemoteViews);
        Notification.Builder mBuilder =new Notification.Builder(this)
                //设置标题
                .setContentTitle("设置标题")
                //设置内容
                .setContentText("设置内容")
                //设置时间
                .setWhen(System.currentTimeMillis())
                //设置小图标
                .setSmallIcon(R.drawable.benchi)
                //设置大图标，当系统下拉时就会看到大图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources() , R.drawable.audi))
                //设置点击时跳转的PendingIntent
                .setContentIntent(pendingIntent)
                //设置点击通知后，通知取消，也可以在代码中去取消
                .setAutoCancel(true);
        mNotificationManager.notify(DataT.i,mBuilder.build());
        DataT.i++;
        //endregion
    }
}
