<script>
import {ref} from "vue";
import axios from "axios";

export default {
  name: "ShowPackage",
  props: ['id'],
  setup(props, {emit}) {
    const idUser = ref(props.id)
    const dataPackage = ref()
    const currenPage = ref(1)
    const maxPage = ref();
    const sizePage = ref(3);
    const setResult = (x) => {
      dataPackage.value = []

      for (const element of x) {
        const packageInfo = {
          name: element[10],
          price: element[4],
          recurring: element[5],
          lastpayment: element[3],
          descreption: element[16],
          status: ""
        }
        if (element[8] === 1) {
          packageInfo.status = "Hoạt động"
        } else {
          if (element[8] === 0) {
            packageInfo.status = "Đã hủy gia hạn"

          } else {
            packageInfo.status = "Đã hủy"
          }
        }
        dataPackage.value.push(packageInfo)
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
    const close = () => {
      emit('change-component-event', false);
    }
    return {
      dataPackage, SwitchPage, currenPage, maxPage, close
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
            <th>Tên gói</th>
            <th>Giá</th>
            <th>Chu kỳ(ngày)</th>
            <th>Thanh toán gần nhất</th>
            <th>Mô tả</th>
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
  background-color: rgba(0, 0, 0, 0.5); /* Một lớp nền mờ để tạo hiệu ứng modal */
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

.button {
  color: red
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
</style>
