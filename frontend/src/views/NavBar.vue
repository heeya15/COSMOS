<template>
  <div id="navPage">
    <b-navbar class="navbar" id="nav" toggleable="lg">

      <!-- 메인 로고 -->
      <b-navbar-brand class="d-flex justify-content-start">
        <router-link class="header_nav_home" :to="{ name: 'MainPage' }">
          <img id="logo" src="https://i.ibb.co/LJfmXpX/cosmos-logo3.png" alt="cosmos">
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse" style="background-color: #c8c1e4"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <!-- 로그인 했을 때 -->
        <b-navbar-nav class="nav ml-auto" v-if="this.$store.state.isLogin">
          <b-nav-item>
            <router-link :to="{ name:'MainBoard' }" class="nav-text text-decoration-none mx-1 px-2">스터디 모집</router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link :to="{ name:'StudyRoomCreateForm' }" class="nav-text text-decoration-none mx-1 px-2">스터디 방 생성</router-link>
          </b-nav-item>

          <!-- 관리자인 경우 -->
          <b-nav-item v-if="this.$store.state.userId == 'admin'">
            <router-link :to="{ name:'AdminPage' }" class="nav-text text-decoration-none mx-1 px-2">회원관리</router-link>
          </b-nav-item>

          <b-nav-item v-else>
            <router-link :to="{ name:'MyPage' }" class="nav-text text-decoration-none mx-1 px-2">마이페이지</router-link>
          </b-nav-item>
          <b-nav-item>
            <div class="nav-text mx-1 px-2 text-decoration-none" @click="logOut">로그아웃</div>
          </b-nav-item>
        </b-navbar-nav>

        <!-- 로그인 안 했을 때 -->
        <b-navbar-nav class="nav ml-auto" v-else>
          <b-nav-item>
            <router-link :to="{ name:'SignUp' }" class="nav-text text-decoration-none mx-1 px-2">회원가입</router-link>
          </b-nav-item>
          <b-nav-item>
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
      window.onbeforeunload = null;
      console.log(">>>>>>>>>>>>> Nav: ",);
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
    position: fixed;
    top: 0;
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
    margin-bottom: 0;
  }

  .nav-text {
    font-size: 20px;
  }

  .nav-item{
    line-height: 40px;
  }
</style>