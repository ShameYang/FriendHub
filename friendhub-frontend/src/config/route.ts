import Index from "../pages/Index.vue";
import Team from "../pages/Team.vue";
import User from "../pages/User.vue";
import Search from "../pages/Search.vue"
import UserEdit from "../pages/UserEdit.vue";

const routes = [
    { path: '/', component: Index },
    { path: '/team', component: Team },
    { path: '/user', component: User },
    { path: '/user/edit', component: UserEdit },
    { path: '/search', component: Search },
]

export default routes;