package cn.caojinxiang.hospital.hosp.controller;

import cn.caojinxiang.hospital.hosp.service.HospitalSetService;
import cn.caojinxiang.hospital.result.Result;
import cn.caojinxiang.model.hosp.HospitalSet;
import cn.caojinxiang.vo.hosp.HospitalQueryVo;
import cn.caojinxiang.vo.hosp.HospitalSetQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cjx
 */
@RestController
@RequestMapping
public class HospitalSetController {

    @Resource
    private HospitalSetService hospitalSetService;

    /**
     * 查询所有的医院
     */
    @GetMapping("getPageList/{current}/{size}")
    @ApiOperation(value = "查询所有的医院")
    public Result<IPage<HospitalSet>> getPageList(@PathVariable("current") Long current,
                                                 @PathVariable("size") Long size,
                                                 HospitalSetQueryVo query){
        // 设置分页
        Page<HospitalSet> page = new Page<>(current, size);
        // 设置查询条件
        LambdaQueryWrapper<HospitalSet> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(query.getHoscode()), HospitalSet::getHoscode, query.getHoscode())
                .like(StringUtils.hasLength(query.getHosname()), HospitalSet::getHosname, query.getHosname());
        IPage<HospitalSet> iPage = hospitalSetService.page(page, wrapper);
        return Result.ok(iPage);

    }
}
