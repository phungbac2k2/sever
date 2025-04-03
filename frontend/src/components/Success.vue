<script>
import {ref} from "vue";
import {useRouter} from "vue-router"

export default {
  name: "Success",
  props: ['mes', 'url'],
  setup(props, {emit}) {
    const router = useRouter();

    const mes = ref(props.mes)
    const ClickOk = () => {
      if (props.url !== "") {
        const path = "/" + props.url
        console.log(router.currentRoute.value.path)
        if (path === router.currentRoute.value.path) {
          router.go(props.url)
        } else {
          router.replace(props.url)
        }
      } else {
        emit('change-component-event', false);

      }


    }
    return {
      mes,
      ClickOk
    }
  }
}


</script>
<template>
  <div class="modal">
    <div class="body">
      <div class="mes">
        <p>{{ mes }}</p>
      </div>
      <div class="list-button">
        <button class="button" @click="ClickOk">OK</button>
      </div>

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

.body {
  display: flex;
  width: 30%;
  height: 20%;
  background-color: #f2f2f2;
  flex-direction: column;
  border-radius: 15px;
  justify-content: center;

}

.mes {
  display: flex;
  width: 100%;
  height: 65%;
  justify-content: center;
  align-items: center;
}

.list-button {
  display: flex;
  width: 100%;
  height: 35%;
  justify-content: center;

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

p {
  font-size: 19px; /* Kích thước chữ */
  font-weight: bold;
}
</style>
