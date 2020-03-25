import React, { useState } from 'react';
import { connect } from 'react-redux';
import { Button, Form, Row, Col, Card, Badge, Toast } from 'react-bootstrap';
import { sendSendMsg, setShowSentToast } from '../../../actions/actions';

import '../main.css'

let SendArea = ({ className, self, selectedUser, room, sendMsg, showSentToast, setShowSentToast }) => {
  const [inputText, setInputText] = useState('');

  const handleSendMsg = () => {
    if (inputText === '') return;
    let msg = {
      from: self.username,
      isSysMsg: false,
      text: inputText,
      timestamp: Date.now()
    };
    if (selectedUser.toAll) {
      msg.toAll = true;
    }
    else {
      msg.to = selectedUser.username
    }
    sendMsg(room.roomname, msg);
    setInputText('');
  }
  return (
    <div className={className}>
      <Row>
        <Col className='mt-3'>
          <Form.Control as='textarea' rows='3' value={inputText}
            onChange={e => setInputText(e.target.value)}
          >
          </Form.Control>
        </Col>

      </Row>
      <Row>
        <Col className='mt-3'>
          <div className='float-right'>
            <Button variant='light' onClick={handleSendMsg}>
              Send to {selectedUser.toAll
                ? <Badge pill variant='info'>all</Badge>
                : <Badge pill variant='secondary'>{selectedUser.username}</Badge>}
            </Button>
          </div>
          <Toast onClose={() => setShowSentToast(false)} show={showSentToast} delay={800} autohide>
            <Toast.Body>Message is received</Toast.Body>
          </Toast>
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = state => ({
  self: state.self,
  selectedUser: state.selectedUser,
  room: state.selectedRoom,
  showSentToast: state.showSentToast,
});

const mapDispatchToProps = dispatch => ({
  sendMsg: (roomname, msg) => dispatch(sendSendMsg(roomname, msg)),
  setShowSentToast: (val) => dispatch(setShowSentToast(val))

});

SendArea = connect(mapStateToProps, mapDispatchToProps)(SendArea);

export default SendArea;