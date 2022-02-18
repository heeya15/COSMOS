const meetingStore = {
  namespaced: true,
  state: {
    isaudio: false,
    isvideo: false,
  },
  mutations: {
    // SET_ROOM_SETTIG_ISAUDIO(state, audio) {
    //   state.isaudio = audio;
    //   // 공개방 들어오면서 셋팅 값 뭐임?
    //   console.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>dsadsadwqrwqrwqrwq")
    //   console.log(state.isaudio);
    // },
    // SET_ROOM_SETTIG_ISVIDIO(state, video) {
    //   state.isvideo = video;
    //   console.log(state.isvideo);
    // },
    SET_ROOM_SETTING(state, settings) {
      state.isaudio = settings.mic
      state.isvideo = settings.cam
    }
  }
};

export default meetingStore;
