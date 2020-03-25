let user: {
    username: string,
    age: number,
    school: string,
    area: string
};

let msg: {
    from: string,
    to: string,
    toAll: boolean,
    text: string,
    isSysMsg: boolean,
    timestamp: number
};

let room : {
    owner: string,
    roomname: string,
    ageMin: number,
    ageMax: number,
    areas: string[],
    schools: string[],
    users: string[],
    msgs: typeof msg[]
};

let checkUserRequest: {
    type: 'CHECK_USER',
    username: string,
};

let registerUserRequest: {
    type: 'REGISTER_USER',
    user: typeof user,
};

let joinRoomRequest: {
    type: 'JOIN_ROOM',
    roomname: string
};

let exitRoomRequest: {
    type: 'EXIT_ROOM',
    roomname: string
};

let sendMsgRequest: {
    type: 'SEND_MSG'
    roomname: string,
    msg: typeof msg
};

let updateRoomRequest: {
    type: 'UPDATE_ROOM',
    roomname: string,
    ageMin: number,
    ageMax: number,
    areas: string[],
    schools: string[]
};

let createRoomRequest: {
    type: 'CREATE_ROOM',
    room: typeof room
};

let userInUseResponse: {
    type: 'USER_IN_USE',
    username: string
};

let noUserResponse: {
    type: 'NO_USER',
    username: string,
};

let userExistResponse: {
    type: 'USER_EXIST';
    username: string
};

let initResponse : {
    type: 'INIT',
    user: typeof user,
    rooms: typeof room[],
};

let sendMsgResponse: {
    type: 'SEND_MSG',
    roomname: string,
    msg: typeof msg
};

let addRoomResponse: {
    type: 'ADD_ROOM',
    room: typeof room
};

let removeRoomResponse: {
    type: 'REMOVE_ROOM',
    roomname: string
};

let updateRoomUsersResponse: {
    type: 'UPDATE_ROOM',
    roomname: string,
    owner: string,
    users: string[]
};

let joinRoomResponse: {
    type: 'JOIN_ROOM',
    room: typeof room
};

let editRoomResponse: {
    type: 'EDIT_ROOM',
    roomname: string,
    ageMin: number,
    ageMax: number,
    areas: string[],
    schools: string[]
}