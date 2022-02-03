import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";
import axios from 'axios'
import router from '@/router'


Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [
    createPersistedState()  // 새로고침 초기화 방지
  ],
  state: {
    userInfo:null,
    isLogin:false,
    boardNo: null,
    comments: [],
    studyOptions: [],
    studyMembers: [],
  },
  mutations: {
    SIGNUP(state, credentials){
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
    },
    GET_COMMENT(state, commentData) {
      state.comments = commentData
    },
    GET_STUDY_TYPE(state, studyTypeData) {
      console.log('스터디타입확인')
      console.log(state.studyOptions)
      console.log('여기까지 확인')
      state.studyOptions = studyTypeData
    },
    GET_STUDY_MEMBERS(state, memberInfo) {
      state.studyMembers = memberInfo
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
        router.push({name:'LogIn'})
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
    },
    getComment({commit}) {
      axios({
        method: 'get',
        url: `http://i6e103.p.ssafy.io:8080/api/comment/searchAll/${this.state.boardNo}`,
        // headers: this.getToken(),
      })
      .then(res => {
        commit('GET_COMMENT', res.data)
      })
      .catch(err => {
        console.log(err)
      })
    },
    getStudyType({commit}) {
      axios({
        method: 'get',
        url: 'http://i6e103.p.ssafy.io:8080/api/study/studyType'
      })
      .then(res => {
        // console.log('res 확인')
        // console.log(res.data)
        commit('GET_STUDY_TYPE', res.data)
      })
      .catch(err => {
        console.log(err)
      })
    },
    getStudyMembers({commit},studyNo) {
      axios({
        method: 'GET',
        url: `http://i6e103.p.ssafy.io:8080/api/studymember/search/${studyNo}`,
      })
      .then(res => {
        // console.log(res.data)
        commit('GET_STUDY_MEMBERS', res.data)
      })
      .catch(err => {
        console.log(err)
      })
    },
  },
  // getters: {
  //   studyMembers(state){
  //     return state.studyMembers
  //   }
  // },
  modules: {
  }
})
