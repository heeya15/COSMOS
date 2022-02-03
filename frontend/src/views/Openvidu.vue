<template>
	<div id="main">
		<div id="main-container" class="container">
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
							<button class="btn btn-lg btn-success" @click="joinSession()">Join!</button>
						</p>
					</div>
				</div>
			</div>
			<div id="session-aside-left" v-if="session">
				<p><img src="../assets/img/openvidu/asideimg01.png" alt=""></p>
				<p><img src="../assets/img/openvidu/asideimg02.png" alt=""></p>
				<p><img src="../assets/img/openvidu/asideimg03.png" alt=""></p>
			</div>
			<div id="session-aside-right" v-if="session">
				<div class="participant">
					<p>참가자</p>
					<p class="participant_list"></p>
				</div>
				<div class="chat">
					<p>채팅</p>
					<p class="chat_content"></p>
					<input class="chat_input" type="text" placeholder="메세지를 입력하세요.">
				</div>
			</div>
			<div id="session" v-if="session">
				<div id="session-header">
					<h1 id="session-title">{{ mySessionId }}</h1> <!-- 방 제목 -->
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
					<div>
						<img src="../assets/img/openvidu/micimg.png" alt="">
						<input class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" value="마이크 켜기/끄기"> <!-- 마이크 on/off 버튼 -->
					</div>
					<div>
						<img src="../assets/img/openvidu/videoimg.png" alt="">
						<input class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" value="비디오 켜기/끄기"> <!-- 비디오 on/off 버튼 -->
					</div>
					<div>
						<img src="../assets/img/openvidu/exitimg.png" alt="">
						<input class="btn btn-large btn-default footerBtn" type="button" id="buttonLeaveSession" @click="leaveSession" value="나가기"> <!-- 나가기 버튼 -->
					</div>
				</div>
			</div>
		</div> <!-- #container -->
		
	</div>
</template>
<style>
#main{
	height: 100%;
}
#main-container{
position: relative;
height: 80%;
}
#session {
	/* position: relative; */
height: 50%;
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
	height: 45%;
	margin-bottom: 20px;
}
#session-aside-right .participant .participant_list{
	width: 100%;
	height: 90%;
	background-color: #ccc;
}


#session-aside-right .chat{
	height: 40%;
}
#session-aside-right .chat .chat_content{
	width: 100%;
	height: 90%;
	background-color: #ccc;
}

#session-aside-right .chat .chat_input{
	width: 100%;
	border: 1px solid #ccc;
	padding: 5px;
	border-radius: 5px;
}
#session-aside-right .chat input:focus{
	outline: none;
}



#session-footer{
width: 40%;
position: absolute;
bottom: 0px;
left: 15%;
/* transform: translate(-50%,0%); */
/* background-color: #ccc; */
}


#session-footer img{
width: 25px;
height: 25px;
}

.footerBtn{
	margin: 0 10px
}

</style>
<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '../components/openvidu/UserVideo';

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
	name: 'App',

	components: {
		UserVideo,
	},

	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],

			mySessionId: 'SessionA',
			myUserName: 'Participant' + Math.floor(Math.random() * 100),
		}
	},

	methods: {
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
							publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
							resolution: '640x480',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false       	// Whether to mirror your local video or not
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

			window.removeEventListener('beforeunload', this.leaveSession);
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
	}
}
</script>
