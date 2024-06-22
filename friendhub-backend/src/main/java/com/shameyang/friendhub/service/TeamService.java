package com.shameyang.friendhub.service;

import com.shameyang.friendhub.model.domain.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shameyang.friendhub.model.domain.User;
import com.shameyang.friendhub.model.dto.TeamQuery;
import com.shameyang.friendhub.model.request.TeamDeleteRequest;
import com.shameyang.friendhub.model.request.TeamJoinRequest;
import com.shameyang.friendhub.model.request.TeamQuitRequest;
import com.shameyang.friendhub.model.request.TeamUpdateRequest;
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
     * @param isNotAdmin 是否为管理员
     * @return TeamUserVO
     */
    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isNotAdmin);

    /**
     * 更新队伍
     * @param teamUpdateRequest 更新请求体
     * @return boolean
     */
    boolean updateTeam(TeamUpdateRequest teamUpdateRequest, User loginUser);

    /**
     * 加入队伍
     * @param teamJoinRequest 加入请求体
     * @param loginUser 当前用户
     * @return boolean
     */
    boolean joinTeam(TeamJoinRequest teamJoinRequest, User loginUser);

    /**
     * 退出队伍
     * @param teamQuitRequest 退出请求体
     * @return boolean
     */
    boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser);

    /**
     * 解散队伍
     * @param teamDeleteRequest 解散请求体
     * @param loginUser 当前用户
     * @return boolean
     */
    boolean deleteTeam(TeamDeleteRequest teamDeleteRequest, User loginUser);

    /**
     * 获得当前队伍人数
     * @param teamId 队伍id
     * @return 队伍人数
     */
    int countTeamUserByTeamId(long teamId);
}
