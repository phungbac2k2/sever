<template>
  <div class="container">
    <Header/>
    <div class="body">
      <div class="image"></div>

      <div class="banner">
        <h3>VÌ SAO NÊN CHỌN RABILOO SERVER</h3>
        <div class="minibanner">
          <div class="child attt">
            <h4>AN TOÀN THÔNG TIN</h4>
          </div>
          <div class="child tucp">
            <h4>TỐI ƯU CHI PHÍ</h4>
          </div>
          <div class="child sp">
            <h4>HỖ TRỢ 24/7</h4>
          </div>
        </div>

      </div>

      <div class="package">
        <PackageCard v-for="item in dataPackages" v-bind:key="item" v-bind:package="item"/>

      </div>
    </div>
    <Footer/>
  </div>
</template>

<script>
import Header from "../../components/Header.vue";
import Footer from "../../components/Footer.vue";
import PackageCard from "../../components/PackageCard.vue";
import {ref} from "vue"
import axios from "axios";

export default {
  name: "HomePage",
  components: {Footer, Header, PackageCard},

  setup() {

    const dataPackages = ref([]);
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/package/getall');
        dataPackages.value = response.data;
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };


    fetchData();


    return {dataPackages, fetchData}
  }


}


</script>

<style scoped>
.container {
  width: 99vw;
  display: flex;
  flex-direction: column;

}

.body {
  width: 100%;
  height: 1000px;
  display: flex;
  justify-content: center;
  text-align: center;
  flex-direction: column;

}

.image {
  border-radius: 0 0 10px 10px;
  width: 100%;
  flex: 3;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  background-image: url("https://static.ybox.vn/2023/4/4/1680746679930-285373703_1931320387258317_438577038675478797_n.jpg");
}

.banner {
  width: 60%;
  flex: 2.7;
  display: flex;
  margin-left: 20%;
  margin-top: 10px;
  background-color: #bce3f5;
  flex-direction: column;
  border-radius: 0 0 20px 20px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);


}

.banner p {
  justify-content: center;
  display: flex;
  flex: 1;
  color: black;
}

.minibanner {
  display: flex;
  flex-direction: row;
  flex: 10;
  justify-content: center;
  padding-bottom: 20px;
}

.child {
  width: 25%;
  height: 100%;
  margin-left: 40px;
  margin-right: 40px;
}

.attt {
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  background-image: url("https://cdn.lawnet.vn/uploads/tintuc/2022/12/10/an-toan-thong-tin.jpg");

}

.tucp {
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  background-image: url("https://doceye.vn/wp-content/uploads/2022/08/202004101111536509-04fa9d1be1991d852c5c12053a96554d.jpg");
}

.sp {
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  background-image: url("https://worldfone.vn/pictures/getfile/Ho-tro-24-7-la-gi.jpg");
}

.package {
  margin-top: 20px;
  width: 100%;
  display: flex;
  flex: 3;

  justify-content: center;
  padding: 0 30px 0 30px;
}

.container PackageCard {
  width: 50px;
}
</style>