package com.wanxi.service;


import com.wanxi.entity.NewModel;
import com.wanxi.commonresult.CommonResult;

public interface NewService extends BaseService<NewModel>{


    CommonResult addText(NewModel newModel);

    CommonResult findNewId(NewModel newModel);
}
