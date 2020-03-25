import React from 'react';
import {connect} from 'react-redux';
import { Card } from 'react-bootstrap';

let UserInfo = ({className, self}) => {
  return (
    <div className={className}>
      <Card>
        <Card.Body>
          <Card.Title>{self.username}</Card.Title>
          <Card.Text>Age: {self.age}</Card.Text>
          <Card.Text>Area: {self.area}</Card.Text>
          <Card.Text>School: {self.school}</Card.Text>
        </Card.Body>
      </Card>
    </div>
  );
};

const mapStateToProps = state => ({
  self: state.self
});

const mapDispatchToProps = dispatch => ({

});

UserInfo = connect(mapStateToProps, mapDispatchToProps)(UserInfo);

export default UserInfo;