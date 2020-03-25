import React from 'react';
import { connect } from 'react-redux';
import { Card, Badge } from 'react-bootstrap';

let RoomInfo = ({ className, roomname, ageMin, ageMax, areas, schools }) => {
  return (
    <div className={className}>
      <Card>
        <Card.Header>Room Info</Card.Header>
        <Card.Body>
          <Card.Text>Room Name: {roomname}</Card.Text>
          <Card.Text>Age: {ageMin} to {ageMax}</Card.Text>
          <Card.Text>Areas:</Card.Text>
          <Card.Text>
            {
              areas.map((area, idx) => (
                <Badge key={idx} pill variant='warning'>{area}</Badge>
              ))
            }
          </Card.Text>
          <Card.Text>Schools:</Card.Text>
          <Card.Text>
            {
              schools.map((school, idx) => (
                <Badge key={idx} pill variant='warning'>{school}</Badge>
              ))
            }
          </Card.Text>
        </Card.Body>
      </Card>
    </div>
  );
};

const mapStateToProps = state => ({
  roomname: state.selectedRoom.roomname,
  ageMin: state.selectedRoom.ageMin,
  ageMax: state.selectedRoom.ageMax,
  areas: state.selectedRoom.areas,
  schools: state.selectedRoom.schools
});

const mapDispatchToProps = dispatch => ({

});

RoomInfo = connect(mapStateToProps, mapDispatchToProps)(RoomInfo);

export default RoomInfo;