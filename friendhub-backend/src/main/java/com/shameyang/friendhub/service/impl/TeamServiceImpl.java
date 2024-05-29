package com.shameyang.friendhub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shameyang.friendhub.model.domain.Team;
import com.shameyang.friendhub.service.TeamService;
import com.shameyang.friendhub.mapper.TeamMapper;
import org.springframework.stereotype.Service;

/**
* @author shameyang
* @description 针对表【team(队伍)】的数据库操作Service实现
* @createDate 2024-05-28 14:17:55
*/
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
    implements TeamService{

}




