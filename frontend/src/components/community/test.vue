<template>
  <div>
    <div v-for="(pick, idx) in picks" :key="idx">
      <img :src="pick.imgUrl" alt="이미지" style="width: 300px; height: 250px;">
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      picks: [
        {
          imgUrl: require("../../../public/코딩고양이.gif")
        },
        {
          imgUrl: require("../../../public/테마2.jpg")
        },
        {
          imgUrl: require("../../../public/테마3.jpg")
        },
        {
          imgUrl: require("../../../public/테마4.png")
        },
        {
          imgUrl: require("../../../public/테마5.jpg")
        },
        {
          imgUrl: require("../../../public/테마6.jpg")
        },
        {
          imgUrl: require("../../../public/테마7.jpg")
        },
        {
          imgUrl: require("../../../public/테마8.jpg")
        },
      ],
    }
  }
}
</script>

<style>

</style>