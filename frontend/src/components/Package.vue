<template>
  <div class="container-user">
    <div class="list-button">

      <button @click="Update" class="button">Xem danh sách gói</button>
      <button @click="Statistical" class="button"> Thống kê</button>

    </div>
    <div class="info">
      <Statistical v-if="isStatistical"/>
      <ListPackage v-if="isUpdate"/>
    </div>

  </div>
</template>

<script>
import Statistical from "./StatisticalPackage.vue";
import ListPackage from "./ListPackage.vue"
import {ref} from "vue";

export default {
  name: "Package",
  components: {Statistical, ListPackage},
  setup() {
    let isStatistical = ref(false);
    let isUpdate = ref(true);
    const Statistical = () => {
      isStatistical.value = true;
      isUpdate.value = false;
    }
    const Update = () => {
      isStatistical.value = false;
      isUpdate.value = true;

    }

    return {
      isStatistical, isUpdate, Statistical, Update
    }
  }
}
</script>

<style scoped>
.container-user {
  display: flex;
  background-color: #40c4c4;
  width: 100%;
  height: 100%;
  flex-direction: row;
}

.list-button {
  display: flex;
  height: 100%;
  width: 10%;
  flex-direction: column;
  align-items: end;
  padding-right: 2px;

}

.info {
  display: flex;
  height: 100%;
  width: 90%;
  background-color: azure;
}

.button {
  height: 30px;
  width: 140px;
  background-color: #004cb8;
  color: azure;
  margin-top: 20px;
}

.button:hover {
  color: black;
}
</style>