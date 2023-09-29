import './Home.css'
import 'bootstrap/dist/css/bootstrap.min.css';

function Home(){
    return (
        <div className="container d-flex flex-column justify-content-center align-items-center text-center" style={{ minHeight: '50vh' }}>
            <h2>Bootcamp Cielo Ada Tech</h2>
            <h3>Desafio individual</h3>
            <div className="footer">
            <p>por Anselmo Blanco Dominguez<br/>
            <a href={"mailto:anselmo.blanco.dominguez@gmail.com"}>anselmo.blanco.dominguez@gmail.com</a><br/>
            +55 21 98088-2229</p>
            </div>
        </div>
    )
}
export default Home;