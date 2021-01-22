package com.kingwarluo.template.base.mybatis.dao;

/**
 * 基础dao方法
 *
 * @author longquan.huang
 * @date 2019-11-25 17:39
 * @since jdk 1.8
**/
public interface IBaseDao<E> {

    /***
     * 插入记录
     * @param bean
     * @return
     */
    Object insert(E bean);

    /***
     * 通过id获取记录
     * @param id
     * @return
     */
    E getById(Long id);

    /***
     * 通过id更新记录
     * @param bean
     * @return
     */
    int updateById(E bean);
}