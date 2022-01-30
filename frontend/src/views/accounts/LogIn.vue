<template>
  <div>

    <center>
      <div style="background-color: #DAC7F9; width:600px">
        <b-form class="p-3" style="width:400px" >
          <p>로그인</p>
          <b-form-input class="mt-3" style="height:50px;" id="id" v-model="credentials.id" required placeholder="아이디"></b-form-input>
          <b-form-input class="mt-3" style="height:50px;" id="password" v-model="credentials.password" type="password" placeholder="비밀번호"></b-form-input>
          <b-button class="mt-3" style="height:50px;" @click="logIn">LOGIN</b-button>
        </b-form>
      </div>
    </center>
  </div>
</template>

<script>

export default {
  name: 'LogIn',
  data() {
    return {
      credentials: {
        id:'',
        password:'',
      }
    }
  },
  methods: {
    logIn() {
      this.$store.dispatch('logIn', this.credentials)
      this.$router.push({name: 'MainPage'})
    }
  }

}
</script>

<style scoped>
p {
  font-size: 40px;
  color: white;
}


</style>