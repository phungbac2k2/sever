<script lang="ts">
import Header from "../../components/Header.vue";
import Footer from "../../components/Footer.vue";
import axios from "axios";
import {ref} from "vue";
import {useRouter} from "vue-router";

export default {

  name: "Login",
  components: {Footer, Header},


  setup() {
    const payload = ref({
      gmail: '',
      password: '',

    });
    const router = useRouter();
    const saveDataToSessionStorage = (payload: any) => {
      const data = payload;
      sessionStorage.removeItem("myData");
      sessionStorage.setItem("myData", JSON.stringify(data));
    };
    const login = async () => {
      try {
        const response = await axios.post('http://localhost:8080/api/auth/login', payload.value);
        console.log('API response:', response.data);
        if (response.data.login) {
          saveDataToSessionStorage(response.data);
          if (response.data.role == "ROLE_ADMIN") {
            router.replace("homeadmin")
          } else {
            router.replace("homepage");
          }
        } else {
          console.log("login fail")
        }
      } catch (error) {
        console.error('API error:', error);

      }


    };
    return {
      login,
      router,
      payload,
      saveDataToSessionStorage
    }
  }


}
</script>
<template>

  <div class="login-container ">
    <Header/>
    <div class="bottom">
      <div class="image">
        <img src="https://rabiloo.com/images/homepage/sv/sv1.svg">
      </div>
      <div class="form">
        <h1>Login</h1>
        <form @submit.prevent="login" class="input">
          <label for="gmail">Gmail:</label>
          <br/>
          <input v-model="payload.gmail" type="text" id="gmail" required>
          <br/>
          <label for="password">Password:</label>
          <br/>
          <input v-model="payload.password" type="password" id="password" required>
          <br/>
          <button type="submit" class="button">Login</button>
        </form>
      </div>
    </div>
    <Footer/>

  </div>


</template>


<style scoped>

.login-container {
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

.bottom {
  display: flex;
  flex-direction: row;
  flex: 80;

}

.image {
  width: 50%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.form {
  width: 50%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
}

.input {
  width: 40%;
  margin-left: 35%;
  justify-content: left;
  text-align: left;


}

#gmail {
  height: 30px;
  width: 250px;
  margin-top: 10px;
  margin-bottom: 10px;
}

#password {
  height: 30px;
  width: 250px;
  margin-top: 10px;
  margin-bottom: 10px;
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
</style>