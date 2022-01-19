<template>
  <div>
    <b-navbar variant="faded" type="light">
      <b-navbar-brand tag="h1" class="mb-0">
        <router-link :to="{ name:'MainPage' }" class="text-decoration-none">COSMOS</router-link>
      </b-navbar-brand>
      <router-link :to="{ name:'SignUp' }" class="text-decoration-none">회원가입</router-link>
      <router-link :to="{ name:'LogIn' }" class="text-decoration-none">로그인</router-link>
    </b-navbar>
  </div>
</template>

<script>
export default {
    name:'NavBar',
}
</script>

<style>

</style>