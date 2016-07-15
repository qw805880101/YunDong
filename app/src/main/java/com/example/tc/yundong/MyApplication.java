package com.example.tc.yundong;


import android.app.Application;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.tc.yundong.Util.Info;
import com.example.tc.yundong.Util.Utils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.io.File;

public class MyApplication extends Application {

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private double lat, lon; //纬度 , 经度

    @Override
    public void onCreate() {
        super.onCreate();
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "imageloader/Cache"); //缓存文件的存放地址
        ImageLoaderConfiguration imageLoaderConfiguration = ImageLoaderConfiguration.createDefault(this);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .memoryCacheExtraOptions(480, 800) // max width, max height
                .threadPoolSize(10)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)  //降低线程的优先级保证主UI线程不受太大影响
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(5 * 1024 * 1024)) //建议内存设在5-10M,可以有比较好的表现
                .memoryCacheSize(5 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100) //缓存的文件数量
                .discCache(new UnlimitedDiskCache(cacheDir))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) //
                // connectTimeout (5 s), readTimeout (30 s)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
        Info.date = Utils.getTodayNews();
        AutoLayoutConifg.getInstance().useDeviceSize(); //AutoLayout初始化
        Utils.initialize(this); //设置Application
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {  //详细定位
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("gcj02");// 返回的定位结果是百度经纬度，默认值gcj02
        int span = 500;
        option.setScanSpan(span);// 设置发起定位请求的间隔时间为500ms,小于1000时不重复定位
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                location(location);
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                location(location);
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                Utils.Toast("当前城市：" + location.getCity());
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                Utils.Toast("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                Utils.Toast("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                Utils.Toast("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            } else {
                Utils.Toast("定位失败，错误码" + location.getLocType());
            }
            mLocationClient.stop();
        }
    }

    private void location(BDLocation location) {
        Utils.Toast("当前城市：" + location.getCity());
        this.setLat(location.getLatitude());
        this.setLon(location.getLongitude());
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
