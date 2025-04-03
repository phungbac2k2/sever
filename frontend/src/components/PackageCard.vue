<template>
  <div class="container">
    <h3>{{ package.name }}</h3>
    <div class="descr">
      <p id="text" v-for="(item, index) in descriptions" :key="index">{{ item }}</p>
    </div>
    <button v-if="!isAdmin" class="button" @click="select">Đăng ký</button>
    <button v-if="isAdmin" class="button" @click="update">Chỉnh sửa</button>

  </div>
</template>

<script>
import {useRouter} from "vue-router";
import {useStore} from 'vuex';
import {ref} from "vue";

export default {
  name: "PackageCard",

  props: ['package'],

  setup(props, {emit}) {
    const isAdmin = ref(false);
    const dataUser = JSON.parse(sessionStorage.getItem("myData"));

    if (dataUser != null) {
      isAdmin.value = dataUser.role === "ROLE_ADMIN";
    }
    const store = useStore();
    const router = useRouter();
    console.log(props.package)
    const descriptions = props.package.description.split('. ');
    const select = () => {
      if (dataUser != null) {
        store.commit('setData', props.package);
        router.push("/selectpage")
      }
    }
    const update = () => {
      emit('change-component-event', props.package);
    }
    return {
      descriptions,
      router,
      select,
      isAdmin,
      update


    }
  }


}
</script>

<style scoped>
.container {

  display: flex;
  background-color: #c6ecf8;
  margin: 10px;
  flex-direction: column;
  border-radius: 5px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);
  align-items: center;

}

.descr {
  flex: 7;
}

.container h3 {
  display: flex;
  flex: 1.5;


}

.button {
  margin-bottom: 10px;
  flex: 1;
  background-color: #317eec;
  color: azure;
  width: 90px;
  height: 20px;
  border: 1px solid black;
}

.button:hover {
  color: black;
}

</style>