import {createRouter, createWebHistory} from 'vue-router';
import HomePage from '../pages/User/HomePage.vue'
import Login from "../pages/User/Login.vue";
import Payment from "../pages/User/payment.vue";
import SelectPage from "../pages/User/SelectPackage.vue"
import Invoices from "../pages/User/Invoices.vue"
import SelectCard from "../pages/User/SelectCard.vue";
import HomeAdmin from "../pages/admin/HomeAdmin.vue"
import ShowUser from "../components/ShowUser.vue"
import Packages from "../pages/User/Packages.vue";
const routes= [
    {
        path: "/homepage",
        component: HomePage,
    },
    {
        path: "/",
        component: HomePage,
    },
    {
        path: "/login",
        component: Login,
    },
    {
        path:"/payment",
        component:Payment,
    },
    {
        path:"/selectpage",
        component:SelectPage,
    },
    {
        path:"/invoices/:id",
        name:"invoices",
        component:Invoices,
    },
    {
        path:"/selectcard",
        component:SelectCard,
    },
    {
        path:"/homeadmin",
        component:HomeAdmin,
    },
    {
        path:"/showuser",
        component:ShowUser,
    },
    {
        path:"/packages",
        component:Packages,
    },



];

const router = createRouter({
    history: createWebHistory(),
    routes

});

export default router;
