<script>
import {ref} from "vue";
export default {
  name: "CardPayment",
  props: {
    cardNumber: String, // Số thẻ Visa đầy đủ
    type: String,
    id: String
  },
  setup(props) {
    // Lấy 4 số cuối của số thẻ Visa
    const lastFourDigits = ref(props.cardNumber.slice(-4));

    return {
      lastFourDigits
    };
  },
}
</script>


<template>
  <div class="card" @click="emitEvent">
    <div class="card-number">**** **** **** {{ lastFourDigits }}</div>
    <div class="type-card">{{ type }}</div>
  </div>
</template>

<style scoped>
.card {
  background-color: #4683b4;
  color: white;
  padding: 10px;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  border-color: darkgray;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);

}

.card-number {
  font-size: 18px;
  font-weight: bold;
  margin-left: 5px;
}

.type-card {
  margin-left: 80%;
}

.card :hover {
  color: #c6ecf8;
}

</style>
