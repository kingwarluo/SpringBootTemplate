/*
* Copyright (C), 2021-2025, KingWarLuo
*/
package ${package.Entity};

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @since ${date}
*/
@Data
public class ${entity} implements Serializable {

  private static final long serialVersionUID = 1L;
  <#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>
    <#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
    </#if>
    private ${field.propertyType} ${field.propertyName};

  </#list>

}
