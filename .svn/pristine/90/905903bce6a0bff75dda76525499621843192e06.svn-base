package com.puxtech.ybk.qidong;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import com.puxtech.ybk.Constant;
import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.R;
import com.puxtech.ybk.qidong.entity.VersionEntity;
import com.puxtech.ybk.util.ActivityUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by fanshuo on 16/6/12.
 */
public class CheckUpdateManager {

    private Context context;
    private Activity activity;

    public CheckUpdateManager(Activity activity) {
        this.context = activity;
        this.activity = activity;
    }

    private void showYesNoDialog(Context context, String title,
                                       String message, DialogInterface.OnClickListener confirmListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定",
                confirmListener);
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    public static void showMessageDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("检查更新");
        builder.setMessage(message);
        builder.setPositiveButton("确定", null);
        builder.show();
    }

    public void checkUpdate() {
        new AsyncTask<Void, Void, Boolean>() {
            VersionEntity versionEntity;
            Dialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = ActivityUtils.showLoadingProgressDialog(context,
                        "正在检查更新");
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                ContentsServerManager mgr = new ContentsServerManager(context);
                try {
                    ContentsServerHttpSender httpSender = new ContentsServerHttpSender();
                    String requestXml = mgr.createCheckNewVersionRequestData(context);
                    InputStream is = httpSender.requestBinary(
                            requestXml.getBytes(),
                            QiDongData.CONTENTS_SERVER_URL_1);
                    // byte[] data = readStream(is);
                    // String xml = new String(data);
                    DocumentBuilderFactory factory = DocumentBuilderFactory
                            .newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document document = builder.parse(is);
                    // 获取根节点
                    Element root = document.getDocumentElement();
                    ContentsServerParser parser = new ContentsServerParser(
                            context);
                    versionEntity = parser.parseCheckVersion(root);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                dialog.dismiss();
                if (result) {
                    // 需要更新
                    if (versionEntity.getNeedUpdate() == 1) {
                        String text = "";
                        if (TextUtils.isEmpty(versionEntity.getUpdateInfo())) {
                            text = "检测到新版本，版本号："
                                    + versionEntity.getVersionName()
                                    + "，是否立即更新？";
                        } else {
                            text = "版本号：" + versionEntity.getVersionName()
                                    + "\n更新内容：\n"
                                    + versionEntity.getUpdateInfo()
                                    + "\n是否立即下载更新？";
                        }
                        showYesNoDialog(context, "发现新版本", text,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        Uri uri = Uri.parse(versionEntity
                                                .getUpdateUrl());
                                        Intent intent = new Intent(
                                                Intent.ACTION_VIEW, uri);
                                        activity.startActivity(intent);
                                    }
                                });
                    }
                    // 不需要更新
                    else {
                        showMessageDialog(context, "您使用的是最新版本。");
                    }
                } else {
                    ActivityUtils.showCenterToast(context, "检查更新失败",
                            Toast.LENGTH_SHORT);
                }
            }

        }.execute();
    }
}
