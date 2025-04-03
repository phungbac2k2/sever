<script>
import {ref} from "vue";
import axios from "axios";

export default {
  name: "ShowInvoices",
  props: ['id'],
  setup(props, {emit}) {
    const idUser = ref(props.id)
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
    const close = () => {
      emit('change-component-event', false);
    }
    return {
      dataInvoices, currenPage, maxPage, SwitchPage, close

    }
  }
}


</script>
<template>
  <div class="modal">
    <div class="back">
      <button @click="close" class="button">X</button>
      <div class="table">
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

      <div class="selectPage">
        <div @click="SwitchPage(-1)"> &lt</div>
        <div>{{ currenPage }}/{{ maxPage }}</div>
        <div @click="SwitchPage(1)"> &gt</div>
      </div>
      <slot></slot>
    </div>

  </div>
</template>

<style scoped>
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4); /* Một lớp nền mờ để tạo hiệu ứng modal */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
  flex-direction: column;
}

table {
  width: 1000px;
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

.selectPage {
  margin-top: 20px;
  display: flex;
  flex-direction: row;
  width: 100%;
  align-items: center;
  justify-content: center;
}

.table {
  height: 250px;
  display: table;
}

.back {
  background-color: #cedfe3;
  justify-content: center;
  align-items: center;
  padding: 10px;
}

.selectPage div {
  cursor: pointer;

  margin-right: 5px;
  margin-left: 5px;
}

#colum-name {
  width: 200px;
}

.button {
  color: red
}
</style>
