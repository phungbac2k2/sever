<template>
  <div class="container-user">
    <div class="list-button">
      <button @click="Search" class="button">Search</button>
      <button @click="Statistical" class="button"> Thống kê</button>

    </div>
    <div class="info">
      <Statistical v-if="isStatistical"/>
      <Search v-if="isSearch"/>
    </div>

  </div>
</template>

<script>
import Search from "./Search.vue";
import Statistical from "./StatisticalUser.vue";
import {ref} from "vue";

export default {
  name: "ShowUser",
  components: {Search, Statistical},
  setup() {
    let isStatistical = ref(false);
    let isSearch = ref(true);
    const Statistical = () => {
      isStatistical.value = true;
      isSearch.value = false;
    }
    const Search = () => {
      isStatistical.value = false;
      isSearch.value = true;

    }

    return {
      isStatistical, isSearch, Statistical, Search
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