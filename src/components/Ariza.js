import React from 'react'
import './Ariza.css';
import {textFilter} from "react-bootstrap-table2-filter";
import axios from "axios";
import {Button, Col, Modal, Row} from "react-bootstrap";
import {Container, Header} from "semantic-ui-react";
import BootstrapTable from "react-bootstrap-table-next";
import filterFactory from "react-bootstrap-table2-filter";
import paginationFactory from "react-bootstrap-table2-paginator";


class Ariza extends React.Component {

    constructor() {
        super();
        this.componentDidMount = this.componentDidMount.bind(this);
        this.buttonFormatter = this.buttonFormatter.bind(this);
        this.toggleModalAdd = this.toggleModalAdd.bind(this);
        this.toggleModalDelete = this.toggleModalDelete.bind(this);
        this.openModalEdit = this.openModalEdit.bind(this);
        this.toggleModalEdit = this.toggleModalEdit.bind(this);
        this.modalEditGet = this.modalEditGet.bind(this);
        this.arizaEkle = this.arizaEkle.bind(this);
        this.arizaGuncelle = this.arizaGuncelle.bind(this);
        this.arizaSil = this.arizaSil.bind(this);
        this.isDisabled = this.isDisabled.bind(this);
        this.arizalariAl = this.arizalariAl.bind(this);
        this.state = {
            modalAdd: false,
            modalEdit: false,
            modalDelete: false,
            isDisabled: true,
            id: '',
            jwt: '',
            post: '',
            isLoading: true,
            posts: [],
            columns: [{
                dataField: 'id',
                text: 'Kayıt No',
                sort: true,
                filter: textFilter()
            }, {
                dataField: 'kayitTarihi',
                text: 'Kayıt Tarihi',
                sort: true,
                filter: textFilter()
            }, {
                dataField: 'kayitYapan',
                text: 'Kayıt Yapan',
                sort: true,
                filter: textFilter()
            }, {
                dataField: 'bildiren',
                text: 'Bildiren',
                sort: true,
                filter: textFilter()
            }, {
                dataField: 'bildirenTel',
                text: 'Bildiren Tel',
                sort: true,
                filter: textFilter()
            }, {
                dataField: 'ariza',
                text: 'Arıza',
                sort: true,
                filter: textFilter()
            }, {
                dataField: 'yeri',
                text: 'Yeri',
                sort: true,
                filter: textFilter()
            }, {
                dataField: 'shop',
                text: 'Shop',
                sort: true,
                filter: textFilter()
            }, {
                dataField: 'atanan',
                text: 'Atanan',
                sort: true,
                filter: textFilter()
            }, {
                dataField: 'acil',
                text: 'Acil',
                sort: true,
                filter: textFilter()
            }, {
                dataField: 'isBitimiAciklama',
                text: 'İş Bitimi Açıklama',
                sort: true,
                filter: textFilter()
            }, {
                dataField: 'tamamlanmaTarihi',
                text: 'Tamamlama Tarihi',
                sort: true,
                filter: textFilter()
            }, {
                dataField: "edit",
                text: "Özellikler",
                sort: false,
                formatter: this.buttonFormatter,
                style: {paddingLeft: 20},
                attrs: {class: "EditRow"},
                events: {
                    onClick: (e, column, columnIndex, row, rowIndex) => {
                        this.setId(row["id"]);
                    }
                }
            }]
        };
    }

    setId(id) {
        this.setState({
            id: id
        });
    }

    async arizaEkle() {
        let sendData = {
            kayitYapan: this.state.kayitYapan,
            bildiren: this.state.bildiren,
            ariza: this.state.ariza,
            yeri: this.state.yeri,
            shop: this.state.shop,
            atanan: this.state.atanan,
            acil: this.state.acil,
            kayitTuru: this.state.kayitTuru,
            bildirenTel: this.state.bildirenTel,
            isBitimiAciklama: this.state.isBitimiAciklama,
            tamamlanmaTarihi: this.state.tamamlanmaTarihi,
            kayitTarihi: this.state.kayitTarihi
        };

        await axios.post('http://localhost:8080/ariza/create', JSON.stringify(sendData), {
            headers: {
                'Content-Type': 'application/json',
                Authorization: localStorage.getItem('jwt')
            },
        }).then(function (res) {
            console.log(res)
        }).catch(function (error) {
            console.log(error)
        });

        this.arizalariAl();

        this.toggleModalAdd();
    }

    async arizaGuncelle() {
        let sendData = {
            kayitYapan: this.state.kayitYapan,
            bildiren: this.state.bildiren,
            ariza: this.state.ariza,
            yeri: this.state.yeri,
            shop: this.state.shop,
            atanan: this.state.atanan,
            acil: this.state.acil,
            kayitTuru: this.state.kayitTuru,
            bildirenTel: this.state.bildirenTel,
            isBitimiAciklama: this.state.isBitimiAciklama,
            tamamlanmaTarihi: this.state.tamamlanmaTarihi,
            kayitTarihi: this.state.kayitTarihi
        };

        await axios.put(`http://localhost:8080/ariza/update/${this.state.id}`, JSON.stringify(sendData), {
            headers: {
                'Content-Type': 'application/json',
                Authorization: localStorage.getItem('jwt')
            },
        }).then(function (res) {
            console.log(res)
        }).catch(function (error) {
            console.log(error)
        });

        this.arizalariAl();

        this.toggleModalEdit();
    }

    async arizaSil() {
        await axios.delete(`http://localhost:8080/ariza/delete/${this.state.id}`, {
            headers: {
                Authorization: localStorage.getItem('jwt')
            }
        })
            .then(res => {
                console.log(res);
            });

        this.arizalariAl();

        this.toggleModalDelete();
    };

    handleClick(e) {
        e.preventDefault();
        this.setState
        ({
            kayitTarihi: this.refs.kayitTarihiRef.value,
            kayitYapan: this.refs.kayitYapanRef.value,
            bildiren: this.refs.bildirenRef.value,
            ariza: this.refs.arizaRef.value,
            yeri: this.refs.yeriRef.value,
            shop: this.refs.shopRef.value,
            atanan: this.refs.atananRef.value,
            acil: this.refs.acilRef.value,
            kayitTuru: this.refs.kayitTuruRef.value,
            bildirenTel: this.refs.bildirenTelRef.value,
            isBitimiAciklama: this.refs.isBitimiAciklamaRef.value,
            tamamlanmaTarihi: this.refs.tamamlanmaTarihiRef.value
        });
        this.isDisabled();
    }

    isDisabled() {
        if (this.state.kayitTarihi && this.state.kayitYapan !== "" && this.state.bildiren !== "" && this.state.ariza !== ""
            && this.state.yeri !== "" && this.state.shop !== "" && this.state.atanan !== "" && this.state.acil !== "Seçiniz"
            && this.state.kayitTuru !== "" && this.state.bildirenTel !== "") {
            this.setState({
                isDisabled: false
            });
        } else {
            this.setState({
                isDisabled: true
            });
        }
    }

    toggleModalAdd() {
        this.setState({
            modalAdd: !this.state.modalAdd,
            isDisabled: true
        });
    }

    openModalEdit() {
        this.setState({
            isDisabled: true
        });
        setTimeout(this.modalEditGet, 10);
    }

    async modalEditGet() {
        await axios.get(`http://localhost:8080/ariza/findbyid/${this.state.id}`, {
            headers: {
                Authorization: localStorage.getItem('jwt')
            }
        }).then(res => {
            this.setState({
                post: res.data,
                kayitTarihi: res.data.kayitTarihi,
                kayitYapan: res.data.kayitYapan,
                bildiren: res.data.bildiren,
                ariza: res.data.ariza,
                yeri: res.data.yeri,
                shop: res.data.shop,
                atanan: res.data.atanan,
                acil: res.data.acil,
                kayitTuru: res.data.kayitTuru,
                bildirenTel: res.data.bildirenTel,
                isBitimiAciklama: res.data.isBitimiAciklama,
                tamamlanmaTarihi: res.data.tamamlanmaTarihi
            })
        });
        this.toggleModalEdit();
    }

    toggleModalEdit() {
        this.setState({
            modalEdit: !this.state.modalEdit
        });
    }

    toggleModalDelete() {
        this.setState({
            modalDelete: !this.state.modalDelete
        });
    }

    buttonFormatter() {
        return (
            <Row>
                <button onClick={this.openModalEdit} className="btn btn-success"><i className="fas fa-edit"/></button>
                <button onClick={this.toggleModalDelete} className="btn btn-danger"><i className="fas fa-trash-alt"/>
                </button>
            </Row>
        )
    }

    componentDidMount() {
        console.log(localStorage.getItem('jwt'));
        this.arizalariAl();
    }

    arizalariAl() {
        //setTimeout(()=>{
        axios.get('http://localhost:8080/ariza/findbykayitturu?kayitturu=TEL', {
            headers: {
                Authorization: localStorage.getItem('jwt')
            }
        }).then(res => {
            this.setState({
                posts: res.data,
                isLoading: false,
            });
        });
        //},2500)
    }

    render() {
        const {isLoading} = this.state;
        return (
            <div>
                <Modal show={this.state.modalAdd}>
                    <Modal.Header>
                        <Modal.Title>Arıza Ekle</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Row>
                            <Col md={3}>
                                Kayıt Tarihi:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="kayitTarihiRef" type="datetime-local"
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Kayıt Yapan:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="kayitYapanRef" type="text"
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Bildiren:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="bildirenRef" type="text"
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Bildiren Tel:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="bildirenTelRef" type="number"
                                       nChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Arıza:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="arizaRef" type="text"
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Yeri:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="yeriRef" type="text"
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Shop:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="shopRef" type="text"
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Atanan:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="atananRef" type="text"
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Acil:
                            </Col>
                            <Col md={9}>
                                <select className="form-control" ref="acilRef"
                                        onChange={this.handleClick.bind(this)}>
                                    <option selected disabled>Seçiniz</option>
                                    <option value="Evet">Evet</option>
                                    <option value="Hayır">Hayır</option>
                                </select>
                            </Col>
                            <Col md={3}>
                                Kayıt Türü:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="kayitTuruRef" type="text"
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                İş Bitimi Açıklama:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="isBitimiAciklamaRef" type="text"
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Tamamlama Tarihi:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="tamamlanmaTarihiRef" type="date"
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                        </Row>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.toggleModalAdd}>İptal</Button>
                        <Button disabled={this.state.isDisabled} variant="primary"
                                onClick={this.arizaEkle}>Kaydet</Button>
                    </Modal.Footer>
                </Modal>

                <Modal show={this.state.modalEdit}>
                    <Modal.Header>
                        <Modal.Title>Arıza Düzenle</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Row>
                            <Col md={3}>
                                Kayıt Tarihi:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="kayitTarihiRef" type="datetime-local"
                                       defaultValue={this.state.post.kayitTarihi}
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Kayıt Yapan:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="kayitYapanRef" type="text"
                                       defaultValue={this.state.post.kayitYapan}
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Bildiren:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="bildirenRef" type="text"
                                       defaultValue={this.state.post.bildiren} onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Bildiren Tel:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="bildirenTelRef" type="number"
                                       defaultValue={this.state.post.bildirenTel}
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Arıza:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="arizaRef" type="text"
                                       defaultValue={this.state.post.ariza} onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Yeri:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="yeriRef" type="text"
                                       defaultValue={this.state.post.yeri} onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Shop:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="shopRef" type="text"
                                       defaultValue={this.state.post.shop} onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Atanan:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="atananRef" type="text"
                                       defaultValue={this.state.post.atanan} onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Acil:
                            </Col>
                            <Col md={9}>
                                <select className="form-control" ref="acilRef"
                                        onChange={this.handleClick.bind(this)}>
                                    <option selected={this.state.post.acil === 'Evet'} value="Evet">Evet</option>
                                    <option selected={this.state.post.acil === 'Hayır'} value="Hayır">Hayır</option>
                                </select>
                            </Col>
                            <Col md={3}>
                                Kayıt Türü:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="kayitTuruRef" type="text"
                                       defaultValue={this.state.post.kayitTuru} onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                İş Bitimi Açıklama:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="isBitimiAciklamaRef" type="text"
                                       defaultValue={this.state.post.isBitimiAciklama}
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                            <Col md={3}>
                                Tamamlama Tarihi:
                            </Col>
                            <Col md={9}>
                                <input className="form-control" ref="tamamlanmaTarihiRef" type="date"
                                       defaultValue={this.state.post.tamamlanmaTarihi}
                                       onChange={this.handleClick.bind(this)}/>
                            </Col>
                        </Row>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.toggleModalEdit}>İptal</Button>
                        <Button disabled={this.state.isDisabled} variant="primary"
                                onClick={this.arizaGuncelle}>Kaydet</Button>
                    </Modal.Footer>
                </Modal>

                <Modal show={this.state.modalDelete}>
                    <Modal.Header>
                        <Modal.Title>Arıza Sil</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Row>
                            <Col md={12}>
                                Kayıt numarası {this.state.id} olan arızayı silmek istediğinden emin misin ?
                            </Col>
                        </Row>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.toggleModalDelete}>
                            İptal
                        </Button>
                        <Button variant="primary" onClick={this.arizaSil}>
                            Sil
                        </Button>
                    </Modal.Footer>
                </Modal>

                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
                      integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
                      crossOrigin="anonymous"/>

                <div className={"container"} style={{marginTop: '2em'}}>
                    <Header as='h1'>ARIZA KAYIT SİSTEMİ</Header>
                    <Button variant="primary" onClick={this.toggleModalAdd}>Arıza Ekle</Button>
                </div>
                {isLoading ? <center><img src='https://thumbs.gfycat.com/FaroffSadGreyhounddog-size_restricted.gif'
                                          alt="loading..."/></center> :
                    <div class="marginTable">
                        <BootstrapTable
                            bootstrap4
                            striped
                            hover
                            keyField='id'
                            data={this.state.posts}
                            filter={filterFactory()}
                            columns={this.state.columns}
                            defaultSorted={defaultSorted}
                            pagination={paginationFactory()}/>
                    </div>
                }
            </div>
        );
    }
}

const defaultSorted = [{
    dataField: 'kayitTarihi',
    order: 'desc'
}];

export default Ariza