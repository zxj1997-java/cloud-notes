package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysUserToken;
import org.apache.ibatis.annotations.Mapper;

/**
 * 扫码登录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-11
 */
@Mapper
public interface SysUserTokenMapper 
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
     * 删除扫码登录
     * 
     * @param id 扫码登录主键
     * @return 结果
     */
    public int deleteSysUserTokenById(String id);

    /**
     * 批量删除扫码登录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysUserTokenByIds(String[] ids);
}
