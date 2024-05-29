package com.shameyang.friendhub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shameyang.friendhub.model.domain.UserTeam;
import com.shameyang.friendhub.service.UserTeamService;
import com.shameyang.friendhub.mapper.UserTeamMapper;
import org.springframework.stereotype.Service;

/**
* @author shameyang
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2024-05-28 14:20:41
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService{

}




