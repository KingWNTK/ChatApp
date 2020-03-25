import React, { useState } from 'react';
import { connect } from 'react-redux'
import { Redirect } from 'react-router-dom';

import { Col, Row, Card, Container } from 'react-bootstrap';

import { sendHeartbeat } from '../../actions/actions';

import MyRooms from './MyRooms';
import AvailableRooms from './AvailableRooms';
import ChatArea from './chatArea/ChatArea';
import UserInfo from './UserInfo';

let Main = ({ verified, sendHeartbeat }) => {

  if(verified) {
    setInterval(() => sendHeartbeat(), 30000);
  }
  

  return (
    <div>
      {!verified && <Redirect to='/' push></Redirect>}
      <Container>
        <Row>
          <Col sm={3}>
            <UserInfo className='mt-3'></UserInfo>
            <MyRooms className='mt-3'></MyRooms>
            <AvailableRooms className='mt-3'></AvailableRooms>
          </Col>
          <Col>
            <ChatArea></ChatArea>
          </Col>
        </Row>
      </Container>
    </div>
  )
};

const mapStateToProps = state => ({
  verified: state.verified,
});

const mapDispatchToProps = dispatch => ({
  sendHeartbeat: () => dispatch(sendHeartbeat())
});

Main = connect(mapStateToProps, mapDispatchToProps)(Main);

export default Main;
