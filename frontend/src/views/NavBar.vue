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
    <nav class="navbar d-flex" id="nav">
      <a class="navbar-brand" href="/">
        <!-- <img src="@/assets/cosmos_logo1.png" alt="cosmos" width="30" height="24"> -->
        <img id="logo" src="@/assets/cosmos_logo3.png" alt="cosmos">
        <!-- <router-link :to="{ name:'MainPage' }" class="text-decoration-none">COSMOS</router-link> -->
      </a>
      <router-link :to="{ name:'Openvidu' }" class="nav-text text-decoration-none">openviduTest</router-link>
      <!-- <div class="nav ml-auto" v-if="!isLogin"> -->
      <div class="nav ml-5 align-items-center" v-if="this.$store.state.isLogin">
        <router-link :to="{ name:'MainBoard' }" class="nav-text text-decoration-none mx-1">스터디 모집</router-link>
        <router-link :to="{ name:'StudyRoomCreateForm' }" class="nav-text text-decoration-none mx-1">스터디 방 생성</router-link>
        <router-link :to="{ name:'MyPage' }" class="nav-text text-decoration-none mx-1">마이페이지</router-link>
        <b-button class="m-1" @click="logOut">로그아웃</b-button>
      </div>
      <div class="nav ml-5" v-else>
        <router-link :to="{ name:'SignUp' }" class="nav-text text-decoration-none mx-1">회원가입</router-link>
        <router-link :to="{ name:'LogIn' }" class="nav-text text-decoration-none mx-1">로그인</router-link>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
    name:'NavBar',
    methods: {
      logOut() {
        this.$store.dispatch('logOut')
        this.$router.push({ name: 'LogIn' })
      }
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
    margin-bottom: 0;
  }

  #navPage {
    height: 10%;
    position: relative;
    padding: 0;
  }

  #nav {
    top: 50%;
    transform: translate(0%,-50%);
  }

  .nav-text {
    font-size: 20px;
  }
</style>