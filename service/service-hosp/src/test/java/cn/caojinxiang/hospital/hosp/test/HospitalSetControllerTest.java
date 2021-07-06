package cn.caojinxiang.hospital.hosp.test;

import cn.caojinxiang.hospital.hosp.HospitalSetApplication;
import cn.caojinxiang.hospital.hosp.controller.HospitalSetController;
import cn.caojinxiang.hospital.result.Result;
import cn.caojinxiang.model.hosp.HospitalSet;
import cn.caojinxiang.vo.hosp.HospitalSetQueryVo;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

        Result<IPage<HospitalSet>> pageList = hospitalSetController.getPageList(current, size, hospitalSetQueryVo);
        log.info("\n 打印日志{}", JSON.toJSON(pageList));

    }
}