<template>
  <div class="container-list-package">
    <h2>Danh sách gói</h2>
    <div class="package">
      <PackageCard class="info-package" v-for="item in dataPackages" v-bind:key="item"
                   v-bind:package="item" @change-component-event="setUpdate"/>
    </div>
    <div class="update">
      <button v-if="showUpdate" @click="closeUpdate" class="button">Hủy bỏ</button>
      <div class="card-update">
        <UpdatePackCard v-if="showUpdate" :info="infoPack"/>
      </div>
    </div>

  </div>
</template>

<script>
import PackageCard from "../components/PackageCard.vue";
import {ref} from "vue";
import axios from "axios";
import UpdatePackCard from "./UpdatePackCard.vue";

export default {
  name: "ListPackage",
  components: {PackageCard, UpdatePackCard},
  setup() {
    const selectPack = ref(5);
    const dataPackages = ref([]);
    const infoPack = ref()
    const showUpdate = ref(false)
    const fetchDataPackages = async () => {
      try {
        const response = await axios.get('http://localhost:8080/package/getall');
        dataPackages.value = response.data;
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    fetchDataPackages();
    const setUpdate = (x) => {
      selectPack.value = x.type;
      infoPack.value = x;
      console.log(infoPack.value)
      showUpdate.value = true;


    }
    const closeUpdate = () => {
      showUpdate.value = false
    }
    return {
      dataPackages,
      selectPack,
      setUpdate,
      infoPack,
      showUpdate,
      closeUpdate
    }
  }
}
</script>

<style scoped>
.container-list-package {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.package {
  margin-top: 20px;
  width: 100%;
  display: flex;
  justify-content: center;
  padding: 0 25px 0 25px;
  height: 30%;
}

.info-package {
  display: flex;
  width: 23%;
  padding: 0 10px 0 10px;

}

.update {
  margin-top: 20px;
  width: 100%;
  display: flex;
  padding: 0 25px 0 25px;
  height: 50%;
  flex-direction: column;
  align-items: end;

}

.button {
  height: 30px;
  width: 90px;
  background-color: #004cb8;
  color: azure;
  margin-top: 10px;

}

.button:hover {
  color: black;
}

.card-update {
  display: flex;
  width: 100%;
  height: 100%;
}
</style>