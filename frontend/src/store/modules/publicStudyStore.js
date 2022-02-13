const publicStudyStore = {
    namespaced:true,
    state: {
        roomName: "",
        roomUrl: "",
        participant: "",
        // roomStudyNo: 0,
    },
    mutations: {
        GET_ROOM_NAME(state, name) {
            state.roomName = name
        },
        GET_ROOM_URL(state, url) {
            state.roomUrl = url
        },
        GET_PARTICIPANT(state, par) {
            state.participant = par
        },
        // GET_ROOM_STUDY_NO(state, studyNO) {
        //     state.roomStudyNo = studyNO
        // },
    }
  };
  
  export default publicStudyStore;