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
      <ul class="navbar-nav m-auto">
        <li class="nav-item fs-4"><router-link :to="{ name:'SignUp' }" class="text-decoration-none">회원가입</router-link></li>
        <li class="nav-item fs-4"><router-link :to="{ name:'LogIn' }" class="text-decoration-none">로그인</router-link></li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
    name:'NavBar',
}
</script>

<style>

</style>