<template>
  <div id="app">
    <NavBar v-if="($route.name !== 'PrivateStudyRoom') && ($route.name !== 'PublicStudyRoom')"/>
    <router-view style="margin-top: 100px; min-height:100%"/>
      <div class="my-5"></div>
      <Footer v-if="($route.name !== 'PrivateStudyRoom') && ($route.name !== 'PublicStudyRoom')
        && ($route.name !== 'SignUp') && ($route.name !== 'LogIn') && ($route.name !== 'StudyRoomCreateForm')
        && ($route.name !== 'MainPage')"/>
  </div>
</template>

<script>
import NavBar from '@/views/NavBar.vue'
import Footer from '@/views/Footer.vue'

export default {
  name: 'App',
  data() {
    return {
      timeOut: null,
    }
  },
  components:{
    NavBar,
    Footer,
  },
  methods: {
    scrollToTop() {
      if (document.body.scrollTop!=0 || document.documentElement.scrollTop!=0){
        window.scrollBy(0, -50000);
        this.timeOut=setTimeout(10);
      }
      else clearTimeout(this.timeOut);
    },
  }
}
</script>

<style>

@font-face {
    font-family: 'IM_Hyemin-Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2106@1.1/IM_Hyemin-Bold.woff2') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'GangwonEdu_OTFBoldA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEdu_OTFBoldA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'GangwonEduSaeeum_OTFMediumA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEduSaeeum_OTFMediumA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'SUIT-Medium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_suit@1.0/SUIT-Medium.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'BMJua';
    font-weight: normal; 
    font-style: normal; 
    src: url(https://cdn.jsdelivr.net/gh/webfontworld/woowahan/BMJua.woff2) format('woff2');
    font-display: swap;
}

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
}

body {
  padding-top: 80px;
}


.page-item.active .page-link {  
    background-color: #9255AD !important;  
    border-color: #9255AD !important;  
    color: #fff !important;
}

.page-link {
    color: #9255AD !important;
}

.page-link:hover {
    color: #9255AD;
}
</style>
