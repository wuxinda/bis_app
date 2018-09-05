package com.bluemobi.base.mapper;

import org.apache.poi.ss.formula.functions.T;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * tk.mybatis通用mapper
 * @author wuxinda
 *
 */
@SuppressWarnings("hiding")
public interface BaseMapper<T> extends Mapper<T>,InsertListMapper<T>,BaseSelectMapper<T>{

}
