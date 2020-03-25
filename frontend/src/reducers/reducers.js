import * as act from '../actions/actions';

const initialState = {
  verified: false,
  needRegister: false,
  showSentToast: false,
  self: {
  },
  selectedUser: {
    toAll: true
  },
  selectedRoom: null,
  myRooms: [
  ],
  availableRooms: [
  ]
};

const chatApp = (state = initialState, action) => {
  try {
    switch (action.type) {
      case act.SELECT_CHAT_USER: {
        if (!state.selectedRoom || state.self.username === action.username) {
          return state;
        }
        if (state.self.username === state.selectedRoom.owner) {
          if (state.selectedUser.username === action.username) {
            return Object.assign({}, state, { selectedUser: { toAll: true } });
          }
        }
        return Object.assign({}, state,
          {
            selectedUser: {
              username: state.selectedRoom.users.find(username => username === action.username)
            }
          });
      }
      case act.SELECT_CHAT_ROOM: {
        let t = {};
        t.selectedRoom = state.myRooms.find(room => room.roomname === action.name);

        if (t.selectedRoom) {
          t.selectedUser =
            state.self.username === t.selectedRoom.owner ?
              ({ toAll: true }) :
              ({ username: t.selectedRoom.owner });
        }
        return Object.assign({}, state, t);
      }
      case act.EXIT_CHAT_ROOM: {
        let room = state.myRooms.find(r => r.roomname === action.name);
        if (!room) return state;
        if (state.selectedRoom === room) state.selectedRoom = null;
        state.myRooms = state.myRooms.filter(r => r !== room);
        state.availableRooms = [...state.availableRooms];
        state.availableRooms.push(room);
        return Object.assign({}, state);
      }
      case act.JOIN_CHAT_ROOM: {
        let room = state.availableRooms.find(r => r.roomname === action.name);
        if (!room) return state;
        state.selectedRoom = room;
        state.availableRooms = state.availableRooms.filter(r => r !== room);
        state.myRooms = [...state.myRooms];
        state.myRooms.push(room);
        return Object.assign({}, state);
      }
      case act.SET_SHOW_SENT_TOAST: {
        return Object.assign({}, state, { showSentToast: action.val });
      }

      case act.RECEIVE_NO_USER: {
        return Object.assign({}, state, {
          verified: false,
          needRegister: true
        });
      }
      case act.RECEIVE_USER_IN_USE: {
        return Object.assign({}, state, {
          verified: false,
          needRegister: false
        });
      }
      case act.RECEIVE_INIT: {
        return Object.assign({}, state, {
          verified: true,
          needRegister: false,
          self: action.user,
          myRooms: action.rooms.filter(room => room.users.includes(action.username)),
          availableRooms: action.rooms.filter(room => !room.users.includes(action.username))
        });
      }
      case act.RECEIVE_SEND_MSG: {
        let room = state.myRooms.find(room => room.roomname === action.roomname);
        if (!room) return state;
        room.msgs = [...room.msgs];
        room.msgs.push(action.msg);
        if(!action.msg.isSysMsg && action.msg.from === state.self.username) {
          state.showSentToast = true;
        }
        return Object.assign({}, state);
      }
      case act.RECEIVE_ADD_ROOM: {
        if (action.room.users.includes(state.self.username)) {
          state.myRooms = [...state.myRooms];
          state.myRooms.push(action.room);
        }
        else {
          state.availableRooms = [...state.availableRooms];
          state.availableRooms.push(action.room);
        }

        return Object.assign({}, state);
      }
      case act.RECEIVE_REMOVE_ROOM: {
        if (state.selectedRoom) {
          if (state.selectedRoom.roomname === action.roomname) {
            state.selectedRoom = null;
          }
        }
        state.myRooms = state.myRooms.filter(room => room.roomname !== action.roomname);
        state.availableRooms = state.availableRooms.filter(room => room.roomname !== action.roomname);
        return Object.assign({}, state);
      }
      case act.RECEIVE_UPDATE_ROOM_USERS: {
        let room = state.myRooms.find(room => room.roomname === action.roomname);
        if (!room) return state;
        room.owner = action.owner;
        if (room.users.includes(state.self.username) && !action.users.includes(state.self.username)) {
          state.myRooms = state.myRooms.filter(r => r.roomname !== room.roomname);
          state.availableRooms = [...state.availableRooms];
          state.availableRooms.push(room);
          state.selectedRoom = null;
        }

        if (room === state.selectedRoom && action.users.includes(state.self.username)) {
          if (state.selectedUser.username && !action.users.includes(state.selectedUser.username)) {
            state.selectedUser =
              state.self.username === action.owner ?
                ({ toAll: true }) :
                ({ username: action.owner });
          }
        }

        room.users = action.users;
        state.myRooms = [...state.myRooms];
        return Object.assign({}, state);
      }
      case act.RECEIVE_JOIN_ROOM: {
        state.availableRooms = state.availableRooms.filter(room => room.roomname !== action.room.roomname);
        state.myRooms = [...state.myRooms];
        state.myRooms.push(action.room);

        let t = {};
        t.selectedRoom = action.room;

        if (t.selectedRoom) {
          t.selectedUser =
            state.self.username === t.selectedRoom.owner ?
              ({ toAll: true }) :
              ({ username: t.selectedRoom.owner });
        }
        return Object.assign({}, state, t);
      }
      case act.RECEIVE_EDIT_ROOM: {
        let room = state.myRooms.find(room => room.roomname === action.roomname);
        if (!room) return state;
        room.ageMin = action.ageMin;
        room.ageMax = action.ageMax;
        room.areas = action.areas;
        room.schools = action.schools;
        state.myRooms = [...state.myRooms];
        return Object.assign({}, state);
      }
      default: {
        return state;
      }
    }
  }
  catch (e) {
    console.log(e);
    return state;
  }
}

export default chatApp;