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
    boardNo: null,
  },
  mutations: {
    SIGNUP(state,credentials){
      state.userInfo = credentials
    },
    LOGIN(state){
      console.log( '로그인됨!!!!')
      state.isLogin = true
    },
    GET_BOARD_NO(state){
      console.log(state)
      console.log('번호 확인')
      // state.boardNo
    },
    LOGOUT(state){
      state.isLogin=false
      localStorage.removeItem('jwt')
    }
  },
  actions: {
    signUp({commit}, credentials) {
      // console.log(credentials)
      axios({
        method: 'POST',
        url: 'http://localhost:8080/api/user/signup',
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
        commit('LOGIN')
      })
      .catch(err => {
        console.log(err)
      })
    },
    getBoardNo({commit}, ) {
      axios({
        method: 'get',
        url: 'http://localhost:8080/api/board/searchAll',
        // headers: this.
      })
      .then(res => {
        console.log(res)
        commit('GET_BOARD_NO')
      })
      .catch(err => {
        console.log(err)
      })
    },
    logOut({commit}) {
      commit('LOGOUT')
    }
    
  },
  modules: {
  }
})
