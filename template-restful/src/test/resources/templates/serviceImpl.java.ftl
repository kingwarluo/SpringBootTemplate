/*
* Copyright (C), 2021-2025, KingWarLuo
*/
package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${table.serviceImplName} implements ${table.serviceName} {

    @Autowired
    private ${table.mapperName} ${table.mapperName?uncap_first};

    /***
     * 插入记录
     * @param ${entity?uncap_first}  数据实体类
     * @return 返回插入成功后的主键id
     */
    @Override
    public Object insert(${entity} ${entity?uncap_first}) {
        return ${table.mapperName?uncap_first}.insert(${entity?uncap_first});
    }
    /***
     * 通过id获取
     * @param id 主键id
     * @return 返回实体类
     */
    @Override
    public ${entity} getById(Long id) {
        return ${table.mapperName?uncap_first}.getById(id);
    }

    /***
     * 通过id更新
     * @param ${entity?uncap_first} 实体类中必须有id
     * @return 更新成功的条数
     */
    @Override
    public int updateById(${entity} ${entity?uncap_first}) {
        return ${table.mapperName?uncap_first}.updateById(${entity?uncap_first});
    }

}

