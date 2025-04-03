<script>
import Header from "../../components/Header.vue";
import Footer from "../../components/Footer.vue";
import ShowUser from "../../components/ShowUser.vue";
import Package from "../../components/Package.vue";
import {ref} from "vue";

export default {
  name: "HomeAdmin",
  components: {Footer, Header, ShowUser, Package},
  setup() {
    const currentComponent = ref("Pack");
    const handleChangeComponent = (nameComponent) => {
      currentComponent.value = nameComponent
    }
    return {
      handleChangeComponent,
      currentComponent
    }
  }

}
</script>
<template>
  <div class="payment-container ">
    <Header @change-component-event="handleChangeComponent"/>
    <div class="home-admin-page">
      <ShowUser v-if="currentComponent==='User'"/>
      <Package v-if="currentComponent==='Pack'"/>
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

.home-admin-page {
  display: flex;
  flex: 80;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

</style>