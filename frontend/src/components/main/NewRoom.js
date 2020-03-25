import React, { useState } from 'react';
import { connect } from 'react-redux';
import { Button, Modal, Form } from 'react-bootstrap';

import { validAreas, validSchools, sendCreateRoom } from '../../actions/actions';

import './main.css'

let NewRoom = ({ className, self, show, handleClose, sendCreateRoom }) => {
  const [input, setInput] = useState({
    roomname: '',
    ageMin: Math.min(self.age, 10),
    ageMax: Math.max(self.age, 80),
    areas: validAreas.map(area => ({ name: area, checked: area === self.area})),
    schools: validSchools.map(school => ({ name: school, checked: '' + school === '' + self.school}))
  });

  const handleSelectArea = (area) => {
    let areas = input.areas.map(a => {
      return a.name === area ? ({ name: a.name, checked: !a.checked }) : ({ name: a.name, checked: a.checked });
    });
    setInput(Object.assign({}, input, { areas }));
  };
  const handleSelectSchool = (school) => {
    let schools = input.schools.map(s => {
      return s.name === school ? ({ name: s.name, checked: !s.checked }) : ({ name: s.name, checked: s.checked });
    });
    setInput(Object.assign({}, input, { schools }));
  };
  const handleOnChange = (e, name) => {
    setInput(Object.assign({}, input, { [name]: e.target.value }));
  };
  const handleSubmit = () => {
    let roomname = input.roomname;
    let ageMin = parseInt(input.ageMin);
    let ageMax = parseInt(input.ageMax);
    let areas = input.areas.filter(a => a.checked).map(a => a.name);
    let schools = input.schools.filter(s => s.checked).map(s => s.name);
    let ok = ageMin !== NaN && ageMax !== NaN && ageMin <= ageMax
      && ageMax >= 0 && ageMax <= 120 && ageMin >= 0 && ageMin <= 120
      && areas.length !== 0 && schools.length !== 0 && roomname !== '';
    if(ok) {
      sendCreateRoom({
        owner: self.username,
        roomname,
        ageMin,
        ageMax,
        areas,
        schools,
        users: [self.username],
        msgs: []
      })
    }
    else {
      console.log('nope');
    }
  }
  return (
    <div className={className}>
      <Modal show={show}>
        <Modal.Header>
          <Modal.Title>New Room</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group>
              <Form.Label>Room name</Form.Label>
              <Form.Control value={input.roomname} onChange={e => handleOnChange(e, 'roomname')}></Form.Control>
            </Form.Group>
            <Form.Group>
              <Form.Label>Minimum age</Form.Label>
              <Form.Control value={input.ageMin} onChange={e => handleOnChange(e, 'ageMin')}></Form.Control>
            </Form.Group>
            <Form.Group>
              <Form.Label>Maximum age</Form.Label>
              <Form.Control value={input.ageMax} onChange={e => handleOnChange(e, 'ageMax')}></Form.Control>
            </Form.Group>
            Areas
            {
              validAreas.map((area, idx) => (
                <Form.Check key={idx} type='checkbox' label={area}
                  checked={input.areas.find(a => a.name === area).checked}
                  onChange={() => handleSelectArea(area)}
                >
                </Form.Check>
              ))
            }
            Schools
            {
              validSchools.map((school, idx) => (
                <Form.Check key={idx} type='checkbox' label={school}
                  checked={input.schools.find(s => s.name === school).checked}
                  onChange={() => handleSelectSchool(school)}
                >
                </Form.Check>
              ))
            }
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={(e) => {handleSubmit(); handleClose() }}>
            New
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  )
};

const mapStateToProps = state => ({
  self: state.self,
  selectedUser: state.selectedUser,
  room: state.selectedRoom
});

const mapDispatchToProps = dispatch => ({
  sendCreateRoom: room => dispatch(sendCreateRoom(room))
});

NewRoom = connect(mapStateToProps, mapDispatchToProps)(NewRoom);

export default NewRoom;