package cn.miaole.aircraft_booking_android.model.internet.api

object ApiInfo {
        const val BASE_URL = "http://123.207.96.66:9797"
//    const val BASE_URL = "http://10.0.2.2:9797"
//    const val BASE_URL = "http://172.6.2.185:9797"
    const val BAIDU_LOCATION_BASE_URL = "http://api.map.baidu.com"

    const val REQUIRE_HEADER_AUTHORIZARION = "authorization"
    const val BASE_TOKEN_PREFIX = "Bearer "

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


    /*****************************************************************
     *
     * 用户相关
     *
     *****************************************************************/

    // 登陆
    const val LOGIN_URL = "/login"
    const val LOGIN_PARAM_USERNAME = "username"
    const val LOGIN_PARAM_PASSWORD = "password"

    // 注册
    const val REGISTER_URL = "/register"
    const val REGISTER_PARAM_USERNAME = "username"
    const val REGISTER_PARAM_PASSWORD = "password"
    const val REGISTER_PARAM_PHONE = "phone"
    const val REGISTER_PARAM_EMAIL = "email"
    const val REGISTER_PARAM_NICKNAME = "nickname"

    //更新用户信息
    const val UPDATE_USER_INFO_URL = "/updateUserInfo"
    const val UPDATE_USER_INFO_PARAM_PHONE = "phone"
    const val UPDATE_USER_INFO_PARAM_EMAIL = "email"
    const val UPDATE_USER_INFO_PARAM_NICKNAME = "nickname"
    const val UPDATE_USER_INFO_PARAM_GENDER = "gender"
    const val UPDATE_USER_INFO_PARAM_BIRTHDAY = "birthday"

    //获取用户信息
    const val GET_USER_INFO_URL = "/getUserInfo"

    //更新TOKEN
    const val UPDATE_TOKEN_URL = "/updateToken"

    //修改密码
    const val MODIFY_PASSWORD_URL = "/modifyPassword"
    const val MODIFY_PASSWORD_PARAM_OLD_PASSWORD = "oldPassword"
    const val MODIFY_PASSWORD_PARAM_NEW_PASSWORD = "newPassword"

    //忘记密码
    const val FORGET_PASSWORD_URL = "/forgetPassword"
    const val FORGET_PASSWORD_PARAM_PHONE = "phone"
    const val FORGET_PASSWORD_PARAM_NEW_PASSWORD = "newPassword"
    const val FORGET_PASSWORD_PARAM_CODE = "code"
    const val FORGET_PASSWORD_PARAM_ZONE = "zone"

    //添加乘机人信息
    const val ADD_PASSENGER_CONTACT_URL = "/addPassengerContact"
    const val ADD_PASSENGER_CONTACT_PARAM_NAME = "name"
    const val ADD_PASSENGER_CONTACT_PARAM_CERTIFICATE_TYPE = "certificateType"
    const val ADD_PASSENGER_CONTACT_PARAM_CERTIFICATE_VALUE = "certificateValue"
    const val ADD_PASSENGER_CONTACT_PARAM_PHONE = "phone"
    const val ADD_PASSENGER_CONTACT_PARAM_EMAIL = "email"
    const val ADD_PASSENGER_CONTACT_IS_ADULT = "isAdult"
    const val ADD_PASSENGER_CONTACT_PARAM_BIRTHDAY = "birthday"

    //获取乘机人信息列表
    const val GET_PASSENGER_CONTACTS_URL = "/getPassengerContacts"

    //更新乘机人信息
    const val UPDATE_PASSENGER_CONTACT_URL = "/updatePassengerContact"
    const val UPDATE_PASSENGER_CONTACT_ID = "id"
    const val UPDATE_PASSENGER_CONTACT_PARAM_NAME = "name"
    const val UPDATE_PASSENGER_CONTACT_PARAM_CERTIFICATE_TYPE = "certificateType"
    const val UPDATE_PASSENGER_CONTACT_PARAM_CERTIFICATE_VALUE = "certificateValue"
    const val UPDATE_PASSENGER_CONTACT_PARAM_PHONE = "phone"
    const val UPDATE_PASSENGER_CONTACT_PARAM_EMAIL = "email"
    const val UPDATE_PASSENGER_CONTACT_IS_ADULT = "isAdult"
    const val UPDATE_PASSENGER_CONTACT_PARAM_BIRTHDAY = "birthday"

    //删除乘机人信息
    const val DELETE_PASSENGER_CONTACT_URL = "/deletePassengerContact"
    const val DELETE_PASSENGER_CONTACT_PARAM_ID = "id"

    //获取订单列表
    const val GET_ORDERS_URL = "/getOrders"
    const val GET_ORDERS_PARAM_PAGE = "page"
    const val GET_ORDERS_PARAM_SIZE = "size"

    //删除订单
    const val DELETE_ORDER_URL = "/deleteOrder"
    const val DELETE_ORDER_PARAM_ORDER_ID = "orderId"

    //获取我的行程
    const val GET_TRIPS_URL = "/getTrips"
    const val GET_TRIPS_PARAM_PAGE = "page"
    const val GET_TRIPS_PARAM_SIZE = "size"


    /**********************************************************************
     *
     * 通用接口
     *
     **********************************************************************/
    const val GET_CITIES = "/getCities"


    /**********************************************************************
     *
     * 机票订购相关接口
     *
     **********************************************************************/

    //检索两个城市之间可用的航班
    const val SEARCH_AVALIABLE_FLIGHT_URL = "/searchAvailableFlight"
    const val SEARCH_AVALIABLE_FLIGHT_PARAM_FROM_CITY_CODE = "fromCityCode"
    const val SEARCH_AVALIABLE_FLIGHT_PARAM_TO_CITY_CODE = "toCityCode"
    const val SEARCH_AVALIABLE_FLIGHT_PARAM_DATE = "date"
    const val SEARCH_AVALIABLE_FLIGHT_PARAM_PAGE = "page"
    const val SEARCH_AVALIABLE_FLIGHT_PARAM_SIZE = "size"

    //生成订单
    const val GENERATE_ORDER_URL = "/generateOrder"
    const val GENERATE_ORDER_PARAM_TICKET_ID = "ticketId"
    const val GENERATE_ORDER_PARAM_PASSENGERS = "passengers[]"
    const val GENERATE_ORDER_PARAM_CONTACT_NAME = "contactName"
    const val GENERATE_ORDER_PARAM_PHONE = "phone"
    const val GENERATE_ORDER_PARAM_EMAIL = "email"

    //退票
    const val RETURN_TICKET_URL = "/returnTicket"
    const val RETURN_TICKET_PARAM_ORDER_ID = "orderId"
}