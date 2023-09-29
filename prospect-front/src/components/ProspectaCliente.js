import React from "react";
import axios from "axios";
import './ProspectaCliente.css'
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import PeekPessoa from "./PeekPessoa";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import ListGroup from "react-bootstrap/ListGroup";
import {json} from "react-router-dom";

class ProspectaCliente extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            id: '1',
            versao: '2',
            cnpj: '3',
            razao_social: '4',
            cpf: '5',
            mcc: '6',
            nome: '7',
            email: '8'
        };
    }

    componentDidMount() {
        this.initState();
    }

    initState = () => {
        this.setState(
            {
                id: '',
                versao: '',
                cnpj: '',
                razao_social: '',
                cpf: '',
                mcc: '',
                nome: '',
                email: ''
            }
        );
    }

    fetchPessoaDados = () => {
        this.initState();
        axios.get("http://localhost:8080/api/v1/pessoa_fisica")
            .then(res => {
                let dados = res.data;
                if (dados.length > 0) {
                    this.setState(dados[0]);
                }
            });
    }

    render() {
        return (
        <div>
            <h1 className="titulo_central">Prospecta pessoa</h1>
            <Container>
                <Row>
                    <Col>
                        <p>Marcador da vez na fila</p>
                        <PeekPessoa />
                    </Col>
                    <Col className="titulo_central">
                        <p>&nbsp;</p>
                        <Button variant="primary" size="lg" onClick={this.fetchPessoaDados}>
                            Prospecta
                        </Button>
                    </Col>
                    <Col>
                    {this.state.cnpj ? (<div>
                        <p>Pessoa jurídica retirada da fila</p>
                        <Card style={{ width: '24rem' }}>
                            <ListGroup variant="flush">
                                {/*<ListGroup.Item>ID: <b>{this.state.id}</b></ListGroup.Item>*/}
                                {/*<ListGroup.Item>Versão: <b>{this.state.versao}</b></ListGroup.Item>*/}
                                <ListGroup.Item>CNPJ: <b>{this.state.cnpj}</b></ListGroup.Item>
                                <ListGroup.Item>Razão Social: <b>{this.state.razao_social}</b></ListGroup.Item>
                                <ListGroup.Item>MCC: <b>{this.state.mcc}</b></ListGroup.Item>
                                <ListGroup.Item>CPF: <b>{this.state.cpf}</b></ListGroup.Item>
                                <ListGroup.Item>Nome: <b>{this.state.nome}</b></ListGroup.Item>
                                <ListGroup.Item>E-mail: <b>{this.state.email}</b></ListGroup.Item>
                            </ListGroup>
                        </Card>
                    </div>) : (<div>
                        <p>Pessoa física retirada da fila</p>
                        <Card style={{ width: '24rem' }}>
                            <ListGroup variant="flush">
                                {/*<ListGroup.Item>ID: <b>{this.state.id}</b></ListGroup.Item>*/}
                                {/*<ListGroup.Item>Versão: <b>{this.state.versao}</b></ListGroup.Item>*/}
                                <ListGroup.Item>CPF: <b>{this.state.cpf}</b></ListGroup.Item>
                                <ListGroup.Item>MCC: <b>{this.state.mcc}</b></ListGroup.Item>
                                <ListGroup.Item>Nome: <b>{this.state.nome}</b></ListGroup.Item>
                                <ListGroup.Item>E-mail: <b>{this.state.email}</b></ListGroup.Item>
                            </ListGroup>
                        </Card>
                    </div>)}
                    </Col>
                </Row>
            </Container>
        </div>
        )
    }
}
export default ProspectaCliente;
