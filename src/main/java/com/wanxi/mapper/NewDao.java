package com.wanxi.mapper;


import com.wanxi.entity.NewModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewDao extends BaseDao<NewModel> {
     int addText(NewModel newModel);

    List<NewModel> findNewId(NewModel newModel);
}
