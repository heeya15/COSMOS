<template>
	<div id="main">
		<div id="main-container" class="d-flex">
			<p>
				<img v-if="!asideRight" src="@/assets/img/openvidu/menu.png" class="rightMenuImg" alt="menu" @click="asideRight=true">
				<img v-else src="@/assets/img/openvidu/close.png" class="rightMenuImg" alt="menu" @click="asideRight=false">
			</p>
			<div id="session-aside-left" v-if="session">
				<p><img src="@/assets/img/openvidu/asideimg01.png" class="sideMenuImg" alt="settings" @click="outMemberModal=true"></p>

				<!-- 강퇴기능 모달 -->
				<div v-if="outMemberModal" class="black-bg">
					<div class="white-bg">
						<h2>멤버</h2>
						<hr>						
						<table class="table table-bordered table-hover align-middle">
							<thead class="table-danger">
								<tr>
									<th>이름(ID)</th>
									<th>강퇴</th>
								</tr>
							</thead>
							<tbody v-for="member in publicStudyMembers" :key="member.id">
								<tr>
								<td>{{member.user.userName}}({{member.user.userId}})</td>
								<td><b-button v-if="member.user.userId!==userId && isLeader" variant="danger" @click="outMember(member.user.userId, member.id)">강퇴</b-button></td>
								<!-- <td><b-button v-if="member.user.userId!==userId && isLeader" variant="danger" @click="outMember(member.id)">강퇴</b-button></td> -->
								</tr>
							</tbody>
						</table>
						<div class="d-flex justify-content-end">
							<button @click="outMemberModal=false" class="btn btn-secondary">닫기</button>
						</div>
					</div>
				</div>

			</div>
			<div id="session-center">
				<div id="session" v-if="session">
					<div id="session-header" class="d-flex">
						<h1 id="session-title">{{ this.roomName }}</h1> <!-- 방 제목 -->
					</div>
					
					<div>
						<!-- <div id="main-video" class="col-md-6">
							<user-video :stream-manager="mainStreamManager"/>
						</div> -->
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

							<!-- 나가기 버튼 설정 -->
							<div class="buttomMenu">
								<button class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" @click="leaveSession">
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
					<div class="right_label">
						<span>참가자</span>
					</div>
					<div class="participant_list"> <!-- 참가자 리스트 화면 -->
						<user-list :stream-manager="publisher"/>
						<user-list v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub"/>
					</div>
				</div>

				<!-- 채팅 기능 시작 -->
				<!-- <p @click="showChat">채팅</p> -->
					<!-- <div class="chat" v-show="isChatVisible"> -->
				<div class="user_chat">
					<div class="right_label">
						<span>채팅</span>
					</div>
					<div class="chat">
						<div class="messages" v-html="messages" ref="messages">
							<!-- <div class="messageLoop" v-for="(message, idx) in messages" :key="idx"> -->
								<!-- <div class="text-left" >{{ userId }} 님의 메시지:</div> -->
								<!-- <div class="text-left message__bubble">{{ message }}</div> -->
							<!-- </div> -->
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
import "@/assets/style/PrivateStudyRoom/room.css"
import axios from 'axios';
import http from "@/util/http-common.js";
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/openvidu/PublicUserVideo';
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
			// OVForScreenShare: undefined,
			// sessionForScreenShare: undefined,
			sharingPublisher: undefined,
			sharing:true,
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			mySessionId: null,
			myUserName: null,
			audioEnabled: true,
			videoEnabled: true,
			audio: true,
			video: true,
			audioMsg: '마이크 ON',
			videoMsg: '비디오 ON',
			messages: '',
			userId: '',
			isChatVisible: false,


			// 타이머
			timer: null,
			time: 0,

			// 권한 여부
			userAuthority: false,
			
			// 공개스터디 참가자 목록
			publicStudyMembers: [],

			// 강퇴관련
			outMemberModal: false,
			isLeader: null,

			// 오른쪽 사이드 메뉴
			asideRight: false,
		}
	},
	computed:{
		...mapState(["roomName", "roomUrl", "participant", "roomStudyNo"]),
	},
	created(){
		// 권한 여부 확인
		http({
            method: 'GET',
            url: `/user/leader`,
            headers: this.getUserToken(),
			params: {study_no: this.roomStudyNo},
			// params: {study_no: 25},
          })
        .then((res) => {
            this.userAuthority = res.data.authority;
			// console.log("😉😉")
			// console.log(res);
          })
          .catch(err => {
            console.log(err)
		});  

		this.mySessionId = this.roomUrl;
		this.myUserName = this.participant;
		this.joinSession();
		
		console.log("😀😀😀😀😀")
		console.log(this.myUserName)

		// 텍스트 채팅에서 사용하기위한 유저 아이디(임시)
		this.userId = jwt_decode(localStorage.getItem("jwt")).sub;
		console.log(">>>>>>>>>>>>>>>>>>>> userId : ", this.userId);

		// 강퇴기능위해 공개스터디멤버 불러오기
		this.getPublicStudyMembers(this.roomUrl)
		console.log(this.roomUrl)
		this.getLeave()

		
	},
	methods: {
		getUserToken(){
			const token = localStorage.getItem('jwt')
			const header = {
				Authorization: `Bearer ${token}`
			}
			return header
		},

		getToken_info(){
			const token = localStorage.getItem('jwt')
			const header = {
				Authorization: `Bearer ${token}`
			}
      return header
		},

		// 공개스터디 멤버 불러오기
		getPublicStudyMembers(publicstudyroomid) {
      http({
        method: 'GET',
        url: '/publicroom/search/publicMember',
        params: { publicstudyroom_id: publicstudyroomid }
      })
      .then(res => {
        console.log(res)
				this.publicStudyMembers = res.data
				this.publicStudyMembers.forEach(element => {
					if (this.userId === element.user.userId){
						if (element.leader){
							this.isLeader = true
						}
					}
				})
      })
      .catch(err => {
        console.log(err)
      })
		},
		// 멤버 강퇴하기(user_id,publicstudyroom_id)
		// outMember(memberId, idx) {
		outMember(idx) {
			// http({
			// 	method: 'DELETE',
			// 	url:'publicroom/remove/publicMember',
			// 	params: {user_id: memberId, publicstudyroom_id: this.roomUrl}
			// })
			// .then(res => {
			// 	console.log(res)
			// 	const data = {
			// 		leave: true,
			// 	};
			// 	this.publisher.session.signal({
      //   data: JSON.stringify(data),
      //   to: [this.this.subscribers[idx].stream.connection],
      //   type: "kick",
      // });
			// })
			// .catch(err => {
			// 	console.log(err)
			// })
			const data = {
					leave: true,
				};
				this.publisher.session.signal({
        data: JSON.stringify(data),
        to: [this.this.subscribers[idx].stream.connection],
        type: "kick",
				})
    },
    getLeave() {
      this.session.on("signal:kick", () => {
        this.leaveSession();
      });
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
				this.getPublicStudyMembers()
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
				console.log(">>>>>>>>>>>>>> message : ", message);

				if(message == "") {
					this.messages += '';
				} else {
					// console.log(">>>>>>>>>>>>>>>>>>> ", message[0]);
					// this.messages.push(message[0]);
					console.log("저장된 : ", this.$store.state.userId, "현재 : ", message[0]);
					if(this.$store.state.userId == message[0]) {
						console.log("내가 쓴 메시지");
						this.messages += '<div align="right">' 
										+ 	'<div style="padding: 10px; margin-bottom: 10px; width: 60%; background-color: #fff; border-radius: 10px;  word-wrap: break-word;"">'
										+  		'<div style="font-weight: 900;">' + message[0] + ' 님의 메시지: </div>'
										+  		'<div class="mb-3">' + message[1] + ' </div>'
										+  	'</div>'
										+ '</div>';
					} else {
						console.log('니가 쓴 메시지');
						this.messages += '<div align="left">' 
										+ 	'<div style="padding: 10px; margin-bottom: 10px; width: 60%; background-color: #6363bf; color: #fff; border-radius: 10px;  word-wrap: break-word;"">'
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
			
			// 타이머 receive
			// this.session.on('signal:study-timer', (event) => {				
			// 	this.time = Number(event.data);
			// })

			window.addEventListener('beforeunload', this.leaveSession)
		},
		async removePublicRoom(){
			await http({
				method: 'DELETE',
				url: '/publicroom/remove/publicRoom',				
				params: {publicstudyroom_id: this.mySessionId},
			})
			.then(() => {
				
			})
			.catch(err => {
				console.log(err)
			});
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
				url: '/publicroom/remove/publicMember',
				headers: this.getToken_info(),
				params: {user_id: this.userId, publicstudyroom_id: this.mySessionId},
			})
			.then(() => {
				this.removePublicRoom()
				this.$router.push({name:'MainPage'})
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
			if(message != "") {
				console.log("message " , message)
				
				
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
