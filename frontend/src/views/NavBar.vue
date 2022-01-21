<template>
  <div>
    <!-- <b-navbar variant="faded" type="light">
      <b-navbar-brand tag="h1" class="mb-0">
        <router-link :to="{ name:'MainPage' }" class="text-decoration-none">COSMOS</router-link>
      </b-navbar-brand>
      <ul class="navbar-nav ml-auto mr-3">
        <li class="nav-item"><router-link :to="{ name:'SignUp' }" class="text-decoration-none">회원가입</router-link></li>
        <li class="nav-item"><router-link :to="{ name:'LogIn' }" class="text-decoration-none">로그인</router-link></li>
      </ul>
    </b-navbar> -->
    <nav class="navbar d-flex">
      <a class="navbar-brand" tag="h1">
        <router-link :to="{ name:'MainPage' }" class="text-decoration-none">COSMOS</router-link>
      </a>
      <!-- <div class="nav ml-auto" v-if="!isLogin"> -->
      <div class="nav ml-auto" v-if="this.$store.state.isLogin">
        <!-- <router-link :to="{ name:'SignUp' }" class="text-decoration-none m-1">회원가입</router-link>
        <router-link :to="{ name:'LogIn' }" class="text-decoration-none m-1">로그인</router-link> -->
        <a href="#" class="text-decoration-none m-1">스터디 모집</a>
        <a href="#" class="text-decoration-none m-1">스터디 방 생성</a>
        <a href="/mypage" class="text-decoration-none m-1">마이페이지</a>
        <b-button class="m-1" @click="logOut">로그아웃</b-button>
      </div>
      <div class="nav ml-auto" v-else>
        <router-link :to="{ name:'SignUp' }" class="text-decoration-none m-1">회원가입</router-link>
        <router-link :to="{ name:'LogIn' }" class="text-decoration-none m-1">로그인</router-link>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
    name:'NavBar',
    methods: {
      logOut() {
        this.$store.state.isLogin=false
        localStorage.removeItem('jwt')
        this.$router.push({ name: 'LogIn' })
      }
    }
}

</script>

<style>

</style>