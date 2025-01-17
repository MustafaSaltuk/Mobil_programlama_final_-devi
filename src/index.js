import React from 'react';
import ReactDOM from 'react-dom';
import {Route, Link, BrowserRouter as Router} from 'react-router-dom'
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import Login from './components/Login'
import Uye from './components/Uye'
import {combineReducers, createStore} from 'redux';
import {Provider} from 'react-redux';
import userReducer from './reducers/userReducer';

const rootReducer = combineReducers({
    user: userReducer
});

const store = createStore(rootReducer, {user: false}, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());

class Routes extends React.Component {
    render() {
        return (
            <Router>
                <div>
                    <Route path="/" component={App}/>
                    <Route path="/Uye" component={Uye}/>
                    <Route path="/Login" component={Login}/>
                </div>
            </Router>

        );
    }
}

ReactDOM.render(
    <Provider store={store}>
        <Router>
            <Routes/>
        </Router>
    </Provider>
    , document.getElementById('root'));

serviceWorker.unregister();
