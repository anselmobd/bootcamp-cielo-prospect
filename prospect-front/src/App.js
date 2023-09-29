import './App.css';
import PessoaFisica from './components/PessoaFisica';
import PessoaJuridica from './components/PessoaJuridica';
import Home from "./components/Home";
import {
    BrowserRouter,
    Routes,
    Link,
    Route
} from "react-router-dom";

function App() {
  return (
    <div className="App">
      <h1>Bootcamp Cielo - Prospectar clientes</h1>
      <BrowserRouter>
        <ul>
          <li><Link to="/">Início</Link></li>
          <li><Link to="/pessoa_fisica">Pessoa física</Link></li>
          <li><Link to="/pessoa_juridica">Pessoa jurídica</Link></li>
        </ul>

        <Routes>
          <Route path="/" index element={<Home />}></Route>
          <Route path="/pessoa_fisica" element={<PessoaFisica />}></Route>
          <Route path="/pessoa_juridica" element={<PessoaJuridica />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
