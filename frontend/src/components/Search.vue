<script>
import {ref} from "vue";
import axios from "axios";
import ShowInvoices from "./ShowInvoices.vue"
import ShowPackage from "./ShowPackage.vue";

export default {
  name: "Search",
  components: {ShowInvoices, ShowPackage},
  setup() {
    const selectedFruit = ref("name");
    const dataInput = ref("");
    const result = ref([]);
    const idSelectUser = ref(null);
    const showInvoices = ref(false);
    const showPackage = ref(false);
    const dataInvoices = ref();
    const dataPackage = ref()
    const currenPage = ref(1)
    const maxPage = ref();
    const sizePage = ref(2);
    const isSearch = ref(false);
    const getAll = async () => {
      const params = {
        page: currenPage.value,
        size: sizePage.value
      }
      const res = await axios.get("http://localhost:8080/account/getall", {params})
      maxPage.value = res.data.totalPages;
      setResult(res.data.content)
    }

    getAll();
    const Search = async () => {
      isSearch.value = true
      currenPage.value = 1
      const params = {
        data: dataInput.value,
        type: selectedFruit.value,
        page: currenPage.value,
        size: sizePage.value
      }
      const res = await axios.get("http://localhost:8080/account/search", {params})
      maxPage.value = res.data.totalPages;
      setResult(res.data.content)
    }
    const setResult = (x) => {
      result.value = []

      for (const element of x) {
        const account = {
          id: element[0],
          name: element[1],
          numberPhone: element[2],
          gmail: element[3],
          role: element[5],
          timeCreate: element[7]
        }
        result.value.push(account)
      }

    }
    const SelectUser = (id) => {
      if (idSelectUser.value != null) {
        document.getElementById(idSelectUser.value).style.backgroundColor = "";
      }
      idSelectUser.value = id;
      console.log(idSelectUser.value)
      document.getElementById(id).style.backgroundColor = "gray";

    }
    const ViewInvoices = async (idSelect) => {
      dataInvoices.value = idSelect;
      showInvoices.value = true;

    }
    const handleChangeComponent = (x) => {
      showInvoices.value = x;
    }
    const handleChangeComponent1 = (x) => {
      showPackage.value = x;
    }
    const ViewPackage = async (idSelect) => {
      dataPackage.value = idSelect;
      showPackage.value = true;
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
      const apiUrl = ref();
      if (isSearch) {
        apiUrl.value = "http://localhost:8080/account/search"
      } else {
        apiUrl.value = "http://localhost:8080/account/getall"
      }
      const params = {
        data: dataInput.value,
        type: selectedFruit.value,
        page: currenPage.value,
        size: sizePage.value
      }
      const res = await axios.get(apiUrl.value, {params});
      setResult(res.data.content)

    }
    return {
      selectedFruit,
      Search,
       dataInput,
      result,
      SelectUser,
      ViewInvoices,
      ViewPackage,
      showInvoices,
      showPackage,
      dataInvoices,
      handleChangeComponent,
      handleChangeComponent1,
      dataPackage,
      currenPage,
      maxPage,
      SwitchPage
    }
  }
}
</script>
<template>
  <div class="container-search">
    <h2>Tìm kiếm thông tin người dùng</h2>
    <ShowInvoices @change-component-event="handleChangeComponent" v-if="showInvoices" :id="dataInvoices"/>
    <ShowPackage @change-component-event="handleChangeComponent1" v-if="showPackage" :id="dataPackage"/>

    <div class="search-field">
      <label for="typesearch">Tìm kiếm theo: </label>
      <select id="typesearch" v-model="selectedFruit" name="typesearch">
        <option value="name">Tên</option>
        <option value="gmail">Gmail</option>
        <option value="numberPhone">Số điện thoại</option>
      </select>
      <input class="input" v-model="dataInput" type="text">
      <button class="button" @click="Search">Search</button>
    </div>
    <div class="table-data">

      <table>
        <thead>
        <tr>
          <th>ID</th>
          <th class="name">Tên</th>
          <th class="gmail">Gmail</th>
          <th class="sdt">Số điện thoại</th>
          <th>Ngày đăng ký</th>
          <th>Chi tiết</th>

        </tr>
        </thead>
        <tbody>
        <tr v-for="item in result" :key="item" class="row" @click="SelectUser(item.id)" :id=item.id>
          <td>{{ item.id }}</td>
          <td class="name">{{ item.name }}</td>
          <td class="gmail">{{ item.gmail }}</td>
          <td class="sdt">{{ item.numberPhone }}</td>
          <td>{{ item.timeCreate }}</td>
          <td id="test">
            <button class="button" @click="ViewInvoices(item.id)">Xem hóa đơn</button>
            <button class="button" @click="ViewPackage(item.id)">Xem Gói</button>
          </td>

        </tr>
        </tbody>
      </table>

    </div>
    <div class="list-button">
      <div @click="SwitchPage(-1)"> &lt</div>
      <div>{{ currenPage }}/{{ maxPage }}</div>
      <div @click="SwitchPage(1)"> &gt</div>
    </div>
  </div>
</template>
<style scoped>
.container-search {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px;
}

.search-field {
  width: 100%;
  height: 10%;
  display: flex;
  align-items: center;
  padding-left: 20px;
  border-bottom: 1px solid gray;

}

.search-field * {
  margin-right: 10px;
}

.table-data {
  display: table;
  width: 100%;
  height: 80%;
}

.list-button {
  display: flex;
  width: 100%;
  height: 10%;
  flex-direction: row;
  justify-content: center;
  align-items: center;

}

.list-button button {
  margin-right: 100px;
  margin-left: 100px;
}

.input {
  height: 30px;
  width: 250px;
}

.button {
  height: 30px;
  width: 120px;
  background-color: #004cb8;
  color: azure;
  margin-right: 20px;

}

.button:hover {
  color: black;
}

#test {
  display: flex;
  height: 100%;
  align-items: center;
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

.list-button div {
  cursor: pointer;

  margin-right: 5px;
  margin-left: 5px;
}

.name {
  width: 200px;
}

.gmail {
  width: 250px;
}

.sdt {
  width: 150px;
}
</style>