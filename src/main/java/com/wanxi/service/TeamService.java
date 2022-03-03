package com.wanxi.service;


import com.wanxi.entity.TeamModel;
import com.wanxi.commonresult.CommonResult;

public interface TeamService extends BaseService<TeamModel>{


    CommonResult addText(TeamModel teamModel);

    CommonResult findTeamId(TeamModel team);
}
