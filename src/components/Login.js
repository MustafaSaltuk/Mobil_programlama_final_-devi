import React from 'react'
import {Col, Card, Button} from "react-bootstrap";
import axios from "axios";
import {Link, BrowserRouter as Router, Redirect} from 'react-router-dom'
import {connect} from 'react-redux';
import {updateUser} from "../actions/user-actions";

class Login extends React.Component {

    constructor() {
        super();
        this.login = this.login.bind(this);
        this.kontrol = this.kontrol.bind(this);
        this.state = {
            username: '',
            password: '',
            loginError: ''
        }
    }

    validateForm() {
        return (
            this.state.username.length > 0 &&
            this.state.password.length > 0
        );
    }

    onChange = (e) => {
        this.setState({[e.target.name]: e.target.value});
    }

    handleSubmit = (e) => {
        e.preventDefault();
    }

    async login() {
        let sendData = {
            username: this.state.username,
            password: this.state.password
        };
        localStorage.setItem('name', sendData.username);
        await axios.post('http://localhost:8080/login', JSON.stringify(sendData), {
            headers: {
                'Content-Type': 'application/json'
            }
        }, {
            withCredentials: true
        }).then(res => localStorage.setItem('jwt', res.data.Authorization));
        this.kontrol();
    }

    kontrol() {
        if (localStorage.getItem('jwt') != 'null') {
            this.props.onUpdateUser(true);
            this.props.history.push("/Ariza");
        } else {
            this.setState({
                loginError: "hatalı kullanıcı adı veya şifre"
            })
        }
    }

    render() {
        const {username, password} = this.state;

        return (
            <center>
                <Card>
                    <Card.Header><h3>GİRİŞ YAP</h3></Card.Header>
                    <Card.Body>
                        <div className="container">

                            <form
                                className="form-signin"
                                onSubmit={this.handleSubmit}
                            >
                                <p>{this.state.loginError}</p>
                                <div className="form-group">
                                    <Col md={4}>
                                        <input
                                            type="text"
                                            name="username"
                                            className="form-control"
                                            placeholder="Kullanıcı Adı"
                                            value={this.state.username}
                                            onChange={this.onChange}

                                        />
                                        <span className="help-block"></span>
                                    </Col>
                                </div>

                                <div className="form-group">
                                    <Col md={4}>
                                        <input
                                            type="password"
                                            name="password"
                                            className="form-control"
                                            placeholder="Şifre"
                                            value={this.state.password}
                                            onChange={this.onChange}
                                        />
                                    </Col>
                                    <span className="help-block"></span>
                                </div>


                                <Col md={4}>
                                    <button
                                        className="btn btn-lg btn-primary btn-block"
                                        type="submit"
                                        disabled={!this.validateForm()}

                                        onClick={this.login}
                                    >
                                        Giriş Yap
                                    </button>

                                </Col>
                            </form>
                        </div>
                    </Card.Body>

                </Card>
            </center>
        );
    }

}

const mapStateToProps = state => {
    return state;
};

const mapDispatchToProps = {
    onUpdateUser: updateUser
};

export default connect(mapStateToProps, mapDispatchToProps)(Login);