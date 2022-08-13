import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        component: () => import('../views/Manager.vue'),
        redirect: '/home',
        children: [
            {path: 'user', name: '用户管理', component: () => import('../views/User.vue')},
            {path: 'home', name: '首页', component: () => import('../views/Home.vue')},
            {path: 'person',name: '个人信息',component : () => import('../views/person.vue')}
        ]


    },
    {
        path: '/about',
        name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component:() => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
},
    {
        path: '/login',
        name: 'Login',
        component : () => import('../views/login.vue')
    },{
        path: '/register',
        name: 'Register',
        component : () => import('../views/register.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to,from,next)=>{
    localStorage.setItem("currentPathName",to.name)
    store.commit("setPath")
    next()
})
export default router
