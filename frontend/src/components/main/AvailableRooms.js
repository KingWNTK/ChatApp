import React from 'react';
import { connect } from 'react-redux';
import { Card, ListGroup, Button } from "react-bootstrap";

import { joinChatRoom, sendJoinRoom } from '../../actions/actions';
let AvailableRooms = ({ className, roomList, joinChatRoom, sendJoinRoom }) => {

  return (
    <div className={className}>
      <Card>
        <Card.Header>
          Available Rooms
        </Card.Header>
        <ListGroup variant='flush'>
          {
            roomList.map((room, idx) => (
              <ListGroup.Item key={idx}>
                {room.roomname}
                <Button onClick={() => sendJoinRoom(room.roomname)} className='float-right'>join</Button>
              </ListGroup.Item>
            ))
          }
        </ListGroup>
      </Card>
    </div>
  );
};

const mapStateToProps = state => ({
  roomList: state.availableRooms || []
});

const mapDispatchToProps = dispatch => ({
  joinChatRoom: (name) => dispatch(joinChatRoom(name)),
  sendJoinRoom: (roomname) => dispatch(sendJoinRoom(roomname))
});

AvailableRooms = connect(mapStateToProps, mapDispatchToProps)(AvailableRooms);

export default AvailableRooms;