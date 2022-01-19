<template>
  <nav-bar/>
  <h1>메인페이지</h1>
</template>

<script>
import { reactive } from '@vue/reactivity'
import NavBar from './components/NavBar.vue'
export default {
  components: { NavBar },
  setup () {
    const state = reactive({
      username: '',
      password: '',
    }),
    const login = () => {

    }
  }
}
</script>

<style>

</style>
