import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";
// import http from 'http'
import router from '@/router'
import http from "@/util/http-common.js";
import jwtDecode from 'jwt-decode';

Vue.use(Vuex)

import publicStudyStore from './modules/publicStudyStore';
import meetingStore from './modules/meetingStore';
export default new Vuex.Store({
  modules: {
    publicStudyStore,
    meetingStore
  },
  plugins: [
    // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
    createPersistedState({ storage: window.sessionStorage })  // 새로고침 초기화 방지 
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
    // roomName: "",
    // roomUrl: "",
    // participant: "",
    // roomStudyNo: 0,

  },
  mutations: {
    SIGNUP(state, credentials){
      state.userInfo = credentials
    },
    LOGIN(state){
      state.isLogin = true
      state.userId = jwtDecode(localStorage.getItem('jwt')).sub;
    },
    GET_BOARD_NO(state, boardNumber){
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
      state.saveCurrentPage = currentPage
    }
  },
  actions: {
    signUp({commit}, credentials) {
      http({
        method: 'POST',
        url: '/user/signup',
        data: credentials
      })
      .then(res => {
        if(res.status === 200) {
          commit('SIGNUP', res.data)
          alert("🌸🌸 Welcome to COSMOS!! 🌸🌸")
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
  
})
