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
    commentInfo: null,
    comments: [],
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
    },
    CREATE_COMMENT(state, commentInput) {
      console.log(state)
      console.log(commentInput)
      state.commentInfo = commentInput
    },
    GET_COMMENT(state, commentData) {
      state.comments = commentData
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
    createComment({commit}, commentInput) {
      const createCommentItem = {
        board_no: this.state.boardNo,
        // comment_no: this.commentInput.comment_no,
        content: commentInput.content,
        user_id: 'test2',
      }
      axios({
        method: 'post',
        url: 'http://i6e103.p.ssafy.io:8080/api/comment/register',
        data: createCommentItem,
        // headers: this.getToken()

      })
      .then(res => {
        var commentInput = JSON.parse(res.config.data)
        commit('CREATE_COMMENT', commentInput)
        console.log('댓글부분')
        console.log(res)
        console.log('댓글')
        
      })
      .catch(err => {
        console.log(err)
      })
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
  },
  modules: {
  }
})
