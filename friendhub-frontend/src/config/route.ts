import Index from "../pages/Index.vue";
import Team from "../pages/Team.vue";
import User from "../pages/User.vue";
import Search from "../pages/Search.vue"
import UserEdit from "../pages/UserEdit.vue";
import SearchResult from "../pages/SearchResult.vue";
import UserLogin from "../pages/UserLogin.vue";
import TeamAdd from "../pages/TeamAdd.vue";
import TeamUpdate from "../pages/TeamUpdate.vue";
import UserTeamCreate from "../pages/UserTeamCreate.vue";
import UserTeamJoin from "../pages/UserTeamJoin.vue";

const routes = [
    { path: '/', title:'推荐用户', component: Index },
    { path: '/team', title:'探索队伍', component: Team },
    { path: '/team/add', title:'创建队伍', component: TeamAdd },
    { path: '/team/update', title:'修改队伍信息', component: TeamUpdate },
    { path: '/team/create', title:'创建的队伍', component: UserTeamCreate },
    { path: '/team/join', title:'加入的队伍', component: UserTeamJoin },
    { path: '/user', title:'我的信息', component: User },
    { path: '/user/edit', title:'修改信息', component: UserEdit },
    { path: '/user/list', title:'搜索到的用户', component: SearchResult },
    { path: '/search', title:'搜索用户', component: Search },
    { path: '/user/login', title:'登录', component: UserLogin },
]

export default routes;