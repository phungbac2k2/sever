<template>
  <div class="container-sta">
    <h2>Thống kê package</h2>
    <div class="chart">
      <div class="chart1 chart-info">
        <Chart class="detail-chart" :data="datatmpBar" :type="typeBar"/>
        <h4>Đồ thị về số lượt đăng ký gói mới trong 5 tháng gần nhất</h4>
      </div>
      <div class="chart2 chart-info">
        <Chart class="detail-chart" :data="datatmpDoughnut" :type="typeDoughnut"/>
        <h4>Đồ thị về số lượt đăng ký của từng gói</h4>

      </div>
    </div>
  </div>
</template>

<script>
import Chart from "./Chart.vue";
import {onMounted, reactive, ref} from "vue";
import axios from "axios";

export default {
  name: "StatisticalPackage",
  components: {Chart},
  setup() {

    const accountForMonth = ref();
    const countPackage = ref();
    const typeBar = ref("bar");
    const dataBar = ref({
      labels: [],
      datasets: [
        {
          label: 'Số tài khoản',
          backgroundColor: 'green',
          data: [],
        },
      ],
    })
    const typeDoughnut = ref("doughnut");

    const dataDoughnut = ref({
      labels: [],
      datasets: [{
        label: 'Số tài khoản',
        data: [],
        backgroundColor: [
          'rgb(255, 99, 132)',
          'rgb(54, 162, 235)',
        ],
        hoverOffset: 4
      }]
    })
    const datatmpBar = ref(null)
    const datatmpDoughnut = ref(null)
    const callAPI = async () => {
      try {
        const response = await axios.get("http://localhost:8080/package/getcountpackagepermonth");
        const response1 = await axios.get("http://localhost:8080/package/getCountPackage")
        accountForMonth.value = response.data;

        dataBar.value.labels[0] = accountForMonth.value[0][0];
        dataBar.value.labels[1] = accountForMonth.value[1][0];
        dataBar.value.labels[2] = accountForMonth.value[2][0];
        dataBar.value.labels[3] = accountForMonth.value[3][0];
        dataBar.value.labels[4] = accountForMonth.value[4][0];
        dataBar.value.datasets[0].data[0] = accountForMonth.value[0][1]
        dataBar.value.datasets[0].data[1] = accountForMonth.value[1][1]
        dataBar.value.datasets[0].data[2] = accountForMonth.value[2][1]
        dataBar.value.datasets[0].data[3] = accountForMonth.value[3][1]
        dataBar.value.datasets[0].data[4] = accountForMonth.value[4][1]


        countPackage.value = response1.data
        dataDoughnut.value.datasets[0].data[0] = countPackage.value[0][1]
        dataDoughnut.value.datasets[0].data[1] = countPackage.value[1][1]
        dataDoughnut.value.labels[0] = countPackage.value[0][0]
        dataDoughnut.value.labels[1] = countPackage.value[1][0]

        test();
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    }

    callAPI();

    const test = () => {
      datatmpBar.value = dataBar.value;
      datatmpDoughnut.value = dataDoughnut.value;
    }


    return {
      typeBar,
      typeDoughnut,
      datatmpBar,
      datatmpDoughnut
    }
  }
}
</script>

<style scoped>
.container-sta {
  display: flex;
  width: 100%;
  flex-direction: column;
  align-items: center;

}

.chart {
  display: flex;
  width: 100%;
  height: 100%;
  padding: 40px;

}

.chart-info {
  display: flex;
  height: 100%;
  width: 50%;
  border: 1px solid gainsboro;
  align-items: center;
  flex-direction: column;
  margin-left: 20px;
  margin-right: 20px;
  border-radius: 6px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);
}

.detail-chart {
  display: flex;
  margin-top: 50px;
  width: 90%;
  height: 70%;
}

</style>