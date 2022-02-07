<template>
  <div id="app">
    <NavBar/>
    <router-view/>
  </div>
</template>

<script>
import NavBar from '@/views/NavBar.vue'

export default {
  name: 'App',
  components:{
    NavBar,
  }
}
</script>

<style>
/* @font-face {
    font-family: 'BMJua';
    font-weight: normal; 
    font-style: normal; 
    src: url(https://cdn.jsdelivr.net/gh/webfontworld/woowahan/BMJua.woff2) format('woff2');
    font-display: swap;
} */

@font-face {
    font-family: 'yg-jalnan';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

#app {
  /* font-family: Avenir, Helvetica, Arial, sans-serif; */
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
  height: 100%;
  font-family: 'yg-jalnan';
  padding-top: 80px;
}

body {
  /* padding-top: 80px; */
  
}
</style>

