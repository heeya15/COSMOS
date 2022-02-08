<template>
	<div id="main">
		<div id="main-container">
			<div id="join" v-if="!session">
				<!-- <div id="img-div"><img src="resources/images/openvidu_grey_bg_transp_cropped.png" /></div> -->
				<div id="join-dialog" class="jumbotron vertical-center">
					<h1>Join a video session</h1>
					<div class="form-group">
						<!-- 참가자 아이디 입력 -->
						<p>
							<label>Participant</label>
							<input v-model="myUserName" class="form-control" type="text" required>
						</p>
						<!-- 방 제목 입력 -->
						<p>
							<label>Session</label>
							<input v-model="mySessionId" class="form-control" type="text" required>
						</p>
						<!-- 방 입장 버튼 -->
						<p class="text-center">
							<button class="btn bftn-lg btn-success" @click="joinSession()">Join!</button>
						</p>
					</div>
				</div>
			</div>
			<div id="session-aside-left" v-if="session">
				<p><img src="../assets/img/openvidu/asideimg01.png" class="sideMenuImg" alt="settings"></p>
				<p><img src="../assets/img/openvidu/asideimg02.png" class="sideMenuImg" alt="score" @click="scoreModal=true"></p>
				
				<!-- 상벌점기능 모달 -->
				<div v-if="scoreModal" class="black-bg">
					<div class="white-bg">
						<h2>멤버 (점수는 즉시 반영됩니다.)</h2>
						<hr>						
						<table class="table table-bordered table-hover align-middle">
							<thead class="table-danger">
								<tr>
									<th>이름</th>
									<th>Email</th>
									<th>출석여부</th>
									<th>공부시간</th>
									<th>점수</th>
								</tr>
							</thead>
							<tbody v-for="member in studyMembers" :key="member.id">
								<tr>
								<td>{{member.user_name}}</td>
								<td>{{member.user_email}}</td>
								<td>{{member.attendance}}</td>
								<td>{{member.studytime}}</td>
								<td><button class="score-btn" @click="minusScore(member.score, member.studymember_no)">-</button>{{member.score}}<button class="score-btn" @click="plusScore(member.score, member.studymember_no)">+</button></td>
								</tr>
							</tbody>
						</table>
						<div class="d-flex justify-content-end">
							<button @click="scoreModal=false" class="btn btn-secondary">닫기</button>
						</div>
					</div>
				</div>

				<p><img src="../assets/img/openvidu/asideimg03.png" class="sideMenuImg" alt="calendar"></p>
			</div>
			<div id="session-aside-right" v-if="session">
				<div class="participant">
					<p>참가자</p>
						<div class="participant_list"> <!-- 참가자 리스트 화면 -->
							<user-list :stream-manager="publisher"/>
							<user-list v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub"/>
						</div>
				</div>

				<!-- 채팅 기능 시작 -->
				<div class="chat">
					<p>채팅</p>
					<div class="messages" ref="messages">
						<div class="messageLoop" v-for="(message, idx) in messages" :key="idx">
							<!-- <div class="text-left" >{{ userId }} 님의 메시지:</div> -->
							<div class="text-left message__bubble">{{ message }}</div>
						</div>
					</div>	

					<form class="chatFooter" onsubmit="return false">
						<input class="chat_input" id="msg" type="text" placeholder="메세지를 입력하세요.">
						<button id="submitBtn" type="submit" @click="sendMessage()">Enter</button>
					</form>
				</div>
				<!-- 채팅 기능 끝 -->

			</div>
			<div id="session" v-if="session">
				<div id="session-header">
					<h1 id="session-title">{{ this.roomName }}</h1> <!-- 방 제목 -->
				</div>
				
				<div>
					<!--
					<div id="main-video" class="col-md-6"> 본인 화면 
						<user-video :stream-manager="mainStreamManager"/>
					</div>
					-->
					<div id="video-container" class=""> <!-- 참가자 화면 -->
						<user-video :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
						<user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
					</div>
				</div>
			</div>
			<div id="session-footer" v-if="session">
				<div class="session-footer_btn d-flex justify-content-center">
					<!-- microphone 버튼 설정 -->
					<div v-if="audio === true" class="buttomMenu">
						
						<button class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" @click="muteAudio()">
							<b-icon icon="mic-fill" class="buttomMenuIcon" aria-hidden="true"></b-icon>
							<span class="footerBtnText">{{ audioMsg }}</span>
						</button> <!-- 마이크 on/off 버튼 -->
					</div>
					<div v-else class="roomFun buttomMenu">
						<button class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" @click="muteAudio()">
							<b-icon icon="mic-mute-fill" class="buttomMenuIcon" aria-hidden="true"></b-icon>
							<span class="footerBtnText">{{ audioMsg }}</span>
						</button><!-- 마이크 on/off 버튼 -->
					</div>	

					<!-- video 버튼 설정 -->
					<div v-if="video === true" class="buttomMenu">
						<!-- <input class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" :value="video" @click="muteVideo()"> 비디오 on/off 버튼 -->
						<button class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" @click="muteVideo()"> 
							<b-icon icon="camera-video-fill" class="buttomMenuIcon" aria-hidden="true"></b-icon>
							<span class="footerBtnText">{{ videoMsg }}</span>
						</button>
					</div>
					<div v-else class="roomFun buttomMenu">
						<button class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" @click="muteVideo()"> 
							<b-icon icon="camera-video-off-fill" class="buttomMenuIcon" aria-hidden="true"></b-icon>
							<span class="footerBtnText">{{ videoMsg }}</span>
						</button>
					</div>

					<!-- 화면공유 버튼 설정 -->
					<div v-if="sharing === true" class="buttomMenu">
						<button class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" @click="startScreenSharing()">
							<b-icon icon="file-arrow-up" class="buttomMenuIcon" aria-hidden="true" ></b-icon>
							<span class="footerBtnText">화면공유</span>
						</button> <!-- 나가기 버튼 -->
					</div>

					<!-- 화면공유 중지 버튼 설정 -->
					<div v-else class="buttomMenu">
						<button class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" @click="leaveSessionForScreenSharing()">
							<b-icon icon="file-arrow-down" class="buttomMenuIcon" aria-hidden="true" ></b-icon>
							<span class="footerBtnText">공유중지</span>
						</button> <!-- 나가기 버튼 -->
					</div>
					
					<!-- 나가기 버튼 설정 -->
					<div class="buttomMenu">
						<button class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" @click="leaveSession">
							<b-icon icon="door-open-fill" class="buttomMenuIcon" aria-hidden="true"></b-icon>
							<span class="footerBtnText">나가기</span>
						</button> <!-- 나가기 버튼 -->
					</div>
				</div>	
			</div>
		</div> <!-- #container -->
		
	</div>
</template>
<style scoped>
/** 채팅창 반응형 */
@media (max-width: 770px){
	#session-aside-right{
		display: none;
	}
	
}
/** footer 버튼 반응형 */
@media (max-width: 1050px) {
	#session-footer{
		width: 270px !important;
	}

	.footerBtnText{
		display: none;
	}
	.buttomMenuIcon{
		margin-right: 0 !important;
	}
}

/* 상벌점 기능 스타일 */
.score-btn {
	border: none;
}
.black-bg{
  width: 100vw;
  margin-left: calc(-50vw + 50%);
	z-index: 2;
  height: 100vw;
  background: rgba(0,0,0,0.5);
  position: fixed;
  left: 0;
  top: 0;
  padding: 20px;
}
.white-bg{
	margin-left:35%;
	margin-top: 10%;
	z-index: 3;
  width: 30%;
  background: white;
  border-radius: 8px;
  padding: 20px;
}
/* 상벌점 스타일 끝 */
#main{
	height: 100%;
	/* margin-top: -70px; */
	margin-top: 0px !important;
	
}
#main-container{
	position: relative;
	width: 90%;
	height: 100%;
	margin: 0 auto;
}
#session {
	/* position: relative; */
	width: 100%;
	height: 90%;
	overflow: auto;
}
#session::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
}
#session-aside-left{
	height: 100%;
	position: absolute;
	left: -40px;
	background-color: #ccc;
}

#session-aside-left p:first-child{
	margin-top: 10px;
}

#session-aside-left p{
	padding: 0 8px;
}

#session-aside-left p img{
	width: 25px;
	height: 25px;
}

#session-aside-right{
	width: 30%;
	height: 100%;
	position: absolute;
	right: 0px;
	/* background-color: #ccc; */
}
#session-aside-right p{
	margin-bottom: 5px;
}
#session-aside-right .participant{
	height: 35%;
	margin-bottom: 20px;
}
#participant-container{
	background-color: #ccc;
}
#session-aside-right .participant .participant_list{
	width: 100%;
	height: 90%;
	background-color: #ccc;
	overflow: auto;
}

#session-aside-right .chat{
	height: 60%;
	display: flex;
	flex-direction: column;
}
#session-aside-right .chat .chat_content{
	width: 100%;
	height: 90%;
	background-color: #ccc;
}

#session-aside-right .chat .chat_input{
	width: 100%;
	flex-grow: 1;
	border: 1px solid #ccc;
	padding: 5px;
	border-radius: 5px;
	background: transparent;
	padding: 0 30px;
	font-size: 16px;
}

#session-aside-right .chat input:focus{
	outline: none;
}

.footerBtn{
	/* margin: 0 10px */
	width: 100%;
}

/* 채팅방 좌측 사이드 메뉴바 */
.sideMenuImg {
	width: 30px;
	height: 30px;
}

/* 채팅방 하단 메뉴바 */
#session-footer{
/* width: 40%; */
width: 520px;
height: 50px;
line-height: 50px;
position: absolute;
bottom: 0px;
left: 15%;
border-radius: 10px;
background-color: #F0F0F0;
margin-bottom: 15px;
/* transform: translate(-50%,0%); */
/* background-color: #ccc; */
}

.buttomMenuIcon {
	width: 15px;
	height: 15px;
	margin-right: 5px;
}

.buttomMenu {
	margin: 0px 10px;
}


/* 채팅창 */
.messages {
	flex-grow: 1;
	padding: 10px 20px;
	overflow: auto;
	height: auto;
	background-color: #ccc;
	font-size: 15px;
}

.messageLoop {
	margin-bottom: 10px;
}

.messages::-webkit-scrollbar {
	width: 5px;
	height: 1px;
}

.messages::-webkit-scrollbar-track {
	background-color: rgb(255, 255, 255);
}

.messages::-webkit-scrollbar-thumb {
	background-color: rgb(126, 125, 125);
}

.messages::-webkit-scrollbar-button {
	display: none;
}

.message__bubble {
	margin: 5px;
	text-align: left;
}
.chatFooter {
	height: 50px;
	line-height: 30px;
	border-top: 1px solid rgba(156, 172, 172, 0.2);
	display: flex;
	flex-shrink: 0;
}

#submitBtn {
	height: 50px;
	border: none;
	background: transparent;
	padding: 0 30px;
	font-size: 16px;
	cursor: pointer;
}

#submitBtn:hover {
	background-color: #6363bf;
	color: #FFF;
}

</style>
<script scoped>
import "@/assets/style/style.css";
import axios from 'axios';
import http from "@/util/http-common.js";
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '../components/openvidu/UserVideo';
import UserList from '../components/openvidu/UserList';
import jwt_decode from "jwt-decode";

import { mapState } from "vuex";

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = "https://i6e103.p.ssafy.io:8084";
const OPENVIDU_SERVER_SECRET = "ssafy1234";

export default {
	name: 'App',

	components: {
		UserVideo,
		UserList,
	},
	metaInfo: {
		// title 입력하기
		title: '페이지 타이틀',
		// link tag 입력하기
		link: [{rel: "stylesheet", href: 'https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'}],
		// meta tag 입력하기
		meta: [
		{ charset: 'utf-8' },
		{ name: 'viewport', content: 'width=device-width, initial-scale=1' },
		{ vmid: "description", name: "description", content: 'description' }
		//vmid ↑ 메타 태그를 고유하게 만들어준다.
		]
  },
	data () {
		return {
			// 화면 공유
			OVForScreenShare: undefined,
			sessionForScreenShare: undefined,
			sharingPublisher: undefined,
			sharing:true,
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			mySessionId: 'SessionA',
			myUserName: 'Participant' + Math.floor(Math.random() * 100),
			audioEnabled: true,
			videoEnabled: true,
			audio: true,
			video: true,
			audioMsg: '마이크 ON',
			videoMsg: '비디오 ON',
			messages: [],
			userId: '',

			// 상벌점 기능
			scoreModal: false,
		}
	},
	computed:{
	...mapState(["roomName", "roomUrl", "participant", "roomStudyNo", "studyMembers"]),
	},
	created(){
		this.mySessionId = this.roomUrl;
		this.myUserName = this.participant;
		this.joinSession();
		
		// 텍스트 채팅에서 사용하기위한 유저 아이디(임시)
		this.userId = jwt_decode(localStorage.getItem("jwt")).sub;
		console.log(">>>>>>>>>>>>>>>>>>>> userId : ", this.userId);

		// 상벌점 위한 스터디멤버 불러오기
		this.getStudyMembers()
	},
	methods: {
		getToken_info(){
      const token = localStorage.getItem('jwt')
      const header = {
				Authorization: `Bearer ${token}`
			}
      return header
    },
		// 상벌점 기능 관련 methods
		getStudyMembers() {
      this.$store.dispatch('getStudyMembers', this.$store.state.roomStudyNo)
    },
		updateScore(score, studymember_no) {
			const updateInfo = {
				authority: this.$store.state.power.authority,
				leader: this.$store.state.power.leader,
				score: score,
				studymember_no: studymember_no
			}
			http({
				method: 'PUT',
				url: '/studymember/updatescore',
				data: updateInfo
			})
			.then(() => {
				// console.log(res)
				this.getStudyMembers()
			})
			.catch(err => {
				console.log(err)
			})
		},
		plusScore(score, studymember_no) {
			score += 1
			this.updateScore(score, studymember_no)
		},
		minusScore(score, studymember_no) {
			score -= 1
			this.updateScore(score, studymember_no)
		},
		
		
		joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();

			// --- Init a session ---
			this.session = this.OV.initSession();
		
			// --- Specify the actions when events take place in the session ---

			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			});

			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});

			// 같은 session 내에서 텍스트 채팅을 위한 signal
			this.session.on('signal', (event) => {
				var message = event.data.split(".././././.");
				this.messages.push(message[0]);
			});
		
			// --- Connect to the session with a valid user token ---

			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {

						// --- Get your own camera stream with the desired properties ---
						// console.log("Dasdasdasdasdasdasdasdasdasdasdasdasdqwrqwrqw");
						// console.log(this.session)
						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
							resolution: '640x480',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false,       	// Whether to mirror your local video or not
						});

						this.mainStreamManager = publisher;
						this.publisher = publisher;

						// --- Publish your stream ---

						this.session.publish(this.publisher);
						console.log("Dasdasdasdasdasdasdasdasdasdasdasdasdqwrqwrqw");
						console.log(this.session)
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});

			window.addEventListener('beforeunload', this.leaveSession)
		},

		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.session.disconnect();

			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;
			this.OVForScreenShare = undefined;
			this.sharingPublisher = undefined;
			window.removeEventListener('beforeunload', this.leaveSession);
			http({
				method: 'DELETE',
				url: `/privateroom/removePrivateMember`,
				headers: this.getToken_info(),
				params: {privatestudyroom_id: this.mySessionId},
			})
			.then(() => {
				this.$router.push({name:'StudyDetail', params:{studyNo: this.roomStudyNo}})
			})
			.catch(err => {
				console.log(err)
			});
			
			this.sharing = !this.sharing;
			if (this.sessionForScreenShare) this.sessionForScreenShare.disconnect();
            this.sessionForScreenShare = undefined;
            this.mainStreamManager = undefined;
            this.sharingPublisher = undefined;
            this.OVForScreenShare = undefined;
            window.removeEventListener('beforeunload', this.leaveSessionForScreenSharing);		
			  	
		},

		// 텍스트 채팅을 위한 메세지 전송하기
		sendMessage() {
			var message = document.getElementById("msg").value;
			message.replace('\n','<br/>');
			console.log(message)
			
			
			this.session.signal({
				data: this.myUserName+ "님의 메시지:\n" +message,
				to: [],
				type: 'my-chat',
			})
			.then(() => {
				console.log("message sent successfully!!");
				document.getElementById("msg").value = "";
			})
			.catch(error => {
				console.error(error);
			})
		},

		muteVideo() {
			this.videoEnabled = !this.videoEnabled;
			this.video = !this.video;
			if(this.video == true) this.videoMsg = "비디오 OFF";
			else this.videoMsg = "비디오 ON";
			// if(this.video == '비디오 ON') this.video = '비디오 OFF';
			// else this.video = '비디오 ON';
			this.publisher.publishVideo(this.videoEnabled);
		},

		muteAudio() {
			this.audioEnabled = !this.audioEnabled;
			this.audio = !this.audio;
			if(this.audio == true) this.audioMsg = "마이크 OFF";
			else this.audioMsg = "마이크 ON";
			// if(this.audioMsg == '마이크 ON') this.audioMsg = '마이크 OFF';
			// else this.audioMsg = '마이크 ON';
			// if(this.mic == 'mic-fill') this.mic = 'mic-mute-fill';
			// else this.mic = 'mic-fill';
			this.publisher.publishAudio(this.audioEnabled);
		},
		
		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

		/**
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
		 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
		 *   3) The Connection.token must be consumed in Session.connect() method
		 */

		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},	
		// 화면 공유 부분
		startScreenSharing () {
			this.OVForScreenShare = new OpenVidu();
			this.sessionForScreenShare = this.OVForScreenShare.initSession();
			var mySessionId = this.mySessionId;
			this.getToken(mySessionId).then(token => {
				this.sessionForScreenShare.connect(token, { clientData: this.screenShareName })
				.then(() => {
					let publisher = this.OVForScreenShare.initPublisher("sharingvideo", {
						audioSource: false,
						videoSource: "screen",      
                        publishVideo: true,  
						resolution: "1920x1980",
						frameRate: 10,           
                        insertMode: 'APPEND',    
                        mirror: false        
					});
					console.log("publisher",publisher);
					publisher.once('accessAllowed', () => {
						try {
							console.log("subscriber >>>>> ", this.subscribers);
							this.isScreenShared=true;
							this.session.signal({
								data: JSON.stringify(status),  // Any string (optional)
								to: [],
								type: 'startScreenSharing'             // The type of message (optional)
							})
							this.sharing = !this.sharing; // 화면 공유 버튼에서 중지 버튼으로 change toggle
							publisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
								console.log('User pressed the "Stop sharing" button');
								this.session.signal({
									data: JSON.stringify(status),  // Any string (optional)
									to: [],
									type: 'stopScreenSharing'             // The type of message (optional)
								})
								this.leaveSessionForScreenSharing()
								this.isScreenShared=false;
							});					
						} catch (error) {
							console.error('Error applying constraints: ', error);
						}
					});
					publisher.once('accessDenied', () => { 
						console.warn('ScreenShare: Access Denied');
					});
					this.mainStreamManager = publisher;
                    this.sharingPublisher = publisher;
                    this.sessionForScreenShare.publish(this.sharingPublisher);
				}).catch((error => {
					console.warn('There was an error connecting to the session:', error.code, error.message);
				}));
			});
			window.addEventListener('beforeunload', this.leaveSessionForScreenSharing)
		},
		leaveSessionForScreenSharing () { // 화면 공유 중지
			this.sharing = !this.sharing;
			if (this.sessionForScreenShare) this.sessionForScreenShare.disconnect();
            this.sessionForScreenShare = undefined;
            this.mainStreamManager = undefined;
            this.sharingPublisher = undefined;
            this.OVForScreenShare = undefined;
            window.removeEventListener('beforeunload', this.leaveSessionForScreenSharing);
		},
	},
	stopScreenShare(){
		
		this.sharing = !this.sharing;
		window.removeEventListener('beforeunload', this.leaveSessionForScreenSharing);
	},
	watch: {
		messages() {
			this.$nextTick(() => {
        let msg = this.$refs.messages;

        msg.scrollTo({ top: msg.scrollHeight, behavior: 'smooth' });
      });
		},
	},
}
</script>
