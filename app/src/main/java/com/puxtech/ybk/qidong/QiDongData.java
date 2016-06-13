package com.puxtech.ybk.qidong;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.puxtech.ybk.MyApplication;
import com.puxtech.ybk.hangqing.HangQingData;
import com.puxtech.ybk.qidong.entity.ContentsServerEntity;
import com.puxtech.ybk.qidong.entity.ContentsServerEnvEntity;
import com.puxtech.ybk.qidong.entity.ContentsServerJysEntity;
import com.puxtech.ybk.qidong.entity.ContentsServerLlEntity;
import com.puxtech.ybk.qidong.entity.ContentsServerServiceEntity;
import com.puxtech.ybk.qidong.entity.ContentsServerYwEntity;
import com.puxtech.ybk.qidong.entity.ContentsServerYwsysEntity;
import com.puxtech.ybk.qidong.entity.ContentsServerZuEntity;
import com.puxtech.ybk.qidong.entity.NormalAppKeyPropertyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanshuo on 16/4/20.
 */
public class QiDongData {
    public final static int JIAO_YI = 1;
    public final static int HANG_QIN = 0;

    public static String CONTENTS_SERVER_URL_1;
    public static String CONTENTS_SERVER_URL_2;
    public static String APP_KEY;

    private NormalAppKeyPropertyEntity normalAppKeyPropertyEntity;
    private ContentsServerEntity contentsServerEntity;
    Context context;
    ContentsServerLlEntity tradeFastLinkEntity, hangQinFastLinkEntity;
    MyApplication myapp;

    public void init(Context context) {
        this.context = context;
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        myapp = (MyApplication) context.getApplicationContext();
        APP_KEY = appInfo.metaData.getString("YBK_APP_KEY");
        CONTENTS_SERVER_URL_1 = appInfo.metaData
                .getString("YBK_CONTENTSERVER_URL_1");
        CONTENTS_SERVER_URL_2 = appInfo.metaData
                .getString("YBK_CONTENTSERVER_URL_2");
    }


    public NormalAppKeyPropertyEntity getNormalAppKeyPropertyEntity() {
        return normalAppKeyPropertyEntity;
    }

    public void setNormalAppKeyPropertyEntity(NormalAppKeyPropertyEntity normalAppKeyPropertyEntity) {
        this.normalAppKeyPropertyEntity = normalAppKeyPropertyEntity;
    }

    public ContentsServerEntity getContentsServerEntity() {
        return contentsServerEntity;
    }

    public void setContentsServerEntity(ContentsServerEntity contentsServerEntity) {
        this.contentsServerEntity = contentsServerEntity;
    }

    private void setTradeFastLinkEntity() {
        ArrayList<ArrayList<ContentsServerLlEntity>> allContentServerLlEntity = getAllContentServerLlEntity();
        ArrayList<ContentsServerLlEntity> tradeList = allContentServerLlEntity.get(JIAO_YI);
        SharedPreferenceManager manager = new SharedPreferenceManager(context, SharedPreferenceManager.LINK);
        tradeFastLinkEntity = tradeList.get(manager.getInt(context, SharedPreferenceManager.LINK_JIAOYI, (int) (Math.random() * tradeList.size())));


    }

    private void setHangQinFastLinkEntity() {
        ArrayList<ArrayList<ContentsServerLlEntity>> allContentServerLlEntity = getAllContentServerLlEntity();
        ArrayList<ContentsServerLlEntity> hangQingList = allContentServerLlEntity.get(HANG_QIN);
        SharedPreferenceManager manager = new SharedPreferenceManager(context, SharedPreferenceManager.LINK);
        hangQinFastLinkEntity = hangQingList.get(manager.getInt(context, SharedPreferenceManager.LINK_HANGQIN, (int) (Math.random() * hangQingList.size())));
    }

    public void setFastLink() {
        setTradeFastLinkEntity();
        setHangQinFastLinkEntity();
    }



    public ArrayList<ArrayList<ContentsServerLlEntity>> getAllContentServerLlEntity() {
        ArrayList<ArrayList<ContentsServerLlEntity>> arrayListArrayList = new ArrayList<>();
        ArrayList<ContentsServerLlEntity> hangQingList = new ArrayList<>();
        ArrayList<ContentsServerLlEntity> tradeList = new ArrayList<>();

        ContentsServerYwsysEntity ywsys = myapp.getQiDongData().getContentsServerEntity()
                .getYwSystemEntity();
        List<ContentsServerJysEntity> jyslist = ywsys.getJysList();
        for (ContentsServerJysEntity jys : jyslist) {
//            if (jys.getCode() == platformNumber) {
            List<ContentsServerEnvEntity> envlist = jys.getEnvList();
            for (ContentsServerEnvEntity env : envlist) {
//                    if (env.getCode() == envirNumber) {
                List<ContentsServerYwEntity> ywlist = env.getYwList();

                for (ContentsServerYwEntity yw : ywlist) {

                    if (yw.getType() == 1) {
                        for (ContentsServerLlEntity ywll : yw
                                .getLlList()) {
                            ywll.setType(QiDongData.JIAO_YI);
                            tradeList.add(ywll);
                        }
                    }

                }
//                    }

            }

//            }
        }
        List<ContentsServerServiceEntity> getServiceList = ywsys.getServiceList();

        for (ContentsServerServiceEntity serviceEntity : getServiceList) {
            // 行情服务前置机地址
            if (serviceEntity.getType() == 1) {
                if (serviceEntity.getZuList() != null && serviceEntity.getZuList().size() > 0) {
                    List<ContentsServerZuEntity> getZuList = serviceEntity.getZuList();
                    for (ContentsServerZuEntity zuEntity : getZuList) {
                        List<ContentsServerLlEntity> getLlList = zuEntity.getLlList();
                        for (ContentsServerLlEntity llEntity : getLlList) {
                            llEntity.setType(QiDongData.HANG_QIN);

                            hangQingList.add(llEntity);


                        }

                    }


//                    ContentsServerZuEntity zuEntity = serviceEntity.getZuList().get(0);
//                    HangQingData.HANGQING_SERVER_URL = zuEntity.getLlList().get(0).getUrl();
                }
            }
        }
        arrayListArrayList.add(hangQingList);
        arrayListArrayList.add(tradeList);
        return arrayListArrayList;
    }

    public ContentsServerLlEntity getTradeFastLinkEntity() {
        return tradeFastLinkEntity;
    }

    public ContentsServerLlEntity getHangQinFastLinkEntity() {
        return hangQinFastLinkEntity;
    }
}
