package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysUserToken;
import com.ruoyi.system.service.ISysUserTokenService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 扫码登录Controller
 * 
 * @author ruoyi
 * @date 2023-08-11
 */
@RestController
@RequestMapping("/system/user/token")
public class SysUserTokenController extends BaseController
{
    @Autowired
    private ISysUserTokenService sysUserTokenService;


    /**
     * 获取扫码登录详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(sysUserTokenService.selectSysUserTokenById(id));
    }

    /**
     * 新增扫码登录
     */
    @Log(title = "扫码登录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserToken sysUserToken)
    {
        return toAjax(sysUserTokenService.insertSysUserToken(sysUserToken));
    }

    /**
     * 修改扫码登录
     */
    @Log(title = "扫码登录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserToken sysUserToken)
    {
        return toAjax(sysUserTokenService.updateSysUserToken(sysUserToken));
    }

    /**
     * 删除扫码登录
     */
    @Log(title = "扫码登录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysUserTokenService.deleteSysUserTokenByIds(ids));
    }
}
