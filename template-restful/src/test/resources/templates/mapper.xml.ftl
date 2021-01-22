<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">
    <sql id="allFieldMap">
    <#list table.fields as field>
      <#if field_has_next>
        ${field.name} as ${field.propertyName},
      <#else>
        ${field.name} as ${field.propertyName}
      </#if>
    </#list>
    </sql>

    <select id="getById" parameterType="java.lang.Long" resultType="${package.Entity}.${entity}">
        SELECT <include refid="allFieldMap"/>
        FROM ${table.name}
        WHERE id = #\{id}
    </select>

    <insert id="insert" parameterType="${package.Entity}.${entity}">
        insert into ${table.name}
        (
      <#list table.fields as field>
          <#if !field.keyFlag>
            ${field.name}<#if field_has_next>,</#if>
          </#if>
      </#list>
        )
        values
        (
      <#list table.fields as field>
          <#if !field.keyFlag>
            #\{${field.propertyName}}<#if field_has_next>,</#if>
          </#if>
      </#list>
        )
        <selectKey resultType="long" keyProperty="id">
            select LASt_INSERT_ID() AS id
        </selectKey>
    </insert>

    <update id="updateById" parameterType="${package.Entity}.${entity}">
        update
            ${table.name}
        set
    <#list table.fields as field>
        <#if field.name != 'id'>
          <if test="${field.propertyName} != null">
            ${field.name} = #\{${field.propertyName}}<#if field_has_next>,</#if>
          </if>
        </#if>
    </#list>
        where id = #\{id}
    </update>

</mapper>
