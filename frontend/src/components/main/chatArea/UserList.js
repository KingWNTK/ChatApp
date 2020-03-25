import React from 'react';
import { connect } from 'react-redux';
import { Card, ListGroup, Badge } from 'react-bootstrap';

import { selectChatUser } from '../../../actions/actions';

let UserList = ({ className, users, owner, selectedUser, selectChatUser }) => {
  let UserItem = ({ username }) => {
    const handleClick = () => {
      selectChatUser(username);
    }
    return (
      <ListGroup.Item action onClick={handleClick} variant={username === selectedUser.username ? 'success' : ''}>
        {username} {username === owner ? <Badge variant='info'>owner</Badge> : ''}
      </ListGroup.Item>
    );  
  };
  
  return (
    <div className={className}>
      <Card>
        <Card.Header>
          Users
        </Card.Header>
        <ListGroup variant='flush'>
          {
            users.map((username, idx) => (
              <UserItem username={username} key={idx}></UserItem>
            ))
          }
        </ListGroup>
      </Card>
    </div>
  );
};

const mapStateToProps = state => ({
  self: state.self,
  users: state.selectedRoom.users,
  owner: state.selectedRoom.owner,
  selectedUser: state.selectedUser
});

const mapDispatchToProps = dispatch => ({
  selectChatUser: (username) => dispatch(selectChatUser(username))
});

UserList = connect(mapStateToProps, mapDispatchToProps)(UserList);

export default UserList;