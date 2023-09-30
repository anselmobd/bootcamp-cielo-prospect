import React from "react";
import axios from "axios";
import './ProspectaCliente.css'
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import ListGroup from "react-bootstrap/ListGroup";

class ProspectaCliente extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            pessoa: {
                id: '1',
                versao: '2',
                cnpj: '3',
                razao_social: '4',
                cpf: '5',
                mcc: '6',
                nome: '7',
                email: '8'
            },
            pessoa_versao: {
                cadastro: '111',
                versao: '222',
            }
        }
    };

    componentDidMount() {
        this.initStatePessoa();
        this.buscaPessoaVersao();
    }

    initStatePessoa = () => {
        this.setState(
            {
                pessoa: {
                    id: '',
                    versao: '',
                    cnpj: '',
                    razao_social: '',
                    cpf: '',
                    mcc: '',
                    nome: '',
                    email: ''
                }
            }
        );
    }

    initStatePessoaVersao = () => {
        this.setState(
            {
                pessoa_versao: {
                    cadastro: '',
                    versao: '',
                }
            }
        );
    }

    buscaPessoaVersao = () => {
        this.initStatePessoaVersao();
        axios.get("http://localhost:8080/api/v1/pessoa_versao/consulta")
            .then(res => {
                console.log("consuta: algo na fila");
                console.log(res);
                let dados = res.data;
                if (dados.length > 0) {
                    this.setState({pessoa_versao: dados});
                }
            })
            .catch(err => {
                console.log(err);
                console.log("consuta: nada na fila");
            });
    }

    fetchPessoaDados = () => {
        this.initStatePessoa();
        fetch("http://localhost:8080/api/v1/pessoa_fisica")
            .then(response => {
                console.log(response);
                response.json();
            })
            .then(data => {
                if (data.length > 0) {
                    this.setState({pessoa: data[0]});
                }
            });

        // axios.get("http://localhost:8080/api/v1/pessoa_fisica")
        //     .then(res => {
        //         console.log("retira: algo na fila");
        //         console.log(res);
        //         console.log("retira: res.data");
        //         console.log(res);
        //         let dados = res.data;
        //         if (dados.length > 0) {
        //             this.setState({pessoa: dados[0]});
        //         }
        //     })
        //     .catch(res => {
        //         console.log(res);
        //         console.log("retira: nada na fila");
        //     });
    }

    render() {
        return (
        <div>
            <h1 className="titulo_central">Prospecta pessoa</h1>
            <Container>
                <Row>
                    <Col>
                        <p>Consulta informação na fila</p>
                        <Card style={{ width: '24rem' }}>
                            <ListGroup variant="flush">
                                <ListGroup.Item>
                                    <span>Cadastro: </span><b>{this.state.pessoa_versao.cadastro}</b>
                                </ListGroup.Item>
                            </ListGroup>
                        </Card>
                    </Col>
                    <Col className="titulo_central">
                        <p>&nbsp;</p>
                        <Button variant="primary" size="lg" onClick={this.buscaPessoaVersao}>
                            Consulta
                        </Button>
                        <Button variant="primary" size="lg" onClick={this.fetchPessoaDados}>
                            Prospecta
                        </Button>
                    </Col>
                    <Col>
                    {this.state.pessoa.cnpj ? (<div>
                        <p>Pessoa jurídica retirada da fila</p>
                        <Card style={{ width: '24rem' }}>
                            <ListGroup variant="flush">
                                <ListGroup.Item>CNPJ: <b>{this.state.pessoa.cnpj}</b></ListGroup.Item>
                                <ListGroup.Item>Razão Social: <b>{this.state.pessoa.razao_social}</b></ListGroup.Item>
                                <ListGroup.Item>MCC: <b>{this.state.pessoa.mcc}</b></ListGroup.Item>
                                <ListGroup.Item>CPF: <b>{this.state.pessoa.cpf}</b></ListGroup.Item>
                                <ListGroup.Item>Nome: <b>{this.state.pessoa.nome}</b></ListGroup.Item>
                                <ListGroup.Item>E-mail: <b>{this.state.pessoa.email}</b></ListGroup.Item>
                            </ListGroup>
                        </Card>
                    </div>) : (<div>
                        <p>Pessoa física retirada da fila</p>
                        <Card style={{ width: '24rem' }}>
                            <ListGroup variant="flush">
                                <ListGroup.Item>CPF: <b>{this.state.pessoa.cpf}</b></ListGroup.Item>
                                <ListGroup.Item>MCC: <b>{this.state.pessoa.mcc}</b></ListGroup.Item>
                                <ListGroup.Item>Nome: <b>{this.state.pessoa.nome}</b></ListGroup.Item>
                                <ListGroup.Item>E-mail: <b>{this.state.pessoa.email}</b></ListGroup.Item>
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
