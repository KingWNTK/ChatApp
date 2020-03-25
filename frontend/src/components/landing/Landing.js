import React, { useState } from 'react';
import { connect } from 'react-redux'
import { Redirect } from 'react-router-dom';
import { Container, Jumbotron, Form, Button } from 'react-bootstrap';

import { sendCheckUser, sendRegisterUser, validAreas, validSchools } from '../../actions/actions';

import './landing.css';

let Landing = ({ sendCheckUser, sendRegisterUser, verified, needRegister }) => {
  const [input, setInput] = useState({
    username: '',
    age: '',
    area: validAreas[0],
    school: validSchools[0]
  });


  const handleOnChange = (e, name) => {
    setInput(Object.assign({}, input, { [name]: e.target.value }));
  }

  const validateInput = () => {
    let age = parseInt(input.age);
    if (age === NaN) return false;
    return input.username !== '' && age >= 0 && age <= 120
      && validAreas.includes(input.area) && validSchools.includes(input.school);
  }

  const handleSubmit = e => {
    e.preventDefault();
    if (needRegister) {
      if (validateInput()) {
        console.log(input)
        sendRegisterUser(Object.assign({}, input, {age: parseInt(input.age)}));
      }
    }
    else {
      if (input.username !== '') {
        sendCheckUser(input.username);
      }
    }
  }

  return (
    <div>
      {verified && <Redirect to='/main' push></Redirect>}
      <Jumbotron>
        <h1 >
          Dragonball chat
        </h1>
      </Jumbotron>
      <Container>
        <div className='mx-auto mid-div'>
          <Form onSubmit={handleSubmit}>
            <Form.Group>
              <Form.Label>Username</Form.Label>
              <Form.Control value={input.username} onChange={e => handleOnChange(e, 'username')} placeholder="Enter username" />
            </Form.Group>
            {
              needRegister &&
              <div>
                <Form.Group>
                  <Form.Label>Age</Form.Label>
                  <Form.Control value={input.age} onChange={e => handleOnChange(e, 'age')} placeholder="Enter age" />
                </Form.Group>
                <Form.Group>
                  <Form.Label>Select Area</Form.Label>
                  <Form.Control value={input.area} onChange={e => handleOnChange(e, 'area')} as="select">
                    {
                      validAreas.map((area, idx) => (
                        <option key={idx}>{area}</option>
                      ))
                    }
                  </Form.Control>
                </Form.Group>
                <Form.Group>
                  <Form.Label>Select school</Form.Label>
                  <Form.Control value={input.school} onChange={e => handleOnChange(e, 'school')} as="select">
                    {
                      validSchools.map((school, idx) => (
                        <option key={idx}>{school}</option>
                      ))
                    }
                  </Form.Control>
                </Form.Group>
              </div>
            }
            <Button variant="primary" type="submit">
              Submit
            </Button>
          </Form>
        </div>
      </Container>
    </div>
  )
};

const mapStateToProps = state => ({
  verified: state.verified,
  needRegister: state.needRegister
})

const mapDispatchToProps = dispatch => ({
  sendCheckUser: (username) => dispatch(sendCheckUser(username)),
  sendRegisterUser: (user) => dispatch(sendRegisterUser(user))
});

Landing = connect(mapStateToProps, mapDispatchToProps)(Landing);

export default Landing;
