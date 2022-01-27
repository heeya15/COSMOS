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
    GET_BOARD_NO(state, boardNumber){
      console.log(state)
      console.log('번호 확인')
      state.boardNo = boardNumber
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
        url: 'http://i6e103.p.ssafy.io:8080/api/user/signup',
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
        url: 'http://i6e103.p.ssafy.io:8080/api/auth/login',
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
    getBoardNo({commit}, boardNumber) {
      commit('GET_BOARD_NO',boardNumber)
    },
    logOut({commit}) {
      commit('LOGOUT')
    }
    
  },
  modules: {
  }
})
