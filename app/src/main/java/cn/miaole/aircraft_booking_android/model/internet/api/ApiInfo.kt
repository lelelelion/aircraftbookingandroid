package cn.miaole.aircraft_booking_android.model.internet.api

object ApiInfo {
    const val BASE_URL = "http//39.106.138.103:7676"
    const val BAIDU_LOCATION_BASE_URL = "http://api.map.baidu.com"

    /****************************************************************
     *
     * 定位相关
     *
     ****************************************************************/
    const val BAIDU_LOCATION_GET_LOCATION_URL = "/geocoder/v2/"
    const val BAIDU_LOCATION_GET_LOCATION_KEY_VALUE = "R1bBSg3AhddFSGN9fuTAUzgeNXAojnLN"
    //根据经纬度坐标获取地址
    const val BAIDU_LOCATION_GET_LOCATION_LOCATION = "location"
    //坐标的类型，目前支持的坐标类型包括：bd09ll（百度经纬度坐标）、bd09mc（百度米制坐标）
    // 、gcj02ll（国测局经纬度坐标，仅限中国）、wgs84ll（ GPS经纬度） 坐标系说明
    const val BAIDU_LOCATION_GET_LOCATION_COORD_TYPE = "coordtype"
    //ak
    const val BAIDU_LOCATION_GET_LOCATION_KEY = "ak"
    const val BAIDU_LOCATION_GET_LOCATION_CALLBACK = "callback"
    const val BAIDU_LOCATION_GET_LOCATION_OUTPUT = "output"
}