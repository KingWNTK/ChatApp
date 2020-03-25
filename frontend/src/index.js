import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import thunkMiddleware from 'redux-thunk';
import { Provider } from 'react-redux';
import { createStore, compose, applyMiddleware } from 'redux';
import { Jumbotron, Container } from 'react-bootstrap';
import { Route, HashRouter as Router, Switch } from 'react-router-dom';
import reducers from './reducers/reducers';

import Landing from './components/landing/Landing';
import Main from './components/main/Main';

import wsMiddleware from './middleware/wsMiddleware';
import * as act from './actions/actions';

import * as serviceWorker from './serviceWorker';
import './index.css';


let store;
if (process.env.NODE_ENV === 'development' && window.__REDUX_DEVTOOLS_EXTENSION__) {
  store = createStore(
    reducers,
    compose(
      applyMiddleware(
        thunkMiddleware,
        wsMiddleware
      ),
      window.__REDUX_DEVTOOLS_EXTENSION__(),
    )
  );
}
else {
  store = createStore(
    reducers,
    compose(
      applyMiddleware(
        thunkMiddleware,
        wsMiddleware
      )
    )
  );
}


const notFound = () => <Jumbotron fluid><Container><h1>Ricebook: Page Not Found!</h1></Container></Jumbotron>


class WebSocketConnection extends Component {
  componentDidMount() {
    const { dispatch, host } = this.props;
    dispatch({ type: act.WS_CONNECT, host });
  }

  render() {
    return <div>{this.props.children}</div>
  }
}

const WS_URL = process.env.NODE_ENV === 'development'
  ? 'ws://localhost:4567/chatapp'
  : 'wss://chatapp-team-dragonball.herokuapp.com/chatapp';

const Root = ({ store }) => {
  return (
    <Provider store={store}>
      <WebSocketConnection
        dispatch={store.dispatch}
        host={WS_URL}
      >
        <Router>
          <Switch>
            <Route exact path='/' component={Landing} />
            <Route path='/main' component={Main} />
            <Route component={notFound} />
          </Switch>
        </Router>
      </WebSocketConnection>
    </Provider>

  )
};

ReactDOM.render(<Root store={store} />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
