<script lang="ts">
import {ref, onMounted, computed} from 'vue';
import Header from "../../components/Header.vue";
import Footer from "../../components/Footer.vue";
import CardPayment from "../../components/CardPayment.vue"
import axios from "axios";
import store from "../../store";
import {useRouter} from "vue-router";
import Success from "../../components/Success.vue";

export default {
  name: "SelectCard",
  components: {Footer, Header, CardPayment, Success},
  setup() {
    const router = useRouter();
    let dataUser = {
      id:"",
      name:"",
      gmail:""
    };
    const storedData = sessionStorage.getItem("myData");
    if (storedData !== null) {
       dataUser = JSON.parse(storedData);

    } else {

    }
    const dataPayment = ref([]);
    const receivedData = computed(() => store.state.packselected).value;
    const cardSelected = ref(null);
    const descriptions = ref<string[]>([]);
    const showSuccess = ref(false)
    onMounted(async () => {
      const params = {
        id: dataUser.id,
      };
      const res = await axios.get("http://localhost:8080/api/getpaymentmethod", {params})
      dataPayment.value = res.data
      descriptions.value.push("Tên gói: " + receivedData.name);
      descriptions.value.push("Giá: " + receivedData.price);
      if (receivedData.recurring == 30) {
        descriptions.value.push("Chu kỳ: Tháng");
      } else descriptions.value.push("Chu kỳ: Năm");
      descriptions.value.push("Hệ thống sẽ tự động gia hạn gói khi hết chu kỳ.Quý khách hủy gói vẫn sẽ được sử dụng cho đến hết chu kỳ đã đăng ký.")

    })


    const selectCard = (idPayment:any) => {
      cardSelected.value = idPayment;
      console.log("dã chọn: ", cardSelected.value)
    }
    const payment = () => {
      const payload = ref({
        token: cardSelected.value,
        idCus: dataUser.id,
        idPack: receivedData.id,
        amount: receivedData.price,
        nameCus: dataUser.name,
        gmail: dataUser.gmail,
        recurring: receivedData.recurring,
        firstTime: 0
      })
      const callApi = async () => {
         await axios.post("http://localhost:8080/api/createpaymentmethod", payload.value);
        showSuccess.value = true
      }
      callApi();
    }
    const usenewcard = () => {
      router.push("/payment")
    }
    return {
      dataPayment,
      receivedData,
      selectCard,
      descriptions,
      payment,
      usenewcard,
      showSuccess
    }

  }

};
</script>
<template>
  <div class="payment-container ">
    <Header/>
    <Success v-if="showSuccess" url="packages" mes="Chúc mừng! Bạn đã đăng ký thành công."/>
    <div class="select-card-page">
      <div class="select">
        <h4>Transaction information</h4>
        <div class="info">
          <p id="text" v-for="(item, index) in descriptions" :key="index">{{ item }} </p>

        </div>
        <h4>Recent card you used</h4>
        <div class="cards">
          <div class="used-card" v-for="({flast, idPayment, type}, index) in dataPayment" :key="index">
            <CardPayment class="cardpayment" :cardNumber=flast :type=type :id=idPayment
                         @click="selectCard(idPayment)"/>
          </div>
        </div>
        <button class="button" @click="payment">Payment</button>
        <p>Or</p>
        <h4 @click="usenewcard" class="gotopayment">Add a new payment method</h4>

      </div>

    </div>
    <Footer/>
  </div>
</template>

<style scoped>
.payment-container {
  width: 100vw;
  height: 100vh;
  background-color: azure;
  display: flex;
  flex-direction: column;

}

.login-container Header {
  flex: 10;
  display: flex;
}

.login-container Footer {
  flex: 10;
  display: flex;
}

.select-card-page {
  display: flex;
  flex: 80;
  flex-direction: column;
  justify-content: center;
  align-items: center;

}

.select {
  height: 80%;
  width: 50%;
  display: flex;
  background-color: #daf1f8;
  border: black 2px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);
  border-radius: 3px;
  border: 1px solid gray;
  flex-direction: column;
  align-items: center;

}

.button {
  height: 30px;
  width: 90px;
  background-color: #004cb8;
  color: azure;
  margin-top: 10px;
  cursor: pointer;

}

.button:hover {
  color: black;
}

.info {
  display: flex;
  height: 20%;
  width: 90%;
  border: 1px solid gray;
  border-radius: 3px;
  padding: 10px;
}

.cards {
  display: flex;
  height: 40%;
  width: 90%;
  border: 1px solid gray;
  border-radius: 3px;
  justify-content: center;
  padding-top: 20px;

}

.cardpayment {
  margin: 10px 10px 20px;
  cursor: pointer;
  transition: background-color 0.3s ease;

}

.info {
  display: flex;
  flex-direction: column;
}

.info p {
  padding: 0;
  margin: 0;
}

.gotopayment {
  cursor: pointer;

}

</style>