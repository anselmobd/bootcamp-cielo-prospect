import './App.css';
import Home from "./components/Home";
import PessoaFisica from './components/PessoaFisica';
import PessoaJuridica from './components/PessoaJuridica';
import ProspectaCliente from "./components/ProspectaCliente";
import {
    BrowserRouter,
    Routes,
    Link,
    Route
} from "react-router-dom";
import Nav from 'react-bootstrap/Nav';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Container, Navbar} from "react-bootstrap";

function App() {
  return (
    <div className="container">
      <div className="App">
          <Navbar bg="light" fixed="top">
              <Container>
                  <Navbar.Brand>
                      <img
                          src="/cielo.svg"
                          width="96"
                          height="32"
                          className="d-inline-block align-top"
                          alt="Cielo"
                      />
                  </Navbar.Brand>
                  <div className="text-center">
                      <h1>Prospectar clientes</h1>
                  </div>
              </Container>
          </Navbar>
        <div className="content-margin">
          <BrowserRouter>
              <Nav variant="tabs" defaultActiveKey="/home">
                  <Nav.Item>
                      <Nav.Link as={Link} to="/">Início</Nav.Link>
                  </Nav.Item>
                  <Nav.Item>
                      <Nav.Link as={Link} to="/pessoa_fisica">Pessoa física</Nav.Link>
                  </Nav.Item>
                  <Nav.Item>
                      <Nav.Link as={Link} to="/pessoa_juridica">Pessoa jurídica</Nav.Link>
                  </Nav.Item>
                  <Nav.Item>
                      <Nav.Link as={Link} to="/prospecta_cliente">Prospecta cliente</Nav.Link>
                  </Nav.Item>
              </Nav>

              <Routes>
                  <Route path="/" index element={<Home/>}></Route>
                  <Route path="/pessoa_fisica" element={<PessoaFisica/>}></Route>
                  <Route path="/pessoa_juridica" element={<PessoaJuridica/>}></Route>
                  <Route path="/prospecta_cliente" element={<ProspectaCliente/>}></Route>
              </Routes>
          </BrowserRouter>
        </div>
      </div>
    </div>
  );
}

export default App;
