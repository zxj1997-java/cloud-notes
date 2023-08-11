package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysUserToken;

/**
 * 扫码登录Service接口
 * 
 * @author ruoyi
 * @date 2023-08-11
 */
public interface ISysUserTokenService 
{
    /**
     * 查询扫码登录
     * 
     * @param id 扫码登录主键
     * @return 扫码登录
     */
    public SysUserToken selectSysUserTokenById(String id);

    /**
     * 查询扫码登录列表
     * 
     * @param sysUserToken 扫码登录
     * @return 扫码登录集合
     */
    public List<SysUserToken> selectSysUserTokenList(SysUserToken sysUserToken);

    /**
     * 新增扫码登录
     * 
     * @param sysUserToken 扫码登录
     * @return 结果
     */
    public int insertSysUserToken(SysUserToken sysUserToken);

    /**
     * 修改扫码登录
     * 
     * @param sysUserToken 扫码登录
     * @return 结果
     */
    public int updateSysUserToken(SysUserToken sysUserToken);

    /**
     * 批量删除扫码登录
     * 
     * @param ids 需要删除的扫码登录主键集合
     * @return 结果
     */
    public int deleteSysUserTokenByIds(String[] ids);

    /**
     * 删除扫码登录信息
     * 
     * @param id 扫码登录主键
     * @return 结果
     */
    public int deleteSysUserTokenById(String id);
}
