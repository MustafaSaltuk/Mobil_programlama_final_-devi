import React, {Component} from 'react';
import './App.css';
import 'semantic-ui-css/semantic.min.css';
import Bas from './components/Bas';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Ariza from "./components/Ariza";
import {BrowserRouter as Router, Route, Redirect, Switch, Link} from "react-router-dom";
import {connect} from 'react-redux';
import {updateUser} from './actions/user-actions';

const ProtectedRoute = ({component: Comp, loggedIn, path, ...rest}) => {
    return (
        <Route
            path={path}
            {...rest}
            render={(props) => {
                return loggedIn ? (
                    <Comp {...props} />
                ) : (
                    <Redirect
                        to={{
                            pathname: "/",
                            state: {
                                prevLocation: path,
                                error: "Önce giriş yapmalısınız!",
                            },
                        }}
                    />
                );
            }}
        />
    );
};

class App extends Component {
    constructor(props) {
        super(props);
        localStorage.setItem('jwt', 'null');
        this.onUpdateUser = this.onUpdateUser.bind(this);
        this.state = {
            loggedIn: this.props.user
        }
    }

    handleLogin = () => {
        const {state = {}} = this.props.location;
        const {prevLocation} = state;

        this.setState(
            {
                loggedIn: true
            },
            () => {

                this.props.history.push(prevLocation || "/Ariza");
            },
        );
    };

    onUpdateUser() {
        this.props.onUpdateUser(true);
    }

    render() {
        const {state = {}} = this.props.location;
        const {error} = state;
        const loggedIn = this.props.user;
        return (
            <div className="App">
                <Bas/>
                <div className="tabs">
                    {error && <div>ERROR: {error}</div>}
                    <Switch>
                        <ProtectedRoute path="/Ariza" loggedIn={loggedIn} component={Ariza}/>
                    </Switch>
                </div>
            </div>
        );
    }
}

const mapStateToProps = state => {
    return state;
};

const mapDispatchToProps = {
    onUpdateUser: updateUser
};

export default connect(mapStateToProps, mapDispatchToProps)(App);
