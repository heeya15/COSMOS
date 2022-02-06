<template>
  <div id="navPage">
    <!-- <b-navbar variant="faded" type="light">
      <b-navbar-brand tag="h1" class="mb-0">
        <router-link :to="{ name:'MainPage' }" class="text-decoration-none">COSMOS</router-link>
      </b-navbar-brand>
      <ul class="navbar-nav ml-auto mr-3">
        <li class="nav-item"><router-link :to="{ name:'SignUp' }" class="text-decoration-none">회원가입</router-link></li>
        <li class="nav-item"><router-link :to="{ name:'LogIn' }" class="text-decoration-none">로그인</router-link></li>
      </ul>
    </b-navbar> -->
    <b-navbar class="navbar" id="nav" toggleable="lg" type="dark" variant="">

      <!-- 메인 로고 -->
      <b-navbar-brand href="#" class="d-flex justify-content-start" style="color: #aaa">
        <router-link class="header_nav_home" :to="{ name: 'MainPage' }">
          <img id="logo" src="@/assets/cosmos_logo3.png" alt="cosmos">
        </router-link>
      </b-navbar-brand>

      <router-link :to="{ name:'Openvidu' }" class="nav-text text-decoration-none">openviduTest</router-link>

      <b-navbar-toggle target="nav-collapse" style="background-color: #ddd"></b-navbar-toggle>
      
      <b-collapse id="nav-collapse" is-nav>
        <!-- 로그인 했을 때 -->
        <b-navbar-nav class="nav ml-auto" v-if="this.$store.state.isLogin">

          <b-nav-item href="#">
            <router-link :to="{ name:'MainBoard' }" class="nav-text text-decoration-none mx-1 px-2">스터디 모집</router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <router-link :to="{ name:'StudyRoomCreateForm' }" class="nav-text text-decoration-none mx-1 px-2">스터디 방 생성</router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <router-link :to="{ name:'MyPage' }" class="nav-text text-decoration-none mx-1 px-2">마이페이지</router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <b-button class="m-1" @click="logOut">로그아웃</b-button>
          </b-nav-item>


        </b-navbar-nav>

        <!-- 로그인 안 했을 때 -->
        <b-navbar-nav class="nav ml-auto" v-else>
          <b-nav-item href="#">
            <router-link :to="{ name:'SignUp' }" class="nav-text text-decoration-none mx-1 px-2">회원가입</router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <router-link :to="{ name:'LogIn' }" class="nav-text text-decoration-none mx-1 px-2">로그인</router-link>
          </b-nav-item>
          
        </b-navbar-nav>

      </b-collapse>

    </b-navbar>
  </div>
</template>

<script>
export default {
    name:'NavBar',
    methods: {
      logOut() {
        this.$store.dispatch('logOut')
        this.$router.push({ name: 'LogIn' })
        this.$router.go()
      },
    }
}

</script>

<style scoped> 
  #logo {
    width: 55px;
    height: 50px;
  }

  img {
    width: 100%;
    height: 100%;
  }

  .navbar {
    background-color: #fff;
  }

  #navPage {
    width: 100%;
    /* height: 80px; */
    /* position: relative; */
    /* padding: 0; */
    position: fixed;
    top: 0;
    /* background-color: #aaa; */
    z-index: 99;
  }
  #nav a {
  font-weight: bold;
  color: #2c3e50;
}

  #nav a.router-link-exact-active {
    color: #9255AD;
  }
  #nav {
    height: 100%;
    /* background-color: #fff; */
    margin-bottom: 0;
    /* top: 50%;
    transform: translate(0%,-50%); */
  }

  .nav-text {
    font-size: 20px;
    /* margin: 0 30px; */
  }

  .nav-item{
    line-height: 40px;
  }
</style>