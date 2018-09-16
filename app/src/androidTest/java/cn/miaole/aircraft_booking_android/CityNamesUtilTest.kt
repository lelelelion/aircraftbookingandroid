package cn.miaole.aircraft_booking_android

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import cn.miaole.aircraft_booking_android.model.data.Province
import cn.miaole.aircraft_booking_android.utils.CityNamesUtil
import cn.miaole.aircraft_booking_android.utils.easyToObj
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CityNamesUtilTest {

    /**
     * 测试从assets文件夹中读取出json数据，并用Gson转为对象是否能成功
     */
    @Test
    fun readConfig() {
        val context = InstrumentationRegistry.getTargetContext()
        CityNamesUtil.init(context, "test.json")
                .apply {
                    Assert.assertTrue(this != "")
                    Assert.assertEquals(this.easyToObj(Province::class.java)
                            .provinceName, "河北省")
                }
    }
}