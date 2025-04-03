<template>
  <div>
    <canvas ref="myChartCanvas"></canvas>
  </div>
</template>

<script>
import {ref, onMounted, watch} from 'vue'
import Chart from 'chart.js/auto'

export default {
  name: "PieChart",
  props: {
    data: Object,
    type: String,

  },
  setup(props) {
    console.log(props.data)

    const myChartCanvas = ref(null)
    let myChartInstance = null
    console.log(props.type)
    const chartData = ref()
    watch(() => props.data, (newData) => {
      if (myChartInstance) {
        myChartInstance.data = newData
        myChartInstance.update()
      }
    })
    onMounted(() => {
          chartData.value = props.data
          console.log(chartData.value)
          if (myChartCanvas.value) {
            myChartInstance = new Chart(myChartCanvas.value, {
              type: props.type,
              data: chartData.value,
              options: {
                responsive: true,
                maintainAspectRatio: false,

              },
            })
          }
        }
    )


    return {
      myChartCanvas,
    }
  },
}
</script>
