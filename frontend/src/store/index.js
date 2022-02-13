import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";
// import http from 'http'
import router from '@/router'
import http from "@/util/http-common.js";
import jwtDecode from 'jwt-decode';

Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [
    createPersistedState()  // 새로고침 초기화 방지
  ],
  state: {
    saveCurrentPage: null,
    userInfo:null,
    isLogin:false,
    boardNo: null,
    comments: [],
    studyOptions: [],
    studyMembers: [],
    power: {
      authority: null,
      leader: null
    },
    userId: "",

    // 비공개 스터디룸 state
    roomName: "",
    roomUrl: "",
    participant: "",
    roomStudyNo: 0,

  },
  mutations: {
    SIGNUP(state, credentials){
      state.userInfo = credentials
    },
    LOGIN(state){
      // console.log( '로그인됨!!!!')
      state.isLogin = true
      state.userId = jwtDecode(localStorage.getItem('jwt')).sub;
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
    },
    IS_LEADER(state, leaderInfo){
      state.power.leader = leaderInfo.leader
      state.power.authority = leaderInfo.authority
    },
    PAGE_CLICK(state, currentPage) {
      console.log(state, '페이지 번호 확인 스토어')
      state.saveCurrentPage = currentPage
    }
  },
  actions: {
    signUp({commit}, credentials) {
      // console.log(credentials)
      http({
        method: 'POST',
        url: '/user/signup',
        data: credentials
      })
      .then(res => {
        // console.log(res)
        if(res.status === 200) {
          commit('SIGNUP', res.data)
          router.push({name:'LogIn'})
        }
      })
      .catch(err => {
        console.log(err)
      })
    },
    async logIn({commit}, credentials) {
      await http({
        method: 'post',
        url: '/auth/login',
        data: credentials
      })
      .then(res => {
        if(res.status === 200) {
          localStorage.setItem('jwt', res.data.accessToken)
          commit('LOGIN')
          
        }
      })
      .catch(err => {
        // this.state.loginMsg = "잘못된 아이디 또는 비밀번호입니다.";
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
      http({
        method: 'get',
        url: `/comment/searchAll/${this.state.boardNo}`,
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
      http({
        method: 'get',
        url: '/study/studyType'
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
      http({
        method: 'GET',
        url: `/studymember/search/${studyNo}`,
      })
      .then(res => {
        // console.log(res.data)
        commit('GET_STUDY_MEMBERS', res.data)
      })
      .catch(err => {
        console.log(err)
      })
    },
    isLeader({commit}, studyNo){
      const token = localStorage.getItem('jwt')
      const header = {
        Authorization: `Bearer ${token}`,
      }
      http({
        method: 'GET',
        url: '/user/leader',
        headers: header,
        // data: studyNo
        params: {study_no: studyNo},
      })
      .then(res => {
        // console.log(res)
        commit('IS_LEADER', res.data)
      })
      .catch(err => {
        console.log(err)
      })
    },
    pageClick({commit}, currentPage) {
      commit('PAGE_CLICK', currentPage)
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
