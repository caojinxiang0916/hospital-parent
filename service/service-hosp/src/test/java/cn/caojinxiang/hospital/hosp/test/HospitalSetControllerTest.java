package cn.caojinxiang.hospital.hosp.test;
import java.util.Arrays;
import java.util.Date;
import com.google.common.collect.Maps;

import cn.caojinxiang.hospital.hosp.HospitalSetApplication;
import cn.caojinxiang.hospital.hosp.controller.HospitalSetController;
import cn.caojinxiang.hospital.result.ApiResult;
import cn.caojinxiang.model.hosp.HospitalSet;
import cn.caojinxiang.vo.hosp.HospitalSetQueryVo;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = HospitalSetApplication.class)
@Slf4j
public class HospitalSetControllerTest {
    @Resource
    private HospitalSetController hospitalSetController;
    @Test
    public void findAll() {
        Long current = 1L;
        Long size = 10L;
        HospitalSetQueryVo hospitalSetQueryVo = new HospitalSetQueryVo();
        hospitalSetQueryVo.setHosname("11北京");
        // hospitalSetQueryVo.setHoscode("");

        ApiResult<IPage<HospitalSet>> pageList = hospitalSetController.getPageList(current, size, hospitalSetQueryVo);
        log.info("\n 打印日志{}", JSON.toJSON(pageList));
    }

    /**
     * 测试分页查询
     */
    @Test
    public void testPageList(){
        HospitalSetQueryVo queryVo = new HospitalSetQueryVo();
        queryVo.setHosname("京");
        ApiResult<IPage<HospitalSet>> pageList = hospitalSetController.getPageList(0L, 10L, queryVo);
        IPage<HospitalSet> data = pageList.getData();
        log.info("\n[data]==> data: {}", JSON.toJSON(data));
    }

    /**
     * 测试添加功能
     */
    @Test
    public void testAdd(){
        HospitalSet hospitalSet = new HospitalSet();
        hospitalSet.setHosname("北京人民医院");
        hospitalSet.setHoscode("1000_2");
        hospitalSet.setApiUrl("http://localhost:8080/bj/hosp");
        hospitalSet.setContactsName("cjx");
        hospitalSet.setContactsPhone("12345678900");
        ApiResult<?> apiResult = hospitalSetController.addHospitalSet(hospitalSet);
        log.info("\n[返回参数为]==> apiResult: {}", JSON.toJSON(apiResult));
    }

    /**
     * 测试获取医院信息
     */
    @Test
    public void testGetHosp(){
        ApiResult<HospitalSet> apiResult = hospitalSetController.getById(1L);
        log.info("\n[ 返回的参数]==> apiResult: {}", apiResult);
    }

    /**
     * 测试删除医院信息
     */
    @Test
    public void testRemoveHosp(){
        ApiResult<?> apiResult = hospitalSetController.deleteByIds(Arrays.asList(1L));
        log.info("\n[返回的参数为]==> apiResult: {}", JSON.toJSON(apiResult));
    }

    /**
     *  测试更新操作
     */
    @Test
    public void testUpdateHosp(){
        ApiResult<HospitalSet> apiResult = hospitalSetController.getById(1L);
        HospitalSet data = apiResult.getData();
        data.setStatus(0);
        ApiResult<?> update = hospitalSetController.update(data);
        log.info("\n[返回的参数为]==> update: {}", JSON.toJSON(update));
    }

}