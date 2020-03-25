export const validAreas = ['North America', 'South America', 'Asia', 'Africa', 'Europe'];
export const validSchools = ['Rice University', 'Harvard University', 'Duke University', 'Peking University', 'UESTC'];

export const WS_CONNECT = 'WS_CONNECT';
export const wsConnect = host => ({ type: WS_CONNECT, host });
export const WS_DISCONNECT = 'WS_DISCONNECT';
export const wsDisconnect = host => ({ type: WS_DISCONNECT, host });


export const SELECT_CHAT_USER = 'SELECT_CHAT_USER';
export const selectChatUser = (username) => ({
  type: SELECT_CHAT_USER,
  username
});

export const SELECT_CHAT_ROOM = 'SELECT_CHAT_ROOM';
export const selectChatRoom = (name) => ({
  type: SELECT_CHAT_ROOM,
  name
});

export const EXIT_CHAT_ROOM = 'EXIT_CHAT_ROOM';
export const exitChatRoom = (name) => ({
  type: EXIT_CHAT_ROOM,
  name
});

export const JOIN_CHAT_ROOM = 'JOIN_CHAT_ROOM';
export const joinChatRoom = (name) => ({
  type: JOIN_CHAT_ROOM,
  name
});

export const SET_SHOW_SENT_TOAST = 'SET_SHOW_SENT_TOAST';
export const setShowSentToast = (val) => ({
  type: SET_SHOW_SENT_TOAST,
  val
})


export const RECEIVE_USER_IN_USE = 'RECEIVE_USER_IN_USE';
export const RECEIVE_NO_USER = 'RECEIVE_NO_USER';
export const RECEIVE_INIT = 'RECEIVE_INIT';
export const RECEIVE_SEND_MSG = 'RECEIVE_SEND_MSG';
export const RECEIVE_ADD_ROOM = 'RECEIVE_ADD_ROOM';
export const RECEIVE_REMOVE_ROOM = 'RECEIVE_REMOVE_ROOM';
export const RECEIVE_UPDATE_ROOM_USERS = 'RECEIVE_UPDATE_ROOM_USERS';
export const RECEIVE_JOIN_ROOM = 'RECEIVE_JOIN_ROOM';
export const RECEIVE_EDIT_ROOM = 'RECEIVE_EDIT_ROOM';

export const WS_SEND_MESSAGE = 'WS_SEND_MESSAGE';

export const sendCheckUser = username => {
  const req = {
    type: 'CHECK_USER',
    username
  };
  return {
    type: WS_SEND_MESSAGE,
    msg: req
  };
};

export const sendRegisterUser = user => {
  const req = {
    type: 'REGISTER_USER',
    user
  };
  return {
    type: WS_SEND_MESSAGE,
    msg: req
  };
};

export const sendJoinRoom = roomname => {
  const req = {
    type: 'JOIN_ROOM',
    roomname
  };
  return {
    type: WS_SEND_MESSAGE,
    msg: req
  };
};

export const sendExitRoom = roomname => {
  const req = { 
    type: 'EXIT_ROOM',
    roomname
  };
  return {
    type: WS_SEND_MESSAGE,
    msg: req
  };
};

export const sendSendMsg = (roomname, msg) => {
  const req = {
    type: 'SEND_MSG',
    roomname,
    msg: Object.assign({}, {
      from: null,
      to: null,
      toAll: false,
      isSysMsg: false,
      timestamp: null,
      text: null
    }, msg)
  };
  return {
    type: WS_SEND_MESSAGE,
    msg: req
  };
};

export const sendUpdateRoom = (roomname, info) => {
  const req = {
    type: 'UPDATE_ROOM',
    roomname,
    ...info
  };
  return {
    type: WS_SEND_MESSAGE,
    msg: req
  };
};

export const sendCreateRoom = room => {
  const req = {
    type: 'CREATE_ROOM',
    room
  };
  return {
    type: WS_SEND_MESSAGE,
    msg: req
  };
};

export const sendHeartbeat = () => {
  return {
    type: WS_SEND_MESSAGE,
    msg: {
      type: 'HEARTBEAT',
      time: Date.now()
    }
  };
};

