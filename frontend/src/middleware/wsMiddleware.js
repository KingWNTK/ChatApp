import * as act from '../actions/actions';

const socketMiddleware = () => {
  let socket = null;

  const onOpen = store => (msg) => {
    console.log('websocket open', msg.target.url);
    // store.dispatch(actions.wsConnected(event.target.url));
  };

  const onClose = store => () => {
    // store.dispatch(actions.wsDisconnected());
  };

  const onMessage = store => (msg) => {
    let obj = JSON.parse(msg.data);

    if(obj.type) {
      obj.type = 'RECEIVE_' + obj.type;
      console.log(obj);
      store.dispatch(obj);
    }
  };

  // the middleware part of this function
  return store => next => action => {
    switch (action.type) {
      case act.WS_CONNECT:
        if (socket !== null) {
          socket.close();
        }
        // connect to the remote host
        socket = new WebSocket(action.host);

        // websocket handlers
        socket.onmessage = onMessage(store);
        socket.onclose = onClose(store);
        socket.onopen = onOpen(store);
        break;
      case act.WS_DISCONNECT:
        if (socket !== null) {
          socket.close();
        }
        socket = null;
        console.log('websocket closed');
        break;
      case act.WS_SEND_MESSAGE:
        console.log('sending a message', action.msg);
        socket.send(JSON.stringify({...action.msg}));
        break;
      default:
        // console.log('the next action:', action);
        return next(action);
    }
  };
};

export default socketMiddleware();