/*
* Copyright (C), 2021-2025, KingWarLuo
*/
package ${package.Mapper};

import ${package.Entity}.${entity};
import org.apache.ibatis.annotations.Mapper;
import com.kingwarluo.template.base.mybatis.dao.IBaseDao;

/**
 * ${table.comment!} Dao 接口
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper
public interface ${table.mapperName} extends IBaseDao<${entity}>{

}
