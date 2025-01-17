import React from 'react'
import {Col, Card, Button} from "react-bootstrap";
import axios from "axios";
import {Link, BrowserRouter as Router} from 'react-router-dom'


class Uye extends React.Component {
    constructor() {
        super();
        this.UyeEkle = this.UyeEkle.bind(this);
        this.validateForm = this.validateForm.bind(this);
        this.state = {
            username: '',
            password: '',
            confirmPassword: '',
        }
    }

    validateForm() {
        return (
            this.state.username.length > 0 &&
            this.state.password.length > 0 &&
            this.state.password === this.state.confirmPassword
        );
    }

    onChange = (e) => {
        this.setState({[e.target.name]: e.target.value});
    }

    handleSubmit = (e) => {
        e.preventDefault();


    }

    UyeEkle() {
        let sendData = {
            username: this.state.username,
            password: this.state.password
        };

        axios.post('http://localhost:8080/personel/sign-up', JSON.stringify(sendData), {
            headers: {
                'Content-Type': 'application/json'
            },
        }).then(function (res) {
            console.log(res)
        }).catch(function (error) {
            console.log(error)
        });
    }

    render() {
        const {username, password, confirmPassword} = this.state;
        return (

            <center>
                <Card>
                    <Card.Header><h3> KAYIT OL</h3></Card.Header>
                    <Card.Body>
                        <div className="container">
                            <form
                                className="form-signin"
                                onSubmit={this.handleSubmit}
                            >
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
                                <div className="form-group">
                                    <Col md={4}>
                                        <input
                                            type="password"
                                            name="confirmPassword"
                                            className="form-control"
                                            placeholder="Tekrar Şifre"
                                            value={this.state.confirmPassword}
                                            onChange={this.onChange}
                                        />
                                    </Col>
                                    <span className="help-block"></span>
                                </div>


                                <Col md={4}>
                                    <Link to="/Login">
                                        <button
                                            className="btn btn-lg btn-primary btn-block"
                                            type="submit"
                                            //  disabled={this.state.isDisabled}
                                            disabled={!this.validateForm()}
                                            onClick={this.UyeEkle}
                                        >
                                            Kayıt Ol
                                        </button>
                                    </Link>
                                </Col>
                            </form>
                        </div>
                    </Card.Body>

                </Card>
            </center>
        );
    }
}

export default Uye;