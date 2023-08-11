package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserTokenMapper;
import com.ruoyi.system.domain.SysUserToken;
import com.ruoyi.system.service.ISysUserTokenService;

/**
 * 扫码登录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-11
 */
@Service
public class SysUserTokenServiceImpl implements ISysUserTokenService 
{
    @Autowired
    private SysUserTokenMapper sysUserTokenMapper;

    /**
     * 查询扫码登录
     * 
     * @param id 扫码登录主键
     * @return 扫码登录
     */
    @Override
    public SysUserToken selectSysUserTokenById(String id)
    {
        return sysUserTokenMapper.selectSysUserTokenById(id);
    }

    /**
     * 查询扫码登录列表
     * 
     * @param sysUserToken 扫码登录
     * @return 扫码登录
     */
    @Override
    public List<SysUserToken> selectSysUserTokenList(SysUserToken sysUserToken)
    {
        return sysUserTokenMapper.selectSysUserTokenList(sysUserToken);
    }

    /**
     * 新增扫码登录
     * 
     * @param sysUserToken 扫码登录
     * @return 结果
     */
    @Override
    public int insertSysUserToken(SysUserToken sysUserToken)
    {
        sysUserToken.setCreateTime(DateUtils.getNowDate());
        return sysUserTokenMapper.insertSysUserToken(sysUserToken);
    }

    /**
     * 修改扫码登录
     * 
     * @param sysUserToken 扫码登录
     * @return 结果
     */
    @Override
    public int updateSysUserToken(SysUserToken sysUserToken)
    {
        return sysUserTokenMapper.updateSysUserToken(sysUserToken);
    }

    /**
     * 批量删除扫码登录
     * 
     * @param ids 需要删除的扫码登录主键
     * @return 结果
     */
    @Override
    public int deleteSysUserTokenByIds(String[] ids)
    {
        return sysUserTokenMapper.deleteSysUserTokenByIds(ids);
    }

    /**
     * 删除扫码登录信息
     * 
     * @param id 扫码登录主键
     * @return 结果
     */
    @Override
    public int deleteSysUserTokenById(String id)
    {
        return sysUserTokenMapper.deleteSysUserTokenById(id);
    }
}
