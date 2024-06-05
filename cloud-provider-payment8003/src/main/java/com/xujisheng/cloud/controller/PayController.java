package com.xujisheng.cloud.controller;

import com.xujisheng.cloud.Constant.ResultData;
import com.xujisheng.cloud.entities.Pay;
import com.xujisheng.cloud.entities.PayDTO;
import com.xujisheng.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法,json串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay) {

        try {
            log.info(pay.toString());
            int i = payService.add(pay);
            return ResultData.success("成功插入记录，返回值" + i);
        } catch (Exception e) {
            log.error("新增支付流水号失败{}", e.getMessage());
            return ResultData.fail("新增支付流水号失败", e.getMessage());
        }
    }

    @DeleteMapping("/pay/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable(value = "id") Integer id) {

        try {
            int i = payService.delete(id);
            return ResultData.success(i);
        } catch (Exception e) {
            log.error("删除支付流水失败{}", e.getMessage());
            return ResultData.fail("删除支付流水失败{}", e.getMessage());
        }
    }

    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改", description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        try {
            Pay pay = new Pay();
            BeanUtils.copyProperties(payDTO, pay);

            int i = payService.update(pay);
            return ResultData.success("成功修改，返回值" + i);
        } catch (BeansException e) {
            log.error("修改支付流水号失败{}", e.getMessage());
            return ResultData.fail("修改支付流水号失败", e.getMessage());
        }
    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "按照ID查流水", description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        try {
            Pay pay = payService.getById(id);
            return ResultData.success(pay);
        } catch (Exception e) {
            log.error("按照ID查流水失败{}", e.getMessage());
            return ResultData.fail("按照ID查流水失败", e.getMessage());
        }
    }

    @GetMapping("/pay/getAll")
    @Operation(summary = "查询所有流水", description = "查询支付流水方法")
    public ResultData<List<Pay>> getAll() {

        try {
            List<Pay> payList = payService.getAll();
            return ResultData.success(payList);
        } catch (Exception e) {
            log.error("查询所有流水失败{}", e.getMessage());
            return ResultData.fail("查询所有流水失败", e.getMessage());
        }
    }

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/pay/get/info")
    public String getInfoByConsul( @Value("${xujisheng.info}") String xujishengInfo){
        return "xujishengInfo:" + xujishengInfo + "\t" + "port:" + port;
    }
 }
