import React from "react";
import './ProspectaCliente.css'
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import PeekPessoa from "./PeekPessoa";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import ListGroup from "react-bootstrap/ListGroup";

class ProspectaCliente extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            id: 1,
            versao: '2',
            cnpj: '',
            razao_social: '22',
            cpf: '3',
            mcc: '4',
            nome: '5',
            email: '6'
        };
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
                        <Button variant="primary" size="lg">
                            Prospecta
                        </Button>
                    </Col>
                    <Col>
                    {this.state.cnpj ? (<div>
                        <p>Pessoa jurídica retirada da fila</p>
                        <Card style={{ width: '24rem' }}>
                            <ListGroup variant="flush">
                                <ListGroup.Item>ID: <b>{this.state.id}</b></ListGroup.Item>
                                <ListGroup.Item>Versão: <b>{this.state.versao}</b></ListGroup.Item>
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
                                <ListGroup.Item>ID: <b>{this.state.id}</b></ListGroup.Item>
                                <ListGroup.Item>Versão: <b>{this.state.versao}</b></ListGroup.Item>
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
