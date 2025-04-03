<script>
import {computed, ref} from 'vue';
import {useStore} from 'vuex';
import Header from "../../components/Header.vue";
import Footer from "../../components/Footer.vue";
import {useRouter} from "vue-router";
import axios from "axios";
import Success from "../../components/Success.vue";

export default {
  name: "SelectPackage",
  components: {Footer, Header, Success},
  setup() {
    const router = useRouter();
    const dataUser = JSON.parse(sessionStorage.getItem("myData"));
    const showFail = ref(false)
    const store = useStore();

    const receivedData = computed(() => store.state.pack).value;
    let price = ref(receivedData.pricemonth);
    const descriptions = receivedData.description.split('. ');
    const changeprice = (x) => {
      if (x === 2) {
        price.value = receivedData.priceyear;

      } else {
        price.value = receivedData.pricemonth;
      }

    }
    const select = async () => {
      let recurring;
      if (price.value === receivedData.pricemonth) {
        recurring = 30;
      } else {
        if (price.value === receivedData.priceyear) {
          recurring = 365;
        }
      }
      if (receivedData.type === 1) {
        const params = {
          idAccount: dataUser.id,
        };
        const res = await axios.get("http://localhost:8080/package/check", {params})
        if (res.data !== 0) {
          showFail.value = true;
        }
        recurring = 14;
      }
      const pack = {
        name: receivedData.name,
        price: price.value,
        recurring: recurring,
        id: receivedData.id,

      }
      console.log(pack)
      store.commit('setPackSelected', pack);
      if (!showFail.value) {
        router.push("selectcard");
      }

    }
    const setUpdate = (x) => {
      showFail.value = x;
    }
    return {
      receivedData,
      price,
      changeprice,
      descriptions,
      select,
      showFail,
      setUpdate
    }
  }
}
</script>
<template>
  <div class="select-container">
    <Header/>
    <Success v-if="showFail" mes="Chỉ được đăng ký gói dùng thử 1 lần" url="" @change-component-event="setUpdate"/>
    <div class="info-page">
      <div class="left">
        <img class="image" src="https://www.vinasa.org.vn/Anhdaidien/Rabiloo_Logo.png" alt="">
      </div>
      <div class="right">
        <h2>{{ receivedData.name }}</h2>
        <div class="packs">
          <div class="pack" id="text" v-for="(item, index) in descriptions" :key="index">

            {{ item }}
          </div>
        </div>

        <div>
          <h2 v-if="receivedData.type===2||receivedData.type===3">Chọn gói bạn muốn đăng ký:</h2>
          <form @submit.prevent="select">
            <label for="option1" v-if="receivedData.type===2||receivedData.type===3">
              <input type="radio" id="option1" name="option" @change="changeprice(1)"> Gói tháng
            </label><br>
            <label for="option2" v-if="receivedData.type===2||receivedData.type===3">
              <input type="radio" id="option2" name="option" @change="changeprice(2)"> Gói năm
            </label><br>
            <p>Giá: {{ price }}$</p>
            <br>
            <input type="submit" class="button">
          </form>
        </div>
      </div>


    </div>
    <Footer/>

  </div>

</template>


<style scoped>
.select-container {
  width: 100vw;
  height: 100vh;
  background-color: azure;
  display: flex;
  flex-direction: column;

}

.select-container Header {
  flex: 10;
  display: flex;
}

.left {
  display: flex;
  height: 100%;
  width: 50%;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.right {
  display: flex;
  width: 50%;
  flex-direction: column;
  justify-content: center;
  padding-left: 100px;
  margin-right: 150px;
  margin-top: 150px;
  margin-bottom: 150px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);
  border: 1px solid gray;
  background-color: #daf1f8;
  border-radius: 10px;

}

.select-container Footer {
  flex: 10;
  display: flex;
}

.info-page {
  display: flex;
  flex: 80;
  flex-direction: row;

}

.pack {
  display: flex;
  width: 150px;
  height: 200px;
  margin-left: 20px;
  margin-right: 20px;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);
  border: 1px solid gray;

}

.packs {
  display: flex;
  flex-direction: row;
}

.button {
  background-color: #007bff;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.3s;
}

.button:hover {
  color: black;
}

</style>