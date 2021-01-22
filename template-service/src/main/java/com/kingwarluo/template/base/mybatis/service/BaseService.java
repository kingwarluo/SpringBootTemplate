package com.kingwarluo.template.base.mybatis.service;

/**
 * 服务接口基类
 *
 * @author jianhua.luo
 * @date 2021/1/22
 */
public interface BaseService<B> {

    /***
     * 插入
     * @param bean 实体类
     * @return 插入成功后的主键id
     */
    Object insert(B bean);

    /***
     * 通过id获取
     * @param id 主键id
     * @return 返回对应数据
     */
    B getById(Long id);

    /***
     * 通过id更新
     * @param bean 实体类
     * @return 返回更新成功数
     */
    int updateById(B bean);
}
