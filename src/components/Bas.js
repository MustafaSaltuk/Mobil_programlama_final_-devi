import React, {Component} from 'react';
import {Link} from "react-router-dom";
import {fixedMenuStyle, menuStyle} from "../helpers/Stylehelpers";
import {Container, Visibility, Menu, Image, Header, Icon} from 'semantic-ui-react'
import {updateUser} from "../actions/user-actions";
import {connect} from "react-redux";

class Bas extends Component {

    state = {
        menuFixed: null,
        overlayFixed: false
    };


    stickTopMenu = () => this.setState({menuFixed: true});
    unStickTopMenu = () => this.setState({menuFixed: null});

    Logout = () => {
        localStorage.setItem('jwt', 'null');
        this.props.onUpdateUser(false);
    }

    render() {

        const {menuFixed} = this.state;
        const loggedIn = this.props.user;
        if (loggedIn === false) {
            return (
                <div>
                    <Visibility
                        onBottomPassed={this.stickTopMenu}
                        onBottomVisible={this.unStickTopMenu}
                        once={false}>
                        <Menu
                            borderless
                            fixed={menuFixed && 'top'}
                            style={menuFixed ? fixedMenuStyle : menuStyle}>
                            <Container>
                                <Menu.Item>
                                    <Image size='tiny'
                                           src='https://www.allaboutlean.com/wp-content/uploads/2018/11/Toyota-Logo-2.jpg'/>
                                </Menu.Item>
                            </Container>
                            <Menu.Item as={Link} to="/Login">
                                <Icon name='sign-in' circular/> <h5> Giriş Yap</h5>
                            </Menu.Item>
                            <Menu.Item as={Link} to="/Uye">
                                <Icon name='add user' circular/> <h5> Kayıt Ol</h5>
                            </Menu.Item>
                        </Menu>
                    </Visibility>

                </div>
            )
        } else {
            return (
                <div>
                    <Visibility
                        onBottomPassed={this.stickTopMenu}
                        onBottomVisible={this.unStickTopMenu}
                        once={false}>
                        <Menu
                            borderless
                            fixed={menuFixed && 'top'}
                            style={menuFixed ? fixedMenuStyle : menuStyle}>
                            <Container>
                                <Menu.Item>
                                    <Image size='tiny'
                                           src='https://www.allaboutlean.com/wp-content/uploads/2018/11/Toyota-Logo-2.jpg'/>
                                </Menu.Item>
                            </Container>
                            <Menu.Item>
                                <Icon name='user' circular textAlign='center'/>
                                <h5>{localStorage.getItem('name')}</h5>
                            </Menu.Item>
                            <Menu.Item as={Link} onClick={this.Logout} to="/Login">
                                <Icon name='log out' circular/> <h5> Çıkış Yap </h5>
                            </Menu.Item>
                        </Menu>
                    </Visibility>
                </div>
            )
        }
    }
}

const mapStateToProps = state => {
    return state;
};

const mapDispatchToProps = {
    onUpdateUser: updateUser
};

export default connect(mapStateToProps, mapDispatchToProps)(Bas);