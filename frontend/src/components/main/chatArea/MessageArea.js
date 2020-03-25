import React, { useState } from 'react';
import { connect } from 'react-redux';
import { Card, Button, Row, ListGroup, Badge } from 'react-bootstrap';


import '../main.css';

let MessageArea = ({ className, self, msgs }) => {
  const getTimeString = (timestamp) => {
    return new Date(timestamp).toLocaleDateString("en-US") + ' ' + new Date(timestamp).toLocaleTimeString("en-US")
  }
  const UserMessage = ({ msg }) => {
    const isFromSelf = msg.from === self.username;
    return (
      <ListGroup>
        <ListGroup.Item className='my-message-item'>
          <div className={isFromSelf ? 'float-right' : 'float-left'}>
            {new Date(msg.timestamp).toLocaleTimeString("en-US")} <Badge pill variant='secondary'>{msg.from}</Badge> said to {msg.toAll ? <Badge pill variant='info'>all</Badge> : <Badge pill variant='secondary'>{msg.to}</Badge>}
          </div>
        </ListGroup.Item>
        <ListGroup.Item className='mb-3 my-message-item'>
          <Card
            bg={isFromSelf ? 'primary' : 'light'}
            text={isFromSelf ? 'white' : 'black'}
            className={'my-message-card shadow-sm ' + (isFromSelf ? 'float-right' : 'float-left')}
          >
            {msg.text}
          </Card>
        </ListGroup.Item>
      </ListGroup>
    )
  };
  const SystemMessage = ({ msg }) => {
    return (
      <Card className='my-message-card mx-auto mb-3'>
        <div>
          {getTimeString(msg.timestamp)} <Badge pill variant='secondary'>{msg.from}</Badge> {msg.text}

        </div>
      </Card>
    )
  };
  const MessageItem = ({ msg }) => {
    return msg.isSysMsg ? <SystemMessage msg={msg}></SystemMessage> : <UserMessage msg={msg}></UserMessage>;
  };

  return (
    <div className={className}>
      <Card className='my-message-area overflow-auto'>
        <Card.Body className='pb-0'>
          {
            msgs.map((msg, idx) => (
              <MessageItem msg={msg} key={idx}></MessageItem>
            ))
          }
        </Card.Body>
      </Card>
    </div>
  );
};

const mapStateToProps = state => ({
  self: state.self,
  msgs: state.selectedRoom.msgs || []
});

const mapDispatchToProps = dispatch => ({
});

MessageArea = connect(mapStateToProps, mapDispatchToProps)(MessageArea);

export default MessageArea;