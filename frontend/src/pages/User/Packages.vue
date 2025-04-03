<template>
  <div class="packages-container ">
    <Header/>
    <Success v-if="showSuccess" mes="Bạn đã hủy gói thành công!" url="packages"/>
    <div class="packages-page">
      <h3>Danh sách gói đã đăng ký</h3>
      <div class="body">
        <table>
          <thead>
          <tr>
            <th>Tên gói</th>
            <th>Giá</th>
            <th>Chu kỳ(ngày)</th>
            <th>Thanh toán gần nhất</th>
            <th>Mô tả</th>
            <th>Key server</th>
            <th>Action</th>
            <th>Trạng thái</th>

          </tr>
          </thead>
          <tbody>
          <tr v-for="item in dataPackage" :key="item" class="row">
            <td>{{ item.name }}</td>
            <td>{{ item.price }}</td>
            <td>{{ item.recurring }}</td>
            <td>{{ item.lastpayment }}</td>
            <td>{{ item.descreption }}</td>
            <td>{{ item.key }}</td>
            <td>
              <button v-if="item.status=='Hoạt động'" @click="Cancel(item.idSub)"> Hủy gói</button>
            </td>
            <td>{{ item.status }}</td>

          </tr>
          </tbody>
        </table>
      </div>
      <div class="list-page">
        <div @click="SwitchPage(-1)"> &lt</div>
        <div>{{ currenPage }}/{{ maxPage }}</div>
        <div @click="SwitchPage(1)"> &gt</div>
      </div>
    </div>
    <Footer/>
  </div>
</template>


<script>
import Header from "../../components/Header.vue";
import Footer from "../../components/Footer.vue";
import {ref} from "vue";
import axios from "axios";
import Success from "../../components/Success.vue";


export default {
  name: "Packages",
  components: {Header, Footer, Success},
  setup() {
    const showSuccess = ref(false)
    const dataUser = JSON.parse(sessionStorage.getItem("myData"));
    const idUser = ref(dataUser.id)
    const dataPackage = ref()
    const currenPage = ref(1)
    const maxPage = ref();
    const sizePage = ref(3);
    const click = ref(1)
    const setResult = (x) => {
      dataPackage.value = []

      for (const element of x) {
        if (element[8] !== -1) {
          const packageInfo = {
            idSub: element[0],
            name: element[10],
            price: element[4],
            recurring: element[5],
            lastpayment: element[3],
            descreption: element[16],
            key: element[7],
            status: "X"
          }
          if (element[8] === 1) {
            packageInfo.status = "Hoạt động"
          } else {
            if (element[8] === 0) {
              packageInfo.status = "Đã hủy gia hạn"
            }
          }
          dataPackage.value.push(packageInfo)
        }

      }

    }
    const GetPackage = async () => {
      const params = {
        id: idUser.value,
        size: sizePage.value,
        page: currenPage.value
      }
      const res = await axios.get("http://localhost:8080/package/findbyaccountid", {params})
      maxPage.value = res.data.totalPages;
      dataPackage.value = []
      setResult(res.data.content)

    }
    GetPackage();

    const Cancel = async (idSub) => {
      const params = {
        idSub: idSub
      }
      const res = await axios.get("http://localhost:8080/package/cancel", {params})
      console.log(res.data)
      showSuccess.value = true;
    }

    const SwitchPage = async (x) => {

      if (x === -1) {
        if (currenPage.value !== 1) {
          currenPage.value--;
        }
      } else {
        if (currenPage.value !== maxPage.value) {
          currenPage.value++;
        }
      }

      const params = {
        id: idUser.value,
        page: currenPage.value,
        size: sizePage.value
      }
      const res = await axios.get("http://localhost:8080/package/findbyaccountid", {params});
      setResult(res.data.content)

    }
    return {
      dataPackage, Cancel, SwitchPage, currenPage, maxPage, showSuccess
    }
  }
}
</script>

<style scoped>
.packages-container {
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

.packages-page {
  display: flex;
  flex: 80;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.body {
  display: table;
  height: 100%;
  width: 100%;
  padding: 10px;
}

.list-page {
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: center;
}

table {
  width: 100%;
  border-collapse: collapse;
  font-family: Arial, sans-serif;
}

th {
  background-color: #f2f2f2;
  text-align: left;
  padding: 10px;
  border: 1px solid #ccc;
  height: 40px;
}


tr {
  background-color: #f7f7f7;
  height: 40px;
}


td {
  padding: 8px;
  border: 1px solid #ccc;
  height: 20px;
}


tr:hover {
  background-color: #c9bebe;
}

.list-page div {
  cursor: pointer;

  margin-right: 5px;
  margin-left: 5px;
}
</style>