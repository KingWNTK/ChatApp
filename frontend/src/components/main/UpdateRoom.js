import React, { useState } from 'react';
import { connect } from 'react-redux';
import { Button, Modal, Form } from 'react-bootstrap';

import { validAreas, validSchools, sendUpdateRoom } from '../../actions/actions';

import './main.css'

let UpdateRoom = ({ className, show, handleClose, room, self, sendUpdateRoom }) => {
  const [input, setInput] = useState({
    ageMin: room ? room.ageMin : '',
    ageMax: room ? room.ageMax : '',
    areas: validAreas.map(area => ({ name: area, checked: room ? room.areas.includes(area) : true })),
    schools: validSchools.map(school => ({ name: school, checked: room ? room.schools.includes(school) : true}))
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
    let ageMin = parseInt(input.ageMin);
    let ageMax = parseInt(input.ageMax);
    let areas = input.areas.filter(a => a.checked).map(a => a.name);
    let schools = input.schools.filter(s => s.checked).map(s => s.name);
    let ok = ageMin !== NaN && ageMax !== NaN && ageMin <= ageMax
      && ageMax >= 0 && ageMax <= 120 && ageMin >= 0 && ageMin <= 120
      && areas.length !== 0 && schools.length !== 0
    if(ok) {
      sendUpdateRoom(room.roomname, {ageMin, ageMax, areas, schools});
    }
  }
  return (
    <div className={className}>
      <Modal show={show}>
        <Modal.Header>
          <Modal.Title>Edit Room</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
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
          <Button variant="primary" onClick={() => {handleSubmit(); handleClose()}}>
            Save
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  )
};

const mapStateToProps = state => ({
  self: state.self,
});

const mapDispatchToProps = dispatch => ({
  sendUpdateRoom: (roomname, info) => dispatch(sendUpdateRoom(roomname, info))
});

UpdateRoom = connect(mapStateToProps, mapDispatchToProps)(UpdateRoom);

export default UpdateRoom;