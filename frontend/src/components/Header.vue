<script>
import {useRouter} from "vue-router";
import {ref} from "vue";

export default {
  name: "header",

  setup: function (props, {emit}) {
    const router = useRouter();
    const isAdmin = ref(false);
    const dataUser = JSON.parse(sessionStorage.getItem("myData"));

    if (dataUser != null) {
      isAdmin.value = dataUser.role === "ROLE_ADMIN";
    }

    const logout = () => {
      sessionStorage.removeItem("myData");
      router.replace("/login");
    }
    const login = () => {
      sessionStorage.removeItem("myData");
      router.replace("/login");
    }
    const goToInvoices = () => {
      console.log(dataUser.id)
      router.push({name: 'invoices', params: {id: dataUser.id}});
    }
    const gotohome = () => {
      if (!isAdmin.value) {
        router.push("/");
      }

    }
    const ChosePackage = () => {

      if (isAdmin.value) {
        emit('change-component-event', 'Pack');
      } else {
        router.push("/packages")
      }
    }
    const ChoseUser = () => {
      emit('change-component-event', 'User');
    }
    return {
      dataUser,
      router,
      login,
      logout,
      goToInvoices,
      gotohome,
      isAdmin,
      ChosePackage,
      ChoseUser
    }
  }
}
</script>
<template>
  <div class="header">
    <p @click="gotohome" class="logo">RABILOO SERVER</p>
    <button v-if="!isAdmin&&dataUser!=null" class="button" @click="goToInvoices">Hóa đơn</button>
    <button v-if="dataUser!=null" class="button" @click="ChosePackage">Quản lý gói</button>
    <button v-if="isAdmin&&dataUser!=null" class="button" @click="ChoseUser">Quản lý user</button>
    <button v-if="dataUser!=null" class="button" @click="logout">Đăng xuất</button>
    <p v-if="dataUser!=null">Xin chào {{ dataUser.name }} </p>
    <button v-if="!(dataUser!=null)" class="button" @click="login">Đăng nhập</button>


  </div>
</template>
<style scoped>
.header {
  display: flex;
  background-color: #004cb8;
  width: 100%;
}

.header p {
  height: 100%;
  margin-left: 10px;
  color: azure;
  font-weight: bold;
}

.logo {
  margin-right: 55%;
}

.header button {
  margin-right: 20px;
  margin-top: 10px;
  height: 30px;
  width: 100px;
  background-color: #40c4c4;
  color: azure;
  border: none;
}

.button:hover {
  color: black;
}
</style>
