package com.shameyang.friendhub.service;

import com.shameyang.friendhub.model.domain.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shameyang.friendhub.model.domain.User;
import com.shameyang.friendhub.model.dto.TeamQuery;
import com.shameyang.friendhub.model.vo.TeamUserVO;

import java.util.List;

/**
* @author shameyang
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2024-05-28 14:17:55
*/
public interface TeamService extends IService<Team> {

    /**
     * 创建队伍
     * @param team 队伍
     * @param loginUser 当前用户
     * @return teamId
     */
    long addTeam(Team team, User loginUser);

    /**
     * 搜索队伍
     *
     * @param teamQuery 用户查询封装类
     * @param loginUser 当前用户
     * @return TeamUserVO
     */
    List<TeamUserVO> listTeams(TeamQuery teamQuery, User loginUser);
}