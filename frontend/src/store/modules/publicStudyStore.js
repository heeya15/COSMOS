const publicStudyStore = {
    namespaced:true,
    state: {
        roomName: "",
        roomUrl: "",
        participant: "",
        studyRule: "",
        // roomStudyNo: 0,
    },
    mutations: {
        SET_ROOM_NAME(state, name) {
            state.roomName = name
        },
        SET_ROOM_URL(state, url) {
            state.roomUrl = url
        },
        SET_PARTICIPANT(state, par) {
            state.participant = par
        },
        SET_STUDYRULE(state, rule) {
            state.studyRule = rule
        }
        // GET_ROOM_STUDY_NO(state, studyNO) {
        //     state.roomStudyNo = studyNO
        // },
    }
};

export default publicStudyStore;