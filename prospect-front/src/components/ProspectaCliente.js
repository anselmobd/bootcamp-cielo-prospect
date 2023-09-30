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
                id: '42',
                versao: '2023-09-28T23:14:51.204Z',
                cnpj: '12345678901234',
                razao_social: 'Empresa Ltda.',
                cpf: '12345678901',
                mcc: 'A1B2',
                nome: 'Fulano de Tal',
                email: 'fulano.tal@empresa.ccc'
            },
            pessoa_versao: {
                cadastro: '12345678901',
                versao: '2023-09-28T23:14:23.243Z',
            }
        }
    };

    componentDidMount() {
        // this.initStatePessoa();
        // this.buscaPessoaVersao();
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
                let dados = res.data;
                if (dados) {
                    this.setState({pessoa_versao: dados});
                }
            })
            .catch(err => {
                console.log("nada na fila");
            });
    }

    fetchPessoaDados = () => {
        this.initStatePessoa();
        axios.get("http://localhost:8080/api/v1/pessoa_versao")
            .then(res => {
                let dados = res.data;
                if (dados) {
                    this.setState({pessoa: dados});
                }
            })
            .catch(res => {
                console.log("nada na fila");
            });
        this.buscaPessoaVersao();
    }

    render() {
        return (
        <div>
            <h1 className="titulo_central">Prospecta pessoa física ou jurídica</h1>
            <Container>
                <Row>
                    <Col>
                        <p>Consulta informação na fila</p>
                        <Card style={{ width: '20rem' }}>
                            <ListGroup variant="flush">
                                <ListGroup.Item>
                                    <span>Cadastro: </span><b>{this.state.pessoa_versao.cadastro}</b>
                                </ListGroup.Item>
                            </ListGroup>
                        </Card>
                    </Col>
                    <Col className="titulo_central">
                        <p>&nbsp;</p>
                        <Button variant="primary" size="lg" onClick={this.fetchPessoaDados}>
                            Prospecta
                        </Button>
                    </Col>
                    <Col>
                    {this.state.pessoa.cnpj ? (<div>
                        <p>Pessoa jurídica retirada da fila</p>
                        <Card style={{ width: '20rem' }}>
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
