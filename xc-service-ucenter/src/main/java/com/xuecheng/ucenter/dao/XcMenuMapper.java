package com.xuecheng.ucenter.dao;

import com.xuecheng.framework.domain.ucenter.XcMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator.
 */
@Mapper
public interface XcMenuMapper {
    //根据用户id查询用户的权限
    @Select("SELECT * FROM `xc_menu` WHERE id IN (SELECT menu_id FROM `xc_permission` WHERE role_id IN (SELECT role_id FROM `xc_user_role` WHERE user_id = #{userid}))")
    public List<XcMenu> selectPermissionByUserId(String userid);
}
