import React from 'react';
import { connect } from 'react-redux';
import { Card, Row, Col } from 'react-bootstrap';

import RoomInfo from './RoomInfo';
import MessageArea from './MessageArea';
import UserList from './UserList';
import SendArea from './SendArea';

import '../main.css'

let ChatArea = ({ className, room }) => {
  if (!room) {
    return (
      <div className={className}>
        <Card className='mt-3'>
          <Card.Body>
            Enter a chat room!
          </Card.Body>
        </Card>
      </div>
    )
  }
  return (
    <div className={className}>
      <Row>
        <Col className='mt-3'>
          <MessageArea></MessageArea>
          <SendArea></SendArea>
        </Col>
        <Col className='mt-3' sm={3}>
          <RoomInfo></RoomInfo>
          <UserList className='mt-3'></UserList>
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = state => ({
  room: state.selectedRoom
});

const mapDispatchToProps = dispatch => ({

});

ChatArea = connect(mapStateToProps, mapDispatchToProps)(ChatArea);

export default ChatArea;