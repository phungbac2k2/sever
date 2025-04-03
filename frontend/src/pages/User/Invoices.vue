<template>
  <div class="packages-container ">
    <Header/>
    <div class="packages-page">
      <h3>Danh sách hóa đơn</h3>
      <div class="body">
        <table>
          <thead>
          <tr>
            <th>ID</th>
            <th>Tên gói</th>
            <th>Chu kỳ(ngày)</th>
            <th>Giá</th>
            <th>Thời gian</th>
            <th>Trạng thái</th>

          </tr>
          </thead>
          <tbody>
          <tr v-for="item in dataInvoices" :key="item" class="row">
            <td>{{ item.id }}</td>
            <td id="colum-name">{{ item.name }}</td>
            <td>{{ item.recurring }}</td>
            <td>{{ item.price }}</td>
            <td>{{ item.time }}</td>
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

export default {
  name: "Packages",
  components: {Header, Footer},
  setup() {
    const dataUser = JSON.parse(sessionStorage.getItem("myData"));
    const idUser = ref(dataUser.id)
    const dataInvoices = ref()
    const currenPage = ref(1)
    const maxPage = ref();
    const sizePage = ref(3);
    const setResult = (x) => {
      dataInvoices.value = []

      for (const element of x) {
        const account = {
          id: element[9],
          name: element[15],
          recurring: element[5],
          price: element[11],
          time: element[12],
          status: ""
        }
        if (element[13] === 1) {
          account.status = "Thành công"

        } else {
          account.status = "Thất bại"
        }
        dataInvoices.value.push(account)
      }

    }
    const GetInvoices = async () => {
      const params = {
        id: idUser.value,
        size: sizePage.value,
        page: currenPage.value

      }
      const res = await axios.get("http://localhost:8080/invoices", {params})
      dataInvoices.value = []
      maxPage.value = res.data.totalPages;
      setResult(res.data.content)

      console.log(dataInvoices.value)
    }
    GetInvoices();
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
      const res = await axios.get("http://localhost:8080/invoices", {params});
      setResult(res.data.content)

    }
    return {
      dataInvoices, currenPage, maxPage, SwitchPage
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

#colum-name {
  width: 200px;
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