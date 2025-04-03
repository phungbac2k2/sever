<template>
  <div class="container-update">
    <Success v-if="showSuccess" mes="Bạn đã chỉnh sửa gói thành công!" url="homeadmin"/>
    <h3>Chỉnh sử gói {{ info.name }}</h3>
    <form @submit.prevent="save">
      <label for="name">Tên gói:</label>
      <input type="text" id="name" v-model="namePack" required>
      <div class="bodyform">
        <div class="info-pack23" v-if="info.type===2||info.type===3">
          <div class="month">
            <label>Gói tháng</label>
            <label for="price-month">Price:</label>
            <input type="number" id="price-month" v-model="priceMonth" required>
          </div>

          <div class="year">
            <label>Gói năm</label>
            <label for="price-year">Price:</label>
            <input type="number" id="price-year" v-model="priceYear" required>
          </div>
        </div>
        <div class="info-pack14" v-if="info.type===4">
          <label for="price-month4">Price:</label>
          <input type="number" id="price-month4" v-model="priceMonth" required>
        </div>
        <label for="description">Mô tả:</label>
        <textarea id="description" v-model="description" required></textarea>
      </div>
      <button type="submit" class="button">Lưu lại</button>
    </form>
  </div>
</template>

<script>
import {ref, watch} from "vue";
import axios from "axios";
import Success from "./Success.vue";


export default {
  name: "UpdatePackCard",
  props: ['info'],
  components: {Success},

  setup(props) {
    const info = ref(props.info)
    const namePack = ref()
    const priceMonth = ref()
    const priceYear = ref()
    const description = ref()
    const showSuccess = ref(false)

    const setval = () => {
      namePack.value = info.value.name
      priceMonth.value = info.value.pricemonth
      priceYear.value = info.value.priceyear
      description.value = info.value.description
    }
    setval();

    watch(() => props.info, (newData) => {
      info.value = newData
      setval()
    })
    console.log(info)
    const save = async () => {
      let payload = ref(info.value)
      payload.value.description = description.value
      payload.value.name = namePack.value
      payload.value.priceyear = priceYear.value
      payload.value.pricemonth = priceMonth.value

      console.log("data da chinh sua: ", payload.value)
      const res = await axios.post("http://localhost:8080/package/update", payload.value)
      if (res.status === 200) {
        showSuccess.value = true
      }
    }

    return {
      info,
      save,
      namePack,
      priceMonth,
      priceYear,
      description,
      showSuccess,

    }
  }
}
</script>

<style scoped>
.container-update {
  display: flex;
  width: 100%;
  border: 1px solid gray;
  border-radius: 5px;
  height: 100%;
  flex-direction: column;
  align-items: center;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);

}

.info-pack23 {
  display: flex;
  flex-direction: row;
  height: 55%;
}

.info-pack14 {
  display: flex;
  flex-direction: column;
  height: 55%;
  width: 100%;

}

form {
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 50%

}

.bodyform {
  display: flex;
  width: 100%;
  height: 60%;
  flex-direction: column;
}

.year, .month {
  width: 100%;
  display: flex;
  flex-direction: column;
  margin-right: 70px;

  padding: 10px;
  border-left: 1px solid gray;

}

input {
  width: 100%;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 5px;
  font-size: 14px;
}

.button {
  height: 30px;
  width: 90px;
  background-color: #004cb8;
  color: azure;
  margin-top: 20px;
}

.button:hover {
  color: black;
}

label {
  display: block;
  margin-bottom: 5px;
  margin-top: 10px;
  font-weight: bold;
}

textarea {
  height: 100%;
}
</style>