<script >
import {computed, onMounted, ref} from 'vue';
import {loadStripe} from '@stripe/stripe-js';
import Header from "../../components/Header.vue";
import Footer from "../../components/Footer.vue";
import CardPayment from "../../components/CardPayment.vue"
import axios from "axios";
import store from "../../store";
import ErrorNotification from "../../components/ErrorNotification.vue";
import Success from "../../components/Success.vue";

export default {
  name: "payment",
  components: {ErrorNotification, Footer, Header, CardPayment, Success},
  setup: function () {
    let data = "";
    const storedData = sessionStorage.getItem("myData");

    if (storedData !== null) {
       data = JSON.parse(storedData);
    } else {
    }
    const stripe = ref(null);
    const cardElement = ref(null);
    const card = ref(null);
    const receivedData = computed(() => store.state.packselected).value;
    const dataPayment = ref([]);
    const showError = ref(false)
    const key = import.meta.env.VITE_SOME_KEY;
    const showSuccess = ref(false)
    let errortype = ref()

    onMounted(async () => {
      // Khởi tạo Stripe với Publishable key
      try {
        const stripePromise = loadStripe(key);

        stripe.value = await stripePromise;

        // Khởi tạo Elements
        const elements = stripe.value.elements();

        // Tạo và đính kèm phần tử input card
        card.value = elements.create('card', {
          style: {
            base: {
              fontWeight: '500',
              fontFamily: 'Roboto, Open Sans, Segoe UI, sans-serif',
              fontSize: '16px',
              fontSmoothing: 'antialiased',
              display: 'flex',
              flexDirection: 'column',
              ':-webkit-autofill': {},
              '::placeholder': {},
            },
            invalid: {},
          },
        });
        card.value.mount(cardElement.value);

      } catch (error) {
        console.error('Lỗi khởi tạo Stripe:', error);


      }
    });

    function extractMessage(s) {
      // Tìm vị trí của dấu chấm phẩy đầu tiên trong chuỗi
      const semicolonIndex = s.indexOf(';');

      // Nếu không tìm thấy dấu chấm phẩy, trả về chuỗi ban đầu
      if (semicolonIndex === -1) {
        return s;
      }

      // Trích xuất chuỗi từ đầu đến dấu chấm phẩy đầu tiên
      return s.substring(0, semicolonIndex);
    }

    const createToken = async () => {
      if (!stripe.value) {
        console.error('Stripe chưa được khởi tạo.');
        return;
      }
      let tokencheck = ref(null)
      try {
        const token = await stripe.value.createPaymentMethod({
          type: 'card',
          card: card.value,
          billing_details: {
            name: 'TestCus',
          },
        });
        console.log(token)
        tokencheck.value = token.paymentMethod.id
        const payload = ref({
          token: token.paymentMethod.id,
          idCus: data.id,
          idPack: receivedData.id,
          amount: receivedData.price,
          nameCus: data.name,
          gmail: data.gmail,
          recurring: receivedData.recurring,
          firstTime: 1

        })

        const callApiCreatePaymentMethod = async () => {
          try {
            const response = await axios.post("http://localhost:8080/api/createpaymentmethod", payload.value);
            showSuccess.value = true
          } catch (error) {
            errortype.value = extractMessage(error.response.data)
            showError.value = true
            console.log(error.response.data)

          }


        }
         callApiCreatePaymentMethod();

      } catch (error) {
        console.error('Lỗi tạo token:', error);
        if (tokencheck.value === null) {
          errortype.value = "Thông tin thẻ không chính xác, vui lòng kiểm tra lại!"

        } else {
          errortype.value = "Lỗi hệ thống. Vui lòng thử lại"

        }
        showError.value = true
        console.log(errortype.value)

      }
    };

    return {
      createToken,
      cardElement,
      dataPayment,
      errortype,
      showError,
      showSuccess

    };
  },
};
</script>
<template>
  <div class="payment-container ">
    <Header/>
    <Success v-if="showSuccess" url="packages" mes="Chúc mừng! Bạn đã đăng ký thành công."/>
    <div class="payment-page">

      <div class="select">
        <h2 class="title">Payment with new card</h2>
        <div class="card">
          <div class="cardform" ref="cardElement"></div>
        </div>
        <div class="notification"><p v-if="showError">{{ errortype }}</p></div>

        <button class="button" @click="createToken">Payment</button>

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

h2 {
  color: #efefef;
}

.login-container Footer {
  flex: 10;
  display: flex;
}

.payment-page {
  display: flex;
  flex: 80;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.button {
  height: 30px;
  width: 150px;
  background-color: #004cb8;
  color: azure;
  margin-top: 10px;
}

.button:hover {
  color: black;
}

.select {
  height: 80%;
  width: 50%;
  display: flex;
  background-color: #daf1f8;
  border: black 2px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);
  border-radius: 3px;
  flex-direction: column;
  align-items: center;
  background-image: url("https://luat24h.net/wp-content/uploads/2022/05/Hinh-thuc-thanh-toan-bang-tien-mat.jpg");
  background-repeat: no-repeat;
  background-size: cover;


}

.card {
  margin-top: 40px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 50px;
  width: 70%;
  border: 1px solid gray;
  border-radius: 5px;
  margin-bottom: 10px;
  background-color: #cddee3;
}

.notification {
  display: flex;
  width: 70%;
  height: 30px;
  justify-content: center;
  align-items: center;
  color: darkred;
}
</style>