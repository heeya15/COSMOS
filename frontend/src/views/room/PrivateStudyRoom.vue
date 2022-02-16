<template>
	<div id="main">
		<div id="main-container" class="d-flex">
			<div id="session-aside-left" v-if="session">
				<p v-if="power.leader"><img src="https://i.ibb.co/x2JRqqX/asideimg02.png" class="sideMenuImg" alt="score" @click="scoreModal=true"></p>
				
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
									<th>점수</th>
								</tr>
							</thead>
							<tbody v-for="member in studyMembers" :key="member.id">
								<tr>
								<td>{{member.user_name}}</td>
								<td>{{member.user_email}}</td>
								<td style="width:75px;"><button class="score-btn1" @click="minusScore(member.score, member.studymember_no)">-</button><span class="ml-4">{{member.score}}</span><button class="score-btn2" @click="plusScore(member.score, member.studymember_no)">+</button></td>
								</tr>
							</tbody>
						</table>
						<div class="d-flex justify-content-end">
							<button @click="scoreModal=false" class="btn btn-secondary">닫기</button>
						</div>
					</div>
				</div>
			</div>
			
			<div id="session-center">
				<div id="session" v-if="session">
					<div id="session-header" class="d-flex">
						<h1 id="session-title">{{ this.roomName }}</h1> <!-- 방 제목 -->
						<div id="session-timer" class="text-center" style="margin-left: 30%;">
							<div>
								<h3 id="session-time"> {{ hours }} : {{ minutes }} : {{ seconds }} </h3>
							</div>
							<div id="timerBtn" v-if="power.authority">
								<b-button v-if="!timer" variant="primary" @click="startTimer()">시작</b-button>
								<b-button v-else variant="danger" @click="stopTimer">
									정지
								</b-button>
								<b-button v-if="resetButton" variant="success" @click="resetTimer">
									종료
								</b-button>
								<b-button v-if="!timer" variant="warning" @click="editTimer">
									시간 설정
								</b-button>
								<div v-if="edit" class="d-flex justify-content-center mt-1">
									<b-input class="p-3 text-center" type="text" v-model="inputHour" placeholder="시" style="width:40px"/>
									<b-input class="p-3 text-center" type="text" v-model="inputMin" placeholder="분" style="width:40px"/>
									<b-input class="p-3 text-center" type="text" v-model="inputSec" placeholder="초" style="width:40px"/>
								</div>
							</div>
						</div>
					</div>
					
					<div>
						<div id="main-video" style="width:60%; margin: 0 auto;">
							<user-video v-if="mainOnOff" :stream-manager="mainStreamManager"  :mainStream="true"  @click.native="deleteMainVideoStreamManager()"/>
						</div>
						<div id="video-container" class="d-flex flex-wrap row"> <!-- 참가자 화면 -->
							<user-video class="col-md-4" v-if="!isScreenShared" :stream-manager= "publisher" @click.native="updateMainVideoStreamManager(publisher)"/> <!--자기 -->
							<user-video class="col-md-4" v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/> <!-- 다른 참가자 -->
						</div>
					</div>
				</div>

				<div id="session-footer-wrap" class="d-flex justify-content-center">
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

							<!-- 채팅 버튼 -->
							<div v-if="!asideRight" class="buttomMenu">
								<b-button class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" @click="asideRight=true">
									<b-icon icon="chat-right-dots-fill" class="buttomMenuIcon" aria-hidden="true"></b-icon>
									<span class="footerBtnText">채팅보기</span>
								</b-button>
							</div>
							<div v-else class="buttomMenu">
								<b-button  class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" @click="asideRight=false">
									<b-icon icon="chat-right-dots" class="buttomMenuIcon" aria-hidden="true"></b-icon>
									<span class="footerBtnText">채팅닫기</span>
								</b-button>
							</div>

							<!-- 나가기 버튼 설정 -->
							<div class="buttomMenu">
								<button class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" @click="[leaveSession(), toggleAttendance()]">
									<b-icon icon="door-open-fill" class="buttomMenuIcon" aria-hidden="true"></b-icon>
									<span class="footerBtnText">나가기</span>
								</button> <!-- 나가기 버튼 -->
							</div>
						</div>	
					</div>
				</div> <!-- #session-footer-wrap -->
			</div> <!-- #session-center -->
			<div id="session-aside-right" v-if="session && asideRight">
				<div class="participant">
					<div class="right_label_participant">
						<span>참가자</span>
					</div>
					<div class="participant_list"> <!-- 참가자 리스트 화면 -->
						<user-list :stream-manager="publisher"/>
						<user-list v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub"/>
					</div>
				</div>

				<!-- 채팅 기능 시작 -->
				<div class="user_chat">
					<div class="right_label">
						<span>채팅</span>
					</div>
					<div class="chat">
						<div class="messages" v-html="messages" ref="messages">
						</div>

						<form class="chatFooter" onsubmit="return false">
							<input class="chat_input" id="msg" type="text" autocomplete="off" placeholder="메세지를 입력하세요.">
							<button id="submitBtn" type="submit" @click="sendMessage()">Enter</button>
						</form>
					</div>
				</div>
				<!-- 채팅 기능 끝 -->
			</div> <!-- session-right -->
		</div> <!-- #main-container -->
		
	</div>
</template>
<style scoped>

</style>
<script>
import "@/assets/style/style.css";
import "@/assets/style/StudyRoom/room.css"
import axios from 'axios';
import http from "@/util/http-common.js";
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/openvidu/PrivateUserVideo';
import UserList from '@/components/openvidu/UserList';
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
		title: 'COSMOS',
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
			spublisher:undefined,
			sminStreamManager: undefined,
			isScreenShared: false,
			mainOnOff: false,

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
			messages: '',
			userId: '',
			isChatVisible: false,

			// 상벌점 기능
			scoreModal: false,

			// 타이머
			timer: null,
			inputHour: null,
			inputMin: null,
			inputSec: null,
			time: 0,
			resetButton: false,
			edit: false,

			// 시간
			userhistoryNo :0,

			// 오른쪽 사이드 메뉴
			asideRight: false,
		}
	},
	computed:{
		...mapState(["roomName", "roomUrl", "participant", "roomStudyNo", "studyMembers", "audio", "video", "power"]),

		totalTime() {
			return Number(this.inputHour * 3600) + Number(this.inputMin * 60) + Number(this.inputSec)
		},
		hours(){
			const hours = Math.floor(this.time / 3600)
			return this.padTime(hours)
		},
		minutes() {
			const minutes = Math.floor((this.time - (this.hours * 3600)) / 60)
			return this.padTime(minutes)
		},
		seconds() {
			const seconds = this.time - ((this.hours * 3600) + (this.minutes * 60))
			return this.padTime(seconds)
		},
	},
	created(){
		// 초기 장치 셋팅
		this.audioEnabled =this.$store.state.audio,
		this.videoEnabled= this.$store.state.video;
		this.audio= this.$store.state.audio;
		this.video= this.$store.state.video;

		if(this.video == true) this.videoMsg = "비디오 OFF";
		else this.videoMsg = "비디오 ON";

		if(this.audio == true) this.audioMsg = "마이크 OFF";
		else this.audioMsg = "마이크 ON";

		this.mySessionId = this.roomUrl;
		this.myUserName = this.participant;
		this.joinSession();
		
		// 텍스트 채팅에서 사용하기위한 유저 아이디(임시)
		this.userId = jwt_decode(localStorage.getItem("jwt")).sub;
		console.log(">>>>>>>>>>>>>>>>>>>> userId : ", this.userId);

		// 상벌점 위한 스터디멤버 불러오기
		this.getStudyMembers()
	},
	mounted() {
    window.addEventListener('beforeunload', this.unLoadEvent);
  },
	beforeUnmount() {
		window.removeEventListener('beforeunload', this.unLoadEvent);
	},
	methods: {
		unLoadEvent: function (event) {
			if (this.canLeaveSite) return;

			event.preventDefault();
			event.returnValue = '';
		},
		getUserToken(){
			const token = localStorage.getItem('jwt')
			const header = {
				Authorization: `Bearer ${token}`
			}
			return header
		},
		startTimer() {
			if(!this.inputHour && !this.inputMin && !this.inputSec){
				alert("시간을 설정해주세요.")
			} else{
				this.timer = setInterval(() => this.countdown(), 1000)
				this.resetButton = true
				this.edit = false
			}
		},
		stopTimer: function() {
			clearInterval(this.timer)
			this.timer = null
			this.resetButton = true
		},
		resetTimer: function() {
			this.time = 0
			clearInterval(this.timer)
			this.timer = null
			this.resetButton = false
			this.inputHour = null
			this.inputMin = null
			this.inputSec = null
		},
		editTimer: function() {
			this.edit = !this.edit
		},
		padTime: function(time){
			return (time < 10 ? '0' : '') + time
		},
		countdown: function() {
			if(this.time>0){
				this.time--
			}else{
				this.resetTimer();
			}
		},
		sendTimer(){
			// 타이머 send
			this.session.signal({
				data: this.time,
				to: [],
				type: 'study-timer',
			})
			.then(() => {
				console.log("timer success");
				if(this.time==0){
					alert("시간이 종료되었습니다.")
				}
			})
			.catch(error => {
				console.error(error);
			})
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
			// 방에 들어온 시간 측정을 위해 rest 요청.
			http({
				method: 'POST',
				url: `/history/register/private/starttime`,
				headers: this.getUserToken(),
			})
			.then( res => {
				// console.log(res);
				this.userhistoryNo = res.data.userhistoryNo;
			})
			.catch(err => {
				console.log(err)
			});

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
			this.session.on('signal:my-chat', (event) => {
				var message = event.data.split("&$");
				// console.log(">>>>>>>>>>>>>> message : ", message);

				if(message == "") {
					this.messages += '';
				} else {
					// console.log(">>>>>>>>>>>>>>>>>>> ", message[0]);
					// this.messages.push(message[0]);
					// console.log("저장된 : ", this.$store.state.userId, "현재 : ", message[0]);
					if(this.$store.state.userId == message[0]) {
						// console.log("내가 쓴 메시지");
						this.messages += '<div align="right">' 
										+ 	'<div style="padding: 10px; margin-bottom: 10px; width: 60%; background-color: #fff; border-radius: 10px; word-wrap: break-word;">'
										+  		'<div style="font-weight: 900;">' + message[0] + ' 님의 메시지: </div>'
										+  		'<div class="mb-3" style="">' + message[1] + ' </div>'
										+  	'</div>'
										+ '</div>';
					} else {
						// console.log('니가 쓴 메시지');
						this.messages += '<div align="left">' 
										+ 	'<div style="padding: 10px; margin-bottom: 10px; width: 60%; background-color: #6363bf; color: #fff; border-radius: 10px; word-wrap: break-word;">'
										+  	'<div style="font-weight: 900;">' + message[0] + ' 님의 메시지: </div>'
										+  	'<div class="mb-3">' + message[1] + ' </div>'
										+  '</div>';
					}
				}
			});
		
			// --- Connect to the session with a valid user token ---

			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {

						// --- Get your own camera stream with the desired properties ---
						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: this.audio,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: this.video,  	// Whether you want to start publishing with your video enabled or not
							resolution: '640x480',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false,       	// Whether to mirror your local video or not
						});

						this.mainStreamManager = publisher;
						this.publisher = publisher;

						// --- Publish your stream ---

						this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});

			// 타이머 receive
			this.session.on('signal:study-timer', (event) => {				
				this.time = Number(event.data);
			})

			window.addEventListener('beforeunload', this.leaveSession)
		},

		// 방 나가기하면 출석원상복귀
    toggleAttendance() {
			const attendanceData = {
				studymember_no: null,
				attendance: null
			}
			this.studyMembers.forEach(member => {
				if (member.user_id === this.userId) {
					attendanceData.studymember_no = member.studymember_no
					attendanceData.attendance = member.attendance
				}
			})
      if (attendanceData.attendance === true) {
        attendanceData.attendance = false
      }
			console.log(attendanceData)
      http({
        method: 'PUT',
        url: 'studymember/update/attendance/',
        data: attendanceData
      })
      .then((res) => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
    },

		leaveSession () {
			// 방 떠나기전 현재까지 공부한 시간을 history에 누적하기 위해, 방 떠날 때 userhistory_no 보내줌.
			http({
				method: 'POST',
				url: `/history/register/private/finishtime`,
				headers: this.getUserToken(),
				params: {userhistory_no: this.userhistoryNo}
				})
			.then(() => {
				// console.log("현재 까지 공부하고 방 떠남")
				// console.log(res);
			})
			.catch(err => {
				console.log(err)
			})
			
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
				url: '/privateroom/remove/privateMember',
				headers: this.getUserToken(),
				params: {privatestudyroom_id: this.mySessionId},
			})
			.then(() => {
				this.sharing = !this.sharing;
				if (this.sessionForScreenShare) this.sessionForScreenShare.disconnect();
				this.sessionForScreenShare = undefined;	
        window.removeEventListener('beforeunload', this.leaveSessionForScreenSharing);		
				this.$router.push({name:'StudyDetail', params:{studyNo: this.roomStudyNo}})
			})
			.catch(err => {
				console.log(err)
			});	
		},

		// 텍스트 채팅을 위한 메세지 전송하기
		sendMessage() {
			var message = document.getElementById("msg").value;
			if(message != "") {
				// console.log("message " , message)
				this.session.signal({
					data: this.myUserName+ "&$" +message,
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
			}
		},

		muteVideo() {
			this.videoEnabled = !this.videoEnabled;
			this.video = !this.video;
			if(this.video == true) this.videoMsg = "비디오 OFF";
			else this.videoMsg = "비디오 ON";
			this.publisher.publishVideo(this.videoEnabled);
		},

		muteAudio() {
			this.audioEnabled = !this.audioEnabled;
			this.audio = !this.audio;
			if(this.audio == true) this.audioMsg = "마이크 OFF";
			else this.audioMsg = "마이크 ON";

			this.publisher.publishAudio(this.audioEnabled);
		},
		
		updateMainVideoStreamManager (stream) {
			this.mainOnOff = true;
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},
		deleteMainVideoStreamManager() { // 해당 화면 크게 한거 지우기.
			this.mainOnOff = false;
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
				this.sessionForScreenShare.connect(token, { clientData: this.myUserName  })
				.then(() => {
					this.spublisher = this.OVForScreenShare.initPublisher(undefined, {
						audioSource: false,
						videoSource: "screen",      
            publishVideo: true,  
						resolution: "1280x720",
						frameRate: 30,           
            insertMode: 'APPEND',    
            mirror: false        
					});
					// console.log("publisher",this.spublisher);
					this.spublisher.once('accessAllowed', () => {
						try {
							console.log("subscriber >>>>> ", this.subscribers);
							this.isScreenShared=true;
							this.session.unpublish(this.publisher); // 송출하고 있는거 중단 (안하면 에러) -- 세션을 없앤다는 뜻.
							
							this.mainStreamManager = undefined;							
							this.OV = undefined;								
							this.sharing = !this.sharing; // 화면 공유 버튼에서 중지 버튼으로 change toggle

							const constraints = {
								width: {min: 640, ideal: 1280},
								height: {min: 480, ideal: 720},
								advanced: [
									{width: 1920, height: 1280},
									{aspectRatio: 1.333}
								]
							};
							this.spublisher.stream.getMediaStream().getVideoTracks()[0].applyConstraints(constraints, () => {
							
							}),
							this.spublisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
								console.log('User pressed the "Stop sharing" button');
								this.leaveSessionForScreenSharing()
								this.isScreenShared=false;
								});					
						} catch (error) {
									console.error('Error applying constraints: ', error);
								}
					});
					this.spublisher.once('accessDenied', () => { 
						console.warn('ScreenShare: Access Denied');
					});
					this.mainStreamManager = this.spublisher;
					this.sharingPublisher =this.spublisher;
					this.sessionForScreenShare.publish(this.sharingPublisher);
					}).catch((error => {
						console.warn('There was an error connecting to the session:', error.code, error.message);
					}));
			});
			//window.addEventListener('beforeunload', this.leaveSessionForScreenSharing)
		},
		leaveSessionForScreenSharing () { // 화면 공유 중지
			this.sharing = !this.sharing; // 화면 공유 버튼에서 중지 버튼으로 change toggle
      this.isScreenShared=false;
		
			var mySessionId = this.mySessionId;
			console.log(mySessionId); // 제대로있고.
		  this.sessionForScreenShare.unpublish(this.spublisher); // 송출하고 있는거 중단 (안하면 에러)
			//  if (this.sessionForScreenShare) this.sessionForScreenShare.disconnect();
			this.sessionForScreenShare = undefined;
      this.smainStreamManager = undefined;
			this.sharingPublisher = undefined;
			this.spublisher = undefined;
      this.OVForScreenShare = undefined;
			
			this.session.publish(this.publisher).then(() => {  // 송출하기 
				this.publisher(this.publisher);
			});

			window.removeEventListener('beforeunload', this.leaveSessionForScreenSharing);
		
		},
	},
	stopScreenShare(){	
		this.sharing = !this.sharing;
		var mySessionId = this.mySessionId;
		console.log(mySessionId);
	
		window.removeEventListener('beforeunload', this.leaveSessionForScreenSharing);
	},
	watch: {
		messages() {
			this.$nextTick(() => {
				let msg = this.$refs.messages;

				msg.scrollTo({ top: msg.scrollHeight, behavior: 'smooth' });
			});
		},

		totalTime() {
			this.time = this.totalTime
    },
		time(){
			this.sendTimer();
		},
	},
}
</script>
