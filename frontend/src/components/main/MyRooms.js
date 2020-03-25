import React, { useState } from 'react';
import { connect } from 'react-redux';
import { Card, ListGroup, Button, Dropdown, ButtonGroup } from "react-bootstrap";

import { selectChatRoom, exitChatRoom, sendExitRoom } from '../../actions/actions';

import NewRoom from './NewRoom';
import UpdateRoom from './UpdateRoom';

let RoomListItem = ({ room, self, sendExitRoom, selectChatRoom }) => {
  const [showUpdateRoom, setShowUpdateRoom] = useState(false);

  return (
    <ListGroup.Item>
      {room.roomname}
      <Dropdown className='float-right' as={ButtonGroup}>
        <Button onClick={() => selectChatRoom(room.roomname)} variant='info'>Enter</Button>
        <Dropdown.Toggle split variant='info' />
        <Dropdown.Menu>
          <Dropdown.Item onClick={() => sendExitRoom(room.roomname)}>Exit</Dropdown.Item>
          {
            room.owner === self.username &&
            <Dropdown.Item onClick={() => setShowUpdateRoom(true)}>
              Edit
            </Dropdown.Item>
          }
          <UpdateRoom
            show={showUpdateRoom}
            handleClose={() => setShowUpdateRoom(false)}
            room={room}
          ></UpdateRoom>
        </Dropdown.Menu>
      </Dropdown>

    </ListGroup.Item>
  )
}


let MyRooms = ({ className, self, rooms, selectChatRoom, sendExitRoom }) => {
  const [showNewRoom, setShowNewRoom] = useState(false);

  return (
    <div className={className}>
      <Card>
        <Card.Header>
          My Rooms
          <Dropdown className='float-right' as={ButtonGroup}>
            <Button onClick={() => setShowNewRoom(true)} variant='success'>new</Button>
            <Dropdown.Toggle split variant='success' />
            <Dropdown.Menu>
              <Dropdown.Item onClick={() => { for (let room of rooms) sendExitRoom(room.roomname) }}>
                Exit all rooms
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>

        </Card.Header>
        <ListGroup variant='flush'>
          {
            rooms.map((room, idx) => (
              <RoomListItem room={room} self={self} sendExitRoom={sendExitRoom} selectChatRoom={selectChatRoom} key={idx}></RoomListItem>
            ))
          }
        </ListGroup>
        <NewRoom show={showNewRoom} handleClose={() => setShowNewRoom(false)}></NewRoom>

      </Card>
    </div >
  );
};

const mapStateToProps = state => ({
  rooms: state.myRooms,
  self: state.self
});

const mapDispatchToProps = dispatch => ({
  selectChatRoom: (name) => dispatch(selectChatRoom(name)),
  sendExitRoom: (roomname) => {
    dispatch(exitChatRoom(roomname));
    dispatch(sendExitRoom(roomname));
  }
});

MyRooms = connect(mapStateToProps, mapDispatchToProps)(MyRooms);

export default MyRooms;