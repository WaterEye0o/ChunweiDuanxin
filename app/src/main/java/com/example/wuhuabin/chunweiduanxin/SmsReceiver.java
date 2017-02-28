package com.example.wuhuabin.chunweiduanxin;

/**
 * Created by whb on 2017/2/28.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
    private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private static final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("测试", "测试");
        String action = intent.getAction();
        Log.i("测试", "测试");

        if (ACTION_BOOT.equals(action)) {
            Toast.makeText(context, "开机完毕~", Toast.LENGTH_LONG).show();
        }

        Log.i(action.toString(),"action");
        //判断广播消息
        if (action.equals(SMS_RECEIVED_ACTION)) {
            Bundle bundle = intent.getExtras();
            //如果不为空
            if (bundle != null) {
                //将pdus里面的内容转化成Object[]数组
                Object pdusData[] = (Object[]) bundle.get("pdus");// pdus ：protocol data unit  ：
                //解析短信
                SmsMessage[] msg = new SmsMessage[pdusData.length];
                for (int i = 0; i < msg.length; i++) {
                    byte pdus[] = (byte[]) pdusData[i];
                    msg[i] = SmsMessage.createFromPdu(pdus);
                }
                StringBuffer content = new StringBuffer();//获取短信内容
                StringBuffer phoneNumber = new StringBuffer();//获取地址
                //分析短信具体参数
                for (SmsMessage temp : msg) {
                    content.append(temp.getMessageBody());
                    phoneNumber.append(temp.getOriginatingAddress());
                }
                Log.i("发送者号码：" + phoneNumber.toString() + "  短信内容：" + content.toString(),"aaaaaaaaa");
                //可用于发命令执行相应的操作
//                if ("#*location*#".equals(content.toString().trim())){
//                    abortBroadcast();//截断短信广播
//                }else if ("#*alarm*#".equals(content.toString().trim())){
//                    MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.guoge);
//                    //播放音乐
//                    mediaPlayer.start();
//                    abortBroadcast();//截断短信广播
//                }else if ("#*wipe*#".equals(content.toString().trim())){
//                    abortBroadcast();//截断短信广播
//                }else if ("#*lockscreen*#".equals(content.toString().trim())){
//                    abortBroadcast();//截断短信广播
//                }
            }
        }
    }
}