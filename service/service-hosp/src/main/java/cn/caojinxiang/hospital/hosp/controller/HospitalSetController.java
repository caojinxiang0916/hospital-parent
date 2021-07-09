package cn.caojinxiang.hospital.hosp.controller;
import java.util.Date;

import cn.caojinxiang.hospital.util.MD5;
import com.google.common.collect.Maps;

import cn.caojinxiang.hospital.hosp.service.HospitalSetService;
import cn.caojinxiang.hospital.result.ApiResult;
import cn.caojinxiang.model.hosp.HospitalSet;
import cn.caojinxiang.vo.hosp.HospitalSetQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

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
     * @param current 当前页
     * @param size 每页大小
     * @param query 查询的条件
     * @return mp的分页列表 IPage<List<HospitalSet>>
     */
    @GetMapping("getPageList/{current}/{size}")
    @ApiOperation(value = "查询所有的医院")
    public ApiResult<IPage<HospitalSet>> getPageList(@PathVariable("current") Long current,
                                                     @PathVariable("size") Long size,
                                                     HospitalSetQueryVo query){
        // 设置分页
        Page<HospitalSet> page = new Page<>(current, size);
        // 设置查询条件
        LambdaQueryWrapper<HospitalSet> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(query.getHoscode()), HospitalSet::getHoscode, query.getHoscode())
                .like(StringUtils.hasLength(query.getHosname()), HospitalSet::getHosname, query.getHosname());
        IPage<HospitalSet> iPage = hospitalSetService.page(page, wrapper);
        return ApiResult.ok(iPage);
    }

    /**
     * 修改医院信息
     * @param hospitalSet 要修改的医院对象
     * @return apiResult
     */
    @PutMapping("update")
    @ApiOperation(value = "修改医院信息")
    public ApiResult<?> update(HospitalSet hospitalSet){
        boolean update = hospitalSetService.updateById(hospitalSet);
        if (update) {
            return ApiResult.ok();
        }
        return ApiResult.fail().setMessage("更新失败");
    }

    /**
     * 批量删除医院信息
     * @param ids 医院信息的主键集合
     * @return apiResult
     */
    @DeleteMapping("deleteByIds")
    @ApiOperation(value = "删除医院信息")
    public ApiResult<?> deleteByIds(@RequestBody List<Long> ids){
        boolean remove = hospitalSetService.removeByIds(ids);
        if (remove) {

            return ApiResult.ok();
        }
        return ApiResult.fail().setMessage("删除失败");

    }

    /**
     * 添加医院信息
     * @param hospitalSet 要添加的医院对象
     * @return apiResult<?>
     */
    @PostMapping("addHospitalSet")
    @ApiOperation(value = "添加医院信息")
    public ApiResult<?> addHospitalSet(HospitalSet hospitalSet){
        //设置状态 1 使用 0 不能使用
        hospitalSet.setStatus(1);
        //签名秘钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        // 调用service
        boolean save = hospitalSetService.save(hospitalSet);
        if (save) {
            return ApiResult.ok();
        }
        return ApiResult.fail();

    }

    /**
     * 获取医院信息
     * @param id  主键
     * @return apiResult<HospitalSet>
     */
    @GetMapping("getById/{id}")
    @ApiOperation(value = "获取医院信息")
    public ApiResult<HospitalSet> getById(@PathVariable Long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return ApiResult.ok(hospitalSet);

    }

    /**
     * 医院信息锁定和解锁
     * @param id 医院信息主键id
     * @param status 设置状态
     * @return apiResult<?>
     */
    @PutMapping("lockHosp/{id}")
    @ApiOperation(value = "医院信息锁定和解锁")
    public ApiResult<?> lockHosp(@PathVariable("id") Long id, Integer status ){
        HospitalSet hospitalSet = getById(id).getData();
        hospitalSet.setStatus(status);
        boolean update = hospitalSetService.updateById(hospitalSet);
        if (update) {
            return ApiResult.ok();
        }
        return ApiResult.fail();
    }


    /**
     * 发送签名密钥
     * @param id
     * @return
     */
    @PutMapping("sendKey/{id}")
    public ApiResult<?> lockHospitalSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //TODO 发送短信
        return ApiResult.ok();
    }
}
