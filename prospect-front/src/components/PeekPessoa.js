import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';

function PeekPessoa() {
    return (
        <Card style={{ width: '24rem' }}>
            <ListGroup variant="flush">
                <ListGroup.Item>Cadastro: <b>12345678900</b></ListGroup.Item>
                {/*<ListGroup.Item>Versão: <b>2023-09-08 45:54:87.4324</b></ListGroup.Item>*/}
            </ListGroup>
        </Card>
    );
}

export default PeekPessoa;