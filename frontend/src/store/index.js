import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";
import axios from 'axios'


Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [
    createPersistedState()  // 새로고침 초기화 방지
  ],
  state: {
    userInfo:null,
    isLogin:false,
    user_ID: null,
  },
  mutations: {
    SIGNUP(state,credentials){
      state.userInfo = credentials
    },
    LOGIN(state, user_ID){
      console.log( '로그인됨!!!!')
      state.user_ID = user_ID
      state.isLogin = true
    }
  },
  actions: {
    signUp({commit}, credentials) {
      // console.log(credentials)
      axios({
        method: 'POST',
        url: 'http://localhost:8080/api/signup',
        data: credentials
      })
      .then(res => {
        // console.log(res)
        commit('SIGNUP', res.data)
      })
      .catch(err => {
        console.log(err)
      })
    },
    logIn({commit}, credentials) {
      axios({
        method: 'post',
        url: 'http://localhost:8080/api/auth/login',
        data: credentials
      })
      .then(res => {
        localStorage.setItem('jwt', res.data.accessToken)
        const user_ID = JSON.parse(res.config.data)['id']
        console.log(JSON.parse(res.config.data))
        commit('LOGIN', user_ID)
      })
      .catch(err => {
        console.log(err)
      })
    },
    
  },
  modules: {
  }
})
